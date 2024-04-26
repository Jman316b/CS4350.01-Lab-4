public class ActualTripStopInfo{
    private TripOffering tripOffering;
    private Stop stop;
    private String actualStartTime;
    private String actualArrivalTime;
    private int numberOfPassengerIn;
    private int numberOfPassengerOut;

    public ActualTripStopInfo(TripOffering tripOffering, Stop stop, String actualStartTime, String actualArrivalTime, int numberOfPassengerIn, int numberOfPassengerOut) {
        this.tripOffering = tripOffering;
        this.stop = stop;
        this.actualStartTime = actualStartTime;
        this.actualArrivalTime = actualArrivalTime;
        this.numberOfPassengerIn = numberOfPassengerIn;
        this.numberOfPassengerOut = numberOfPassengerOut;
    }

    public TripOffering getTripOffering() {
        return tripOffering;
    }

    public void setTripOffering(TripOffering tripOffering) {
        this.tripOffering = tripOffering;
    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    public String getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(String actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public String getActualArrivalTime() {
        return actualArrivalTime;
    }

    public void setActualArrivalTime(String actualArrivalTime) {
        this.actualArrivalTime = actualArrivalTime;
    }

    public int getNumberOfPassengerIn() {
        return numberOfPassengerIn;
    }

    public void setNumberOfPassengerIn(int numberOfPassengerIn) {
        this.numberOfPassengerIn = numberOfPassengerIn;
    }

    public int getNumberOfPassengerOut() {
        return numberOfPassengerOut;
    }

    public void setNumberOfPassengerOut(int numberOfPassengerOut) {
        this.numberOfPassengerOut = numberOfPassengerOut;
    }

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
