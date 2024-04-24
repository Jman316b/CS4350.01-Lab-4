import java.sql.*;

public class PomonaTransitSystem {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn = newPTSConnection("CS4350", "postgres", "^d5uvNTM2");
        
        closeConnection(conn);
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
    
    public static String fullSchedule() {
    	String output = "";
    	
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
