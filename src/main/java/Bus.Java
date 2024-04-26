public class Bus{
    private int busID;
    private String model;
    private int year;

    public Bus(int busID, String model, int year) {
        this.busID = busID;
        this.model = model;
        this.year = year;
    }

    public int getBusID() {
        return busID;
    }

    public void setBusID(int busID) {
        this.busID = busID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString() {
        return 
        "\n [PK] Bus ID: " + busID + 
        "\n Model: " + model + 
        "\n Year: " + year;
    }
}