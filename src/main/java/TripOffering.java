public class TripOffering {
	private int tripNumber;
	private String date;
	private String scheduledStartTime;
	private String secheduledArrivalTime;
	private String driverName;
	private int busID;
	
	public TripOffering(int tripNumber, String date, String scheduledStartTime, String secheduledArrivalTime,
		String driverName, int busID) {
		this.tripNumber = tripNumber;
		this.date = date;
		this.scheduledStartTime = scheduledStartTime;
		this.secheduledArrivalTime = secheduledArrivalTime;
		this.driverName = driverName;
		this.busID = busID;
	}

	public int getTripNumber() {
		return tripNumber;
	}

	public void setTripNumber(int tripNumber) {
		this.tripNumber = tripNumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getScheduledStartTime() {
		return scheduledStartTime;
	}

	public void setScheduledStartTime(String scheduledStartTime) {
		this.scheduledStartTime = scheduledStartTime;
	}

	public String getSecheduledArrivalTime() {
		return secheduledArrivalTime;
	}

	public void setSecheduledArrivalTime(String secheduledArrivalTime) {
		this.secheduledArrivalTime = secheduledArrivalTime;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public int getBusID() {
		return busID;
	}

	public void setBusID(int busID) {
		this.busID = busID;
	}

	
}
