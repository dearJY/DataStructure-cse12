
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include "stack.h"

//Type conversions starts here//

char* charToString(char c) {
	char *str = (char*)malloc(sizeof(char)*TWO);
	str[ZERO] = c;
	str[ONE] = '\0';
	return str;
}
char stringToChar(char* str) {
	return str[ZERO];
}

char* intToString(int res) {
	char *str = (char*)malloc(BUFSIZ);
	sprintf(str,"%d",res);
	return str;
}

int stringToInt(char* str) {
	return atoi(str);
}
//Type conversions end here

// --------------------------------------------------------------------------

//Helper function to return precedence of operator
int precedence(char x) {
	//TODO
	if(x == '*' || x == '/' || x == '%') {
		return 1;
	}else if(x == '+' || x == '-') {
		return 0;
	}else {
		return -1;
	}
}


char* convertToPostFix(char* str) {
	//TODO
	Stack* operatorStack = stack_init();
	char* postfix;//for output
	postfix = (char*)malloc(256*sizeof(char));

	int i;
	for(i=0;i<strlen(str);i++) {
		char c = *(str+i);//char of index i in string

		if(isdigit(c)) {
			strcat(postfix,charToString(c));//if is digit, append to output string
		}else if(c == '(') {
			push(operatorStack,charToString(c));//if left parenthesis, push to stack
		}else if(c == ')') {
			//if is right parenthesis, pop stack until find left parenthesis
			char right[2];
			strcpy(right,"(");
			while(strcmp(peek(operatorStack),right) != 0) {
				strcat(postfix,pop(operatorStack));
			}
			pop(operatorStack);//pop left parenthsis
		}else {//if is operator

			int op1 = precedence(c);
			if(isEmpty(operatorStack) == 1) {
				push(operatorStack,charToString(c));
				continue;
			}

			char top = stringToChar(peek(operatorStack));
			int op2 = precedence(top);

			//if top is not operator or smaller than op1, push op1
			if(op2<0 || op1>op2) {
				push(operatorStack,charToString(c));
			}else {
				//if top is equal to or greater than op1. continue popping stack
				//until top is not operator or smaller than op1
				while(op2>=op1) {
						strcat(postfix,pop(operatorStack));
						if(isEmpty(operatorStack) == 0) {
						top = stringToChar(peek(operatorStack));
						op2 = precedence(top);
					}else {
						break;
					}
				}
				push(operatorStack,charToString(c));//finally push op1 to stack
			}
		}
	}

	//run out of string, pop all elements in stack
	while(isEmpty(operatorStack) == 0) {
		strcat(postfix,pop(operatorStack));
	}

	return postfix;
	
}


int calculateExpression(char* str) {
	//TODO
	Stack* operatorStack2 = stack_init();//store char in str
	int result = 0;
	int temp;
	int i;
	for(i=0;i<strlen(str);i++) {
		char c2 = *(str+i);
		if(isdigit(c2)) {
			push(operatorStack2,charToString(c2));
		}else {
			int n1 = stringToInt(pop(operatorStack2));
			int n2 = stringToInt(pop(operatorStack2));
			if(c2 == '+') {
				temp = n1+n2;
			}else if(c2 == '-') {
				temp = n2-n1;
			}else if(c2 == '*') {
				temp = n2*n1;
			}else if(c2 == '/') {
				temp = n2/n1;
			}else if(c2 == '%') {
				temp = n2%n1;
			}
			result = temp;
			push(operatorStack2,intToString(temp));
		}
	}
	return result;
}


int main(int argc, char **argv) {
	
	if(argc<TWO) {
		printf(STR_INCORRECT_ARGUMENT);
	} else {
		
		//TODO CHANGE BELOW!!
		char* input;
		//input = (char*)malloc(256*sizeof(char));  
		input = *(argv+1);
		char* postfix;
		//postfix = (char*)malloc(256*sizeof(char));
		int answer;

		printf("Input: %s",input);
		postfix = convertToPostFix(input);
		answer = calculateExpression(postfix);
		//TODO CHANGE ABOVE!!
		
		printf(STR_POSTFIX_EXPRESSION,postfix);
		printf(STR_RESULT,answer);
		//free(input);
		free(postfix);
	}
	return EXIT_SUCCESS;
}
