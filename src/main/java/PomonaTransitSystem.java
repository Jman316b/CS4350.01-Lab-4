import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class PomonaTransitSystem {
    // Privated variables for the database connection
    private static Dotenv dot_env = Dotenv.load();
    private static final String DB_NAME = "4350";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "joshadmin";
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Connect to the database, if error, throw exception.
        Connection conn = null;
        Statement stmt = null;
        try {
        // Open connection to the database
        conn = newPTSConnection(DB_NAME, USERNAME, PASSWORD);

        stmt = conn.createStatement();

        System.out.println(createTables(conn, stmt));


        // Close the connection
        stmt.close();
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

    /*

    Create the tables in the database. If they already exist, does nothing.

    @param connection: Connection object to the database.
    @param statement: Statement object to execute SQL queries.
     
    @return: Wether tables could or couldn't be created.

    */
    @SuppressWarnings("unused")
    public static String createTables(Connection connection, Statement statement){
        // Check if the connection and statement are not null
        if (connection != null || statement != null) {
            try{

                // Trip Table
                String createTripTable = 
                "CREATE TABLE IF NOT EXISTS Trip (" +
                "tripNumber INT PRIMARY KEY," +
                "startLocationName VARCHAR(255) NOT NULL," +
                "destinationName VARCHAR(255) NOT NULL" +
                ");";

                // Bus Table
                String createBusTable =
                "CREATE TABLE IF NOT EXISTS Bus (" +
                "busID INT PRIMARY KEY," +
                "model VARCHAR(255) NOT NULL," +
                "year INT NOT NULL" +
                ");";

                // Driver Table
                String createDriverTable =
                "CREATE TABLE IF NOT EXISTS Driver (" +
                "driverName VARCHAR(255) PRIMARY KEY," +
                "driverTelephoneNumber VARCHAR(255) NOT NULL" +
                ");";

                // Stop Table
                String createStopTable =
                "CREATE TABLE IF NOT EXISTS Stop (" +
                "stopID INT PRIMARY KEY," +
                "stopName VARCHAR(255) NOT NULL" +
                ");";

                // TripOffering Table
                String createTripOfferingTable =
                "CREATE TABLE IF NOT EXISTS TripOffering (" +
                "tripNumber INT NOT NULL," +
                "date DATE NOT NULL," +
                "scheduledStartTime TIME NOT NULL," +
                "scheduledArrivalTime TIME NOT NULL," +
                "driverName VARCHAR(255) NOT NULL," +
                "busID INT NOT NULL," +
                "PRIMARY KEY (tripNumber, date, scheduledStartTime)," +
                "FOREIGN KEY (tripNumber) REFERENCES Trip(tripNumber)," +
                "FOREIGN KEY (driverName) REFERENCES Driver(driverName)," +
                "FOREIGN KEY (busID) REFERENCES Bus(busID)" +
                ");";

                // Trip Stop Info Table
                String createTripStopInfoTable =
                "CREATE TABLE IF NOT EXISTS TripStopInfo (" +
                "tripNumber INT NOT NULL," +
                "stopID INT NOT NULL," +
                "sequenceNumber INT NOT NULL," +
                "driveTime VARCHAR(255) NOT NULL," +
                "FOREIGN KEY (tripNumber) REFERENCES Trip(tripNumber)," +
                "FOREIGN KEY (stopID) REFERENCES Stop(stopID)" +
                ");";

                // Actual Trip Stop Info Table
                String createActualTripStopInfoTable =
                "CREATE TABLE IF NOT EXISTS ActualTripStopInfo (" +
                "tripNumber INT NOT NULL," +
                "date DATE NOT NULL," +
                "scheduledStartTime TIME NOT NULL," +
                "stopID INT NOT NULL," +
                "actualStartTime TIME NULL," +
                "actualArrivalTime TIME NULL," +
                "numberOfPassengerIn INT NULL," +
                "numberOfPassengerOut INT NULL," +
                "PRIMARY KEY (tripNumber, date, scheduledStartTime, stopID)," +
                "FOREIGN KEY (tripNumber) REFERENCES Trip(tripNumber)," +
                "FOREIGN KEY (stopID) REFERENCES Stop(stopID)" +
                ");";

                // Add all the queries to a batch
                statement.addBatch(createTripTable);
                statement.addBatch(createBusTable);
                statement.addBatch(createDriverTable);
                statement.addBatch(createStopTable);
                statement.addBatch(createTripOfferingTable);
                statement.addBatch(createTripStopInfoTable);
                statement.addBatch(createActualTripStopInfoTable);

                // Execute the batch
                statement.executeBatch();
                return "Tables created/recreated successfully!";
            }
        catch (SQLException e) {
                e.printStackTrace();
            }

        return "in if statement but not returning anything, probably an exception is thrown";
        }
        // Error handling.
        else if (connection == null && statement != null){
            return "Error creating tables, connection is null.";
        }
        else if (connection != null && statement == null)
            return "Error creating tables, statement is null.";

        else
        return "Error creating tables both connection and statement is null.";
    }










    public static String fullSchedule(String startLoc, String destLoc, String date, Connection connection) {
        // Query the database for the full schedule
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
