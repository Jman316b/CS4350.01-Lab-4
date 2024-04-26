public class TripStopInfo{
    private Trip trip;
    private Stop stop;
    private int sequenceNumber;
    private String drivingTime;

    public TripStopInfo(Trip trip, Stop stop, int sequenceNumber, String drivingTime) {
        this.trip = trip;
        this.stop = stop;
        this.sequenceNumber = sequenceNumber;
        this.drivingTime = drivingTime;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getDrivingTime() {
        return drivingTime;
    }

    public void setDrivingTime(String drivingTime) {
        this.drivingTime = drivingTime;
    }

    public String toString() {
        return 
        "\n [PK] Trip Number: " + trip.getTripNumber() + 
        "\n [PK] Stop Number: " + stop.getStopNumber() + 
        "\n Sequence Number: " + sequenceNumber + 
        "\n Driving Time: " + drivingTime;
    }
}