/* 
 * Project: Lab 4 - CS 4350
 * Date: 04/28/2024
 * Description: The PomonaTransitSystem class is the main class for the Pomona Transit System.
 * It connects to the database, creates the tables in the database, and performs various operations on the database
 * with the use of the various different custom objects within this package.
 */

// Import statements
import java.sql.*;

/**
 * The PomonaTransitSystem class is the main class for database manipulation.
 * 
 * @author Joshua Jenkins
 * @author Jeremiah Garcia
 * @version 1.0
*/
public class PomonaTransitSystem{

    // Privated variables for the database connection
    private static final String DB_NAME = "4350";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "joshadmin";
    /**
     * Main method for the Pomona Transit System.
     * @param args The command line arguments.
     * @throws ClassNotFoundException it will throw an exception if the PostgreSQL driver is not found.
     * @throws SQLException it will throw an exception if there is an error connecting to the database.
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Connect to the database, if error, throw exception.
        Connection conn = null;
        try {
        // Open connection to the database
        conn = newPTSConnection(DB_NAME, USERNAME, PASSWORD);
        // Create the tables in the database
        createTables(conn);
        
        













        conn.close();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    /**
    * Connects to the database.
    * 
    * @param dbName The name of the database to connect to.
    * @param username The username of the database.
    * @param password The password of the database.
    * 
    * @throws ClassNotFoundException If the PostgreSQL driver is not found.
    * @throws SQLException If there is an error connecting to the database.
    * 
    * @return The connection object to the database.
    * 
    */
    public static Connection newPTSConnection(String dbName, String username, String password) throws ClassNotFoundException, SQLException {
    	String jdbcUrl = "jdbc:postgresql://localhost:5432/" + dbName;
    	
    	// Register the PostgreSQL driver
        Class.forName("org.postgresql.Driver");

        // Connect to the database
        return DriverManager.getConnection(jdbcUrl, username, password);
    }

    /**
    *
    * Creates the tables in the database. If tables already exist, it will not create the tables again.
    *
    * @param connection Connection object to the database.
    *
    */
    @SuppressWarnings("unused")
    public static void createTables(Connection connection){
        // Create a statement object to execute SQL queries
        Statement statement = null;
        try{
        statement = connection.createStatement();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
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
                System.out.println("Tables created successfully.");
            }
        catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error creating tables.");
            }
        }
        // Error handling.
        else if (connection == null && statement != null){
            System.out.println("Error creating tables, connection is null.");
        }
        else if (connection != null && statement == null)
            System.out.println("Error creating tables, statement is null.");

        else
            System.out.println("Error creating tables both connection and statement is null.");
        
        if (statement != null) {
            try{
                statement.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Error closing statement.");
        }

    }

    /**
     * Display the schedule of all trips on a given date, depending on start location and destination location.
     * 
     * @param startLoc The starting location of the trip.
     * @param destLoc The destination location of the trip.
     * @param date The date of the trip.
     * @param connection Connection object to the database.
     * 
     * @return The full schedule of the trips on the given date, start locations, and end locations.
     * 
     */
    public static String fullSchedule(String startLoc, String destLoc, String date, Connection connection) {
        // Query the database for the full schedule
        Statement statement = null;
        String query = "";
        ResultSet rs = null;
        String result = "";
    	try{
            statement = connection.createStatement();
            query =
            "SELECT * FROM TripOffering " +
            "WHERE tripNumber IN (SELECT tripNumber FROM Trip WHERE startLocationName = '" + startLoc + "' AND destinationName = '" + destLoc + "') " +
            "AND date = '" + date + "';";
            rs = statement.executeQuery(query);
            result = rs.toString();
            statement.close();
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        if (rs == null) {
            return "No trips found.";
        }
        return result;
    }

    /**
     * Adds a Trip object to the database.
     * 
     * @param trip The trip to be added to the database.
     * 
    */
    public static void addTrip(Trip trip){
        
    }

    /**
     * Deletes a Trip object from the database.
     * 
     * @param trip The trip to be deleted from the database.
     * 
    */
    public static void deleteTrip(Trip trip){
        
    }

    /**
     * Adds a Stop object to the database.
     * 
     * @param stop The stop to be added to the database.
     * 
    */
    public static void addStop(Stop stop){
        
    }

    /**
     * Deletes a Stop object from the database.
     * 
     * @param stop The stop to be deleted from the database.
     * 
    */
    public static void deleteStop(Stop stop){
        
    }

    /**
     * Adds a Driver object to the database.
     * 
     * @param driver The driver to be added to the database.
     * 
    */
    public static void addDriver(Driver driver){
    }

    /**
     * Deletes a Driver object from the database.
     * 
     * @param driver The driver to be deleted from the database.
     * 
    */
    public static void deleteDriver(Driver driver){

    }

    /**
     * Adds a Bus object to the database.
     * 
     * @param bus The bus to be added to the database.
     * 
    */
    public static void addBus(Bus bus){
        
    }

    /**
     * Deletes a Bus object from the database.
     * 
     * @param bus The bus to be deleted from the database.
     * 
    */
    public static void deleteBus(Bus bus){

    }

    /**
     * Adds a TripStopInfo object to the database.
     * 
     * @param tripStopInfo The trip stop info to be added to the database.
     * 
    */
    public static void addTripStopInfo(TripStopInfo tripStopInfo){
        
    }

    /**
     * Deletes a TripStopInfo object from the database.
     * 
     * @param tripStopInfo The trip stop info to be deleted from the database.
     * 
    */
    public static void deleteTripStopInfo(TripStopInfo tripStopInfo){
        
    }

    /**
     * Adds a TripOffering object to the database.
     * 
     * @param tripOffering The trip offering to be added to the database.
     * 
    */
    public static void addTripOffering(TripOffering tripOffering){
        
    }

    /**
     * Deletes a TripOffering object from the database.
     * 
     * @param tripOffering The trip offering to be deleted from the database.
     * 
    */
    public static void deleteTripOffering(TripOffering tripOffering){
        
    	
    }
    
    /**
     * Changes the driver of a trip offering.
     * 
     * @param tripOffering The trip offering to change the driver of.
     * @param driver The driver to change the trip offering to.
     * 
    */
    public static void changeDriver(TripOffering tripOffering, Driver driver){
        

    }

    /**
     * Changes the bus of a trip offering.
     * 
     * @param tripOffering The trip offering to change the bus of.
     * @param bus The bus to change the trip offering to.
     * 
    */
    public static void changeBus(TripOffering tripOffering, Bus bus){
        

    }

    /**
     * Gets the stops of a given TripStopInfo object
     * 
     * @param tripStopInfo The trip stop info to get the stops of.
     * 
     * @return The stops of the given TripStopInfo object.
     * 
    */
    public static String tripStops(TripStopInfo tripStopInfo){

        return "";
    }

    /**
     * Gets the schedule of a Driver object for a given date.
     * 
     * @param driver The driver to get the schedule of.
     * @param date The date to use on the driver.
     * 
     * @return The schedule of the driver for the given date.
     * 
    */
    public static String driverScheduleForDate(Driver driver, String date){
        String output = "";
    	
    	return output;
    }

    /**
     * Adds a drive from a ActualTripStopInfo object.
     * 
     * @param actualTripStopInfo The actual trip stop info to add the drive to.
    */
    public static void addDrive(ActualTripStopInfo actualTripStopInfo){
        
    }

    /**
     * Records the actual date of a given TripOffering object. It specifies by its key (tripNumber, date, scheduledStartTime).
     * The actual data is stored in the ActualTripStopInfo table.
     * 
     * @param tripOffering The trip offering to record the actual data of.
     *
    */
    public static void recordActualData(TripOffering tripOffering){
        
    }

}
