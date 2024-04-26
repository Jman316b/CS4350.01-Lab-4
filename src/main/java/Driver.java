/**
 * Driver object class for Pomona Transit System
 * @author Joshua Jenkins
 * @author Jeremiah Garchia
 * @version 1.0
 */
public class Driver{
    private String driverName;
    private String driverTelephoneNumber;

    /**
     * Constructor for Driver object
     * @param driverName - name of the driver
     * @param driverTelephoneNumber - telephone number of the driver
     */
    public Driver(String driverName, String driverTelephoneNumber) {
        this.driverName = driverName;
        this.driverTelephoneNumber = driverTelephoneNumber;
    }

    /**
     * Get the name of the driver
     * @return - name of the driver
     */
    public String getDriverName() {
        return driverName;
    }

    /**
     * Set the name of the driver
     * @param driverName - name of the driver
     */
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    /**
     * Get the telephone number of the driver
     * @return - telephone number of the driver
     */
    public String getDriverTelephoneNumber() {
        return driverTelephoneNumber;
    }

    /**
     * Set the telephone number of the driver
     * @param driverTelephoneNumber - telephone number of the driver
     */
    public void setDriverTelephoneNumber(String driverTelephoneNumber) {
        this.driverTelephoneNumber = driverTelephoneNumber;
    }

    /**
     * toString method for Driver object
     * @return - driverName and driverTelephoneNumber in string format
     */
    public String toString() {
        return 
        "\n [PK] Driver Name: " + driverName + 
        "\n Telephone Number: " + driverTelephoneNumber;
    }
}