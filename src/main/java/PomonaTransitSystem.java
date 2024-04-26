import java.sql.*;

public class PomonaTransitSystem {
    // Privated variables for the database connection
    private static final String DB_NAME = "4350";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "joshadmin";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Connect to the database, if error, throw exception.
        Connection conn = null;
        Statement statement = null;

        try {
        // Open connection to the database
        conn = newPTSConnection(DB_NAME, USERNAME, PASSWORD);

        // Create all tables if they do not exist.
        statement = conn.createStatement();




        // Close the connection
        closeConnection(conn);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static Connection newPTSConnection(String dbName, String username, String password) throws ClassNotFoundException, SQLException {
    	String jdbcUrl = "jdbc:postgresql://localhost:5432/" + dbName;
    	
    	// Register the PostgreSQL driver
        Class.forName("org.postgresql.Driver");

        // Connect to the database
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
    
    public static void closeConnection(Connection connection) throws SQLException {
    	// Close the connection
        connection.close();
    }


    public static String fullSchedule(String startLoc, String destLoc, String date, Connection connection) {
        // Query the database for the full schedule
        String query = "SELECT * FROM TripOffering WHERE startLocationName = ? AND destinationName = ? AND date = ?";
    	String output = "This is a schedule for all trips from " + startLoc + " to " + destLoc + " on " + date + "\n";
    	
    	return output;
    }
    
    public static void deleteTripOffering(int tripNumber, String date, String scheduledStartTime) {
    	
    }
    
    public static void addTripOfferings(TripOffering[] trips){
        
    }
    
    public static void changeDriver(int tripNumber, String date, String scheduledStartTime, String driver){

    }

    public static void changeBus(int tripNumber, String date, String scheduledStartTime, int busID){

    }

    public static String tripStops(int tripNumber){
        String output = "";
    	
    	return output;
    }

    public static String driverScheduleForDate(String driver, String date){
        String output = "";
    	
    	return output;
    }

    public static void addDrive(){

    }

    public static void addBus(){

    }

    public static void deleteBus(){

    }
    
// 8. Record (insert) the actual data of a given trip offering specified by its key. 
// The actual data include the attributes of the table ActualTripStopInfo.


}
