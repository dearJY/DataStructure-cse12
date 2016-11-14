

import java.io.*;
import java.util.Scanner;

public class EDF {
	
	public static void print(int choice,long current_time, Record r)
	{
		if(choice==1)
			System.out.println( current_time + ": adding " +
	                r.toString() );
		else if(choice==2)
			 System.out.println( current_time + ": busy with " +
	                 r.toString() );
		else
			System.out.println( current_time +  ": done with " +
	                r.toString( current_time ) );
	}

	public static void main(String[] args) {

		if(args.length != 1)
		{
			 System.err.println("Incorrect number of arguments: "+args.length);
			 System.err.println("java hw6.EDF <input-file>");
			 System.exit(1); 
		}

		File file = new File(args[0]);
		MyPriorityQueue<Record> queue = new MyPriorityQueue<Record>(10);
		long current_time=0;


		try{
			Scanner scanner =  new Scanner(file);
			while(scanner.hasNextLine()) {

				String line = scanner.nextLine();
				String[] info = line.trim().split(" ");//divide line

				String event;
				long deadline=0;
				long duration=0;
				long time=0;
				Record newItem;//event added first time
				Record doingEvent;//busy doing event
				Record continueEvent = null;//event finished late

				if(info[0].equals("schedule")) {

					event = info[1];
					deadline = Long.parseLong(info[2]);
					duration = Long.parseLong(info[3]);
					newItem = new Record(event,deadline,duration);
					queue.add(newItem);
					print(1,current_time,newItem);

				}else if (info[0].equals("run")) {

					long diff=0;//remaining time to finish a certain event
					time = Long.parseLong(info[1]);//end time

					while(current_time!=time) {

						doingEvent = queue.poll();

						//queue is empty
						if(doingEvent == null) break;

						print(2, current_time, doingEvent);

						current_time += doingEvent.GetDuration();

						//overtime
						if (current_time > time) {
							diff = current_time - time;
							continueEvent = new Record(doingEvent,diff);//revise event
							current_time = time;
							queue.add(continueEvent);//add renewed event
							print(1,current_time,continueEvent);
						}else {
							print(3,current_time,doingEvent);
						}

						if(doingEvent.equals(continueEvent)) {
							print(3, current_time, doingEvent);
						}

					}

					current_time = time;

				}


			}
		}
		catch(FileNotFoundException e)
		{
			System.err.println("Failed to open "+file);
			System.exit(1);
		}
		System.exit(0);
		
	}

}
