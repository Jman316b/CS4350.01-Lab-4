import java.sql.*;
/**
 * TripOffering object class for Pomona Transit System
 * @author Joshua Jenkins
 * @author Jeremiah Garchia
 * @version 1.0
 */
public class TripOffering{
	private Trip trip;
	private Date date;
	private String scheduledStartTime;
	private String secheduledArrivalTime;
	private Driver driver;
	private Bus bus;
	
	/**
	 * Constructor for TripOffering object
	 * @param trip - Trip object to get the trip number
	 * @param date - date of the trip offering
	 * @param scheduledStartTime - scheduled start time of the trip offering
	 * @param secheduledArrivalTime - scheduled arrival time of the trip offering
	 * @param driver - Driver object to get the driver name and telephone number
	 * @param bus - Bus object to get the busID, model, and year
	 */
	public TripOffering(Trip trip, Date date, String scheduledStartTime, String secheduledArrivalTime,
		Driver driver, Bus bus) {
		this.trip = trip;
		this.date = date;
		this.scheduledStartTime = scheduledStartTime;
		this.secheduledArrivalTime = secheduledArrivalTime;
		this.driver = driver;
		this.bus = bus;
	}

	/**
	 * Get the Trip object
	 * @return Trip object
	 */
	public Trip getTrip() {
		return trip;
	}

	/**
	 * Set the Trip object
	 * @param trip Trip object
	 */
	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	/**
	 * Get the date of the trip offering as a Date object
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Get the date of the trip offering as a string
	 * @return the date as a string
	 */
	public String getDateAsString() {
		return date.toString();
	}

	/**
	 * Set the date of the trip offering given a string
	 * @param dateString - date of the trip offering
	 */
	public void setDateFromString(String dateString) {
		this.date = java.sql.Date.valueOf(dateString);
	}

	/**
	 * Set the date of the trip offering as a Date object
	 * @param date - date of the trip offering
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Get the scheduled start time of the trip offering
	 * @return the scheduled start time
	 */
	public String getScheduledStartTime() {
		return scheduledStartTime;
	}

	/**
	 * Set the scheduled start time of the trip offering
	 * @param scheduledStartTime - scheduled start time
	 */
	public void setScheduledStartTime(String scheduledStartTime) {
		this.scheduledStartTime = scheduledStartTime;
	}

	/**
	 * Get the scheduled arrival time of the trip offering
	 * @return the scheduled arrival time
	 */
	public String getSecheduledArrivalTime() {
		return secheduledArrivalTime;
	}

	/**
	 * Set the scheduled arrival time of the trip offering
	 * @param secheduledArrivalTime - scheduled arrival time
	 */
	public void setSecheduledArrivalTime(String secheduledArrivalTime) {
		this.secheduledArrivalTime = secheduledArrivalTime;
	}

	/**
	 * Get the Driver object
	 * @return Driver object
	 */
	public Driver getDriver() {
		return driver;
	}

	/**
	 * Set the Driver object
	 * @param driver Driver object
	 */
	public void setDriverName(Driver driver) {
		this.driver = driver;
	}

	/**
	 * Get the Bus object
	 * @return Bus object
	 */
	public Bus getBus() {
		return bus;
	}

	/**
	 * Set the Bus object
	 * @param bus Bus object
	 */
	public void setBusID(Bus bus) {
		this.bus = bus;
	}

	/**
	 * toString method for TripOffering object
	 * @return - trip number, date, scheduled start time, scheduled arrival time, driver name, and busID in string format
	 */
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
