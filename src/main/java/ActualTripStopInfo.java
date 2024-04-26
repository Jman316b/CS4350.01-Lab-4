/**
 * ActualTripStopInfo object class for the Pomona Transit System.
 * @author Joshua Jenkins
 * @author Jeremiah Garchia
 * @version 1.0
 */
public class ActualTripStopInfo{
    private TripOffering tripOffering;
    private Stop stop;
    private String actualStartTime;
    private String actualArrivalTime;
    private int numberOfPassengerIn;
    private int numberOfPassengerOut;

    /**
     * Constructor for ActualTripStopInfo object
     * @param tripOffering - TripOffering object to get the trip number, date, scheduled start time, and scheduled arrival time
     * @param stop - Stop object to get the stop number
     * @param actualStartTime - The actual start time
     * @param actualArrivalTime - The actual arrival time
     * @param numberOfPassengerIn - The number of passengers in the bus at the stop
     * @param numberOfPassengerOut - The number of passengers who left the bus at the stop
     */
    public ActualTripStopInfo(TripOffering tripOffering, Stop stop, String actualStartTime, String actualArrivalTime, int numberOfPassengerIn, int numberOfPassengerOut) {
        this.tripOffering = tripOffering;
        this.stop = stop;
        this.actualStartTime = actualStartTime;
        this.actualArrivalTime = actualArrivalTime;
        this.numberOfPassengerIn = numberOfPassengerIn;
        this.numberOfPassengerOut = numberOfPassengerOut;
    }

    /**
     * Get the TripOffering object
     * @return TripOffering object
     */
    public TripOffering getTripOffering() {
        return tripOffering;
    }

    /**
     * Set the TripOffering object
     * @param tripOffering TripOffering object
     */
    public void setTripOffering(TripOffering tripOffering) {
        this.tripOffering = tripOffering;
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
     * Get the actual start time
     * @return Actual start time
     */
    public String getActualStartTime() {
        return actualStartTime;
    }

    /**
     * Set the actual start time
     * @param actualStartTime String of the actual start time
     */
    public void setActualStartTime(String actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    /**
     * Get the actual arrival time
     * @return Actual arrival time
     */
    public String getActualArrivalTime() {
        return actualArrivalTime;
    }

    /**
     * Set the actual arrival time
     * @param actualArrivalTime The actual arrival time
     */
    public void setActualArrivalTime(String actualArrivalTime) {
        this.actualArrivalTime = actualArrivalTime;
    }

    /**
     * Get the number of passengers in the bus at the stop
     * @return Number of passengers in the bus
     */
    public int getNumberOfPassengerIn() {
        return numberOfPassengerIn;
    }

    /**
     * Set the number of passengers who got in the bus at the stop
     * @param numberOfPassengerIn Number of passengers in the bus
     */
    public void setNumberOfPassengerIn(int numberOfPassengerIn) {
        this.numberOfPassengerIn = numberOfPassengerIn;
    }

    /**
     * Get the number of passengers who left the bus at the stop
     * @return Number of passengers out of the bus
     */
    public int getNumberOfPassengerOut() {
        return numberOfPassengerOut;
    }

    /**
     * Set the number of passengers out of the bus at the stop
     * @param numberOfPassengerOut Number of passengers out of the bus
     */
    public void setNumberOfPassengerOut(int numberOfPassengerOut) {
        this.numberOfPassengerOut = numberOfPassengerOut;
    }

    /**
     * Overwrite the toString method to return the string representation of the ActualTripStopInfo object
     * @return tripNumber, date, scheduledStartTime, stopNumber, scheduledArrivalTime, actualStartTime, actualArrivalTime, numberOfPassengersIn, and numberOfPassengersOut in string format
     */
    public String toString() {
        return 
        "\n [PK] Trip Number: " + tripOffering.getTrip().getTripNumber() + 
        "\n [PK] Date: " + tripOffering.getDate() + 
        "\n [PK] Scheduled Start Time: " + tripOffering.getScheduledStartTime()  + 
        "\n [PK] Stop Number: " + stop.getStopNumber() + 
        "\n Scheduled Arrival Time: " + tripOffering.getSecheduledArrivalTime() + 
        "\n Actual Start Time: " + actualStartTime + 
        "\n Actual Arrival Time: " + actualArrivalTime + 
        "\n Number of Passengers In: " + numberOfPassengerIn + 
        "\n Number of Passengers Out: " + numberOfPassengerOut;
    }

}
