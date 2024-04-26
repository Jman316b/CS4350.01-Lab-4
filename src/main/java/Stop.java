public class Stop{
    private int stopNumber;
    private String stopAddress;

    public Stop(int stopNumber, String stopAddress) {
        this.stopNumber = stopNumber;
        this.stopAddress = stopAddress;
    }

    public int getStopNumber() {
        return stopNumber;
    }

    public void setStopNumber(int stopNumber) {
        this.stopNumber = stopNumber;
    }

    public String getStopAddress() {
        return stopAddress;
    }

    public void setStopAddress(String stopAddress) {
        this.stopAddress = stopAddress;
    }

    public String toString() {
        return 
        "\n [PK] Stop Number: " + stopNumber + 
        "\n Stop Address: " + stopAddress;
    }
}