import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LineCounter {

	//Method to print the filename to console
	
	public static void printFileName(String filename) {
		System.out.println("\n"+filename+":");
	}
	
	//method to print the statistics to console
	public static void printStatistics(String compareFileName, int percentage) {
		System.out.println(percentage+"% of lines are also in "+compareFileName);
	}
	
	public static void main(String[] args) {
		
		if(args.length<2) {
			System.err.println("Invalid number of arguments passed");
			return;
		}
		
		int numArgs = args.length;
		
		//Create a hash table for every file
		HashTable[] tableList = new HashTable[numArgs];
		
		//Preprocessing: Read every file and create a HashTable
		try{
			for(int i=0; i<numArgs; i++) {

				tableList[i] = new HashTable(10);
				File file = new File(args[i]);
				Scanner input = new Scanner(file);

				while(input.hasNextLine()) {
					String line = input.nextLine();//read each line
					tableList[i].insert(line);
				}
			}
		} catch(Exception e) {
			System.out.println("Exception when storing!");
		}
		
		//Find similarities across files

			try{
				//outer loop: get ith file
				for(int i=0; i<numArgs; i++) {

					File file = new File(args[i]);

					printFileName(args[i]);

					//inner loop: ith file compare with all the files
					for(int j=0;j<numArgs;j++) {
						Scanner input = new Scanner(file);
						int total = 0;//counter for total amounts of elements
						int counter = 0;//count similarity

						//ignore compare with itself
						if(i==j) {
							continue;
						}
						
						while(input.hasNextLine()) {
							total++;
							String line = input.nextLine();//read lines in ith file
							if(tableList[j].lookup(line)) {
								counter++;
							}
						}
						input.close();
						int percent = counter*100/total;
						printStatistics(args[j],percent);

					}
				}
			} catch(Exception e) {
				System.out.println("Exception when checking!");
			
		}
	}

}
