
public class Record implements Comparable<Record> {

	private String process;
	private long deadline; 
	private long duration; 

	// constructor to create a new record
	// given the name of the process,
	// deadline and duration
	Record (String process, long deadline, long duration)
	{
		this.process = process;
		this.deadline = deadline;
		this.duration = duration;
	}

	// constructor to create a new record
	// from the esisting record and new
	// duration
	Record (Record r, long duration)
	{
		this.process=r.process;
		this.deadline=r.deadline;
		this.duration=duration;
	}
	
	public long GetDuration()
	{
		return duration;
	}
	
	public String toString()
	{
		return process+" with deadline "+deadline+" and duration "+duration;
	}
	public String toString(long current_time)
	{
		if(current_time > deadline) return process + " (late)";
		return process;
	}
	
	/**
	 * Compares this Record with another Record, by comparing their deadlines.
	 * If the deadline of this is earlier, returns a negative integer. If the
	 * deadline of the Record received is earlier, returns a positive integer.
	 * If both Records have the same deadline, returns zero.
	 *
	 * @param o The Record to be compared with this.
	 * @return A value smaller than 0 if this has an earlier deadline. A value
	 *            larger than 0 if o has an earlier deadline. 0 if both this
	 *            and o have the same deadline.
	 */
	@Override
    public int compareTo(Record o) 
	{
		if(this.deadline<o.deadline) {
			return -1;
		}else if(this.deadline>o.deadline) {
			return 1;
		}else {
			return 0;
		}
    }
}
