

import java.util.*;
import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class HashTable implements IHashTable {
	
	//You will need a HashTable of LinkedLists. 
	
	private int nelems;  //Number of element stored in the hash table
	private int expand;  //Number of times that the table has been expanded
	private int collision;  //Number of collisions since last expansion
	private String statsFileName;     //FilePath for the file to write statistics upon every rehash
	private boolean printStats = false;   //Boolean to decide whether to write statistics to file or not after rehashing
	private double load; // load factor
	private LinkedList<String>[] hashTable;
	private LinkedList<String>[] tempTable;
	private File output;
	private int longest;//the number of longest chain
	//You are allowed to add more :)
	
	/**
	 * Constructor for hash table
	 * @param size
	 */
	public HashTable(int size) {
		
		//Initialize
		this.hashTable = new LinkedList[size];
		for(int i=0;i<hashTable.length;i++) {
			hashTable[i] = new LinkedList<String>();
		}

	}
	
	/**
	 * Constructor for hash table
	 * @param size
	 * @param fileName
	 */
	public HashTable(int size, String fileName){
		
		//Set printStats to true and statsFileName to fileName
		this.printStats = true;
		this.statsFileName = fileName;
		this.hashTable = new LinkedList[size];
		for(int i=0;i<hashTable.length;i++) {
			hashTable[i] = new LinkedList<String>();
		}

	}

	/**
	 * insert a value into hashTable
	 * @param value value to insert
	 * @return
	 */
	@Override
	public boolean insert(String value){

		if(value == null) {
			throw new NullPointerException();
		}

		if(lookup(value)) {
			return false;
		}

		if(this.load >= 0.67) {
			//rehash when load factor is greater than 0.67;
				expand++;
			if(printStats == true) {
				printStatistics();
			}//print table only when printStats is true
				collision = 0;
				longest = 0;
				rehash();
		}

		int index = this.hashFunction(value,tableSize());
		//check collision
		if(!hashTable[index].isEmpty()) {
			collision++;
		}

		hashTable[index].add(value);
		nelems++;
		setLoad();
		//check longest chain
		if(hashTable[index].size() > longest) {
			longest = hashTable[index].size();
		}

		return true;

		
	}

	/**
	 * delete a value from hashTable
	 * @param value value to delete
	 * @return
	 */
	@Override
	public boolean delete(String value) {

		int index = hashFunction(value,tableSize());
		if(value == null) {
			throw new NullPointerException();
		}

		if(!lookup(value)) {
			return false;
		}else {
			hashTable[index].remove(value);
			nelems--;
			return true;
		}

	}

	/**
	 * look for a value in hashTable
	 * @param value value to look up
	 * @return
	 */
	@Override
	public boolean lookup(String value) {

		int index = hashFunction(value,tableSize());
		if(hashTable[index].isEmpty()) {
			return false;
		}else {
			for (int i = 0; i < hashTable[index].size(); i++) {
				if(hashTable[index].contains(value)){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * print out the hashTable
	 */
	@Override
	public void printTable() {
		if(nelems == 0) {
			return;
		}else {
			for (int i = 0; i < hashTable.length; i++) {
				if (hashTable[i].isEmpty()) {
					System.out.println(i + ":");
				} else {
					System.out.print(i + ": ");
					System.out.println(hashTable[i].toString());
				}
			}
		}
	}

	/**
	 * get the number of elements in the hashTable
	 * @return
	 */
	@Override
	public int getSize() {
		return this.nelems;
	}

	/**
	 * algorithm of hashfunction
	 * @param key
	 * @param tableSize
	 * @return
	 */
	private int hashFunction(String key,int tableSize) {
		int hashValue = 0;
		for(int i=0;i<key.length();i++) {
			int letter = key.charAt(i);
			hashValue = (hashValue*27 + letter)%tableSize;
		}
		return hashValue;
	}

	/**
	 * get size of hashTable
	 * @return
	 */
	private int tableSize() {
		return this.hashTable.length;
	}

	/**
	 * rehash function
	 */
	private void rehash() {

		int newTableSize = tableSize()*2;
		tempTable = new LinkedList[newTableSize];//doubled size hashTable
		for(int i=0;i<newTableSize;i++) {
			tempTable[i] = new LinkedList<String>();
		}
		for(int i=0;i<tableSize();i++) {
			LinkedList<String> chain = hashTable[i];//linkedlist of ith position
			for(int j=0;j<chain.size();j++) {
				int newIndex = hashFunction(chain.get(j),newTableSize);//position in doubled size hashTable
				tempTable[newIndex].add(chain.get(j));//add element into new hashTable
			}
		}
		hashTable = tempTable;

	}

	/**
	 * print statistic
	 */
	private void printStatistics() {

		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);

		try {
				output = new File(statsFileName);

				if(!output.exists())
				output.createNewFile();


			FileWriter writer = new FileWriter(output,true);
			BufferedWriter bWriter = new BufferedWriter(writer);
			bWriter.write("" + this.expand + " resizes, load factor " + df.format(load) + ", " + this.collision + " collisions, " +
					this.longest + " longest chain\n");
			bWriter.newLine();
			bWriter.close();
		} catch (IOException e) {
			System.out.println("IOExceptions!");
		}
	}

	/**
	 * calculate load factor
	 */
	private void setLoad() {
		load = 1.0 * this.nelems/tableSize();
	}

	public double getLoad() {
		return this.load;
	}

}
