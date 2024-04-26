import java.sql.*;

public class TripOffering{
	private Trip trip;
	private Date date;
	private String scheduledStartTime;
	private String secheduledArrivalTime;
	private Driver driver;
	private Bus bus;
	
	
	public TripOffering(Trip trip, Date date, String scheduledStartTime, String secheduledArrivalTime,
		Driver driver, Bus bus) {
		this.trip = trip;
		this.date = date;
		this.scheduledStartTime = scheduledStartTime;
		this.secheduledArrivalTime = secheduledArrivalTime;
		this.driver = driver;
		this.bus = bus;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public String getDate() {
		return date.toString();
	}

	public void setDate(String dateString) {
		this.date = java.sql.Date.valueOf(dateString);
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

	public Driver getDriver() {
		return driver;
	}

	public void setDriverName(Driver driver) {
		this.driver = driver;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBusID(Bus bus) {
		this.bus = bus;
	}

	public String toString() {
		return 
		"\n [PK] Trip Number: " + trip.getTripNumber() + 
		"\n [PK] Date: " + date.toString() + 
		"\n [PK] Scheduled Start Time: " + scheduledStartTime +
		"\n Scheduled Arrival Time: " + secheduledArrivalTime + 
		"\n Driver Name: " + driver.getDriverName() + 
		"\n Bus ID: " + bus.getBusID();
	}
}
