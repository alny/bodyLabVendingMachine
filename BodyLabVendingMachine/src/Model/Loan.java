package Model;

import java.util.Date;

public class Loan {

	private int id;
	private Date timestamp;

	public Loan(int id, Date timestamp) {
		this.id = id;
		this.timestamp = timestamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
