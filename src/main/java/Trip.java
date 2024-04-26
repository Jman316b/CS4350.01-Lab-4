/**
 * Trip object class for Pomona Transit System
 * @author Joshua Jenkins
 * @author Jeremiah Garchia
 * @version 1.0
 */
public class Trip{
    private int tripNumber;
    private String startLocationName;
    private String destinationName;

    /**
     * Constructor for Trip object
     * @param tripNumber - trip number
     * @param startLocationName - start location name
     * @param destinationName - destination name
     */
    public Trip(int tripNumber, String startLocationName, String destinationName) {
        this.tripNumber = tripNumber;
        this.startLocationName = startLocationName;
        this.destinationName = destinationName;
    }

    /**
     * Get the trip number
     * @return the trip number
     */
    public int getTripNumber() {
        return tripNumber;
    }
    /**
     * Set the trip number
     * @param tripNumber - the trip number
     */
    public void setTripNumber(int tripNumber) {
        this.tripNumber = tripNumber;
    }

    /**
     * Get the start location name
     * @return the start location name
     */
    public String getStartLocationName() {
        return startLocationName;
    }

    /**
     * Set the start location name
     * @param startLocationName - the start location name
     */
    public void setStartLocationName(String startLocationName) {
        this.startLocationName = startLocationName;
    }

    /**
     * Get the destination name
     * @return the destination name
     */
    public String getDestinationName() {
        return destinationName;
    }

    /**
     * Set the destination name
     * @param destinationName - the destination name
     */
    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    /**
     * toString method for Trip object
     * @return - tripNumber, startLocationName, and destinationName in string format
     */
    public String toString() {
        return 
        "\n [PK] Trip Number: " + tripNumber + 
        "\n Start Location: " + startLocationName + 
        "\n Destination: " + destinationName;
    }
}