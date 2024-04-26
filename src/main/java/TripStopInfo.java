/**
 * TripStopInfo object class for the Pomona Transit System.
 * @author Joshua Jenkins
 * @author Jeremiah Garchia
 * @version 1.0
 */
public class TripStopInfo{
    private Trip trip;
    private Stop stop;
    private int sequenceNumber;
    private String drivingTime;

    /**
     * Constructor for TripStopInfo object
     * @param trip - Trip object to get the trip number
     * @param stop - Stop object to get the stop number
     * @param sequenceNumber - The sequence number of the stop in the trip
     * @param drivingTime - The driving time from the previous stop
     */
    public TripStopInfo(Trip trip, Stop stop, int sequenceNumber, String drivingTime) {
        this.trip = trip;
        this.stop = stop;
        this.sequenceNumber = sequenceNumber;
        this.drivingTime = drivingTime;
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
     * Get the Stop object
     * @return Stop object
     */
    public Stop getStop() {
        return stop;
    }

    /**
     * Set the Stop object
     * @param stop Stop object
     */
    public void setStop(Stop stop) {
        this.stop = stop;
    }

    /**
     * Get the sequence number
     * @return the sequence number
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Set the sequence number
     * @param sequenceNumber - the sequence number
     */
    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Get the driving time
     * @return the driving time
     */
    public String getDrivingTime() {
        return drivingTime;
    }

    /**
     * Set the driving time
     * @param drivingTime - the driving time
     */
    public void setDrivingTime(String drivingTime) {
        this.drivingTime = drivingTime;
    }

    /**
     * toString method for TripStopInfo object
     * @return - trip number, stop number, sequence number, and driving time in string format
     */
    public String toString() {
        return 
        "\n [PK] Trip Number: " + trip.getTripNumber() + 
        "\n [PK] Stop Number: " + stop.getStopNumber() + 
        "\n Sequence Number: " + sequenceNumber + 
        "\n Driving Time: " + drivingTime;
    }
}