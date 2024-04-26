public class Driver{
    private String driverName;
    private int driverTelephoneNumber;

    public Driver(String driverName, int driverTelephoneNumber) {
        this.driverName = driverName;
        this.driverTelephoneNumber = driverTelephoneNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public int getDriverTelephoneNumber() {
        return driverTelephoneNumber;
    }

    public void setDriverTelephoneNumber(int driverTelephoneNumber) {
        this.driverTelephoneNumber = driverTelephoneNumber;
    }

    public String toString() {
        return 
        "\n [PK] Driver Name: " + driverName + 
        "\n Telephone Number: " + driverTelephoneNumber;
    }
}