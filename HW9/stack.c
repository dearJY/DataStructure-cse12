

//Including c libraries
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//Including function prototype
#include "stack.h"

/*
 * Initializes a stack
 * Return:
 *   Normal: An empty stack
 *   When there is no memory available to allocate: null pointer
 */
Stack * stack_init() {
	Stack *mystack;
	mystack = (Stack*)malloc(sizeof(Stack));//allocate stack memory
	mystack->array = (char**)malloc(STACK_SIZE*sizeof(char*));//allocate array memory
	
	//allocate string memory
	int i;
	for(i=0;i<STACK_SIZE;i++) {
		(mystack->array)[i] = (char*)malloc(256*sizeof(char));
	}
	mystack->top = STACK_EMPTY;
	return mystack;
}

/*
 * Pushes to a stack
 * Param:
 *   item - the stuff to be pushed
 */
void push(Stack* stack, char * str) {
	int index = stack->top;
	char** p = stack->array;
	index++;
	strcpy(p[index],str);
	stack->top = index;
}

/*
 * Pops from a stack
 */
char * pop(Stack* stack) {
	int index = stack->top;
	char** p = stack->array;
	if(isEmpty(stack)) {
		return (char*)NULL;
	}else {
		char* last = p[index];
		index--;
		stack->top = index;
		return last;
	}
}

/*
 * Returns top element of stack
 */
char * peek(Stack* stack){
	int index = stack->top;
	char** p = stack->array;
	if(isEmpty(stack)) {
		return (char*)NULL;
	}else {
		char* last = p[index];
		return last;
	}
}

/*
 * Destructs a stack
 */
void stack_delete(Stack* stack) {
	//free all strings memory
	int i;
	for(i=0;i<STACK_SIZE;i++) {
		free((stack->array)[i]);
	}
	free(stack->array);
}

int isEmpty(Stack* stack) {
	if(stack->top == STACK_EMPTY) {
		return 1;
	}else {
		return 0;
	}
}

/*
 * Prints out the information of a stack
 */
void print(Stack* stack) {
	if(isEmpty(stack)) {
		printf(STR_PRINT_NO_ITEMS);
	}else {
		char** ele = stack->array;
		printf(STR_PRINT_ITEMS);
		int i;
		for(i=0;i<=stack->top;i++) {
			printf(" %s ",ele[i]);
		}
	}
}












