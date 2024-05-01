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
    private static final String DB_NAME = "CS4350";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "^d5uvNTM2";
    /**
     * Main method for the Pomona Transit System.
     * @param args The command line arguments.
     * @throws ClassNotFoundException it will throw an exception if the PostgreSQL driver is not found.
     * @throws SQLException it will throw an exception if there is an error connecting to the database.
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Connect to the database, if error, throw exception.
        Connection conn = null;
        Statement stmt = null;
        //this is for the delete methods bc a separate statement object saves us the pain of accidentally deleting stuff if the batch is never called for stmt.
        Statement dltstmt = null;

        // Open connection to the database
        conn = newPTSConnection(DB_NAME, USERNAME, PASSWORD);
        // Create the tables in the database

        createTables(conn);
        // Object creation

        // First objects created
        Date date = null;
        Trip trip1 = new Trip(1, "Pomona", "Los Angeles");
        Bus bus1 = new Bus(1, "Greyhound", 2020);
        Driver driver1 = new Driver("John Doe", "123-456-7890");
        Stop stop1 = new Stop(1, "Pomona");
        Stop stop2 = new Stop(2, "Los Angeles");
        TripStopInfo tripStopInfo1 = new TripStopInfo(trip1, stop2, 1, "30 minutes");
        date = Date.valueOf("2024-04-28");
        TripOffering tripOffering1 = new TripOffering(trip1, date ,"08:00:00", "08:30:00", driver1, bus1);
        ActualTripStopInfo actualTripStopInfo1 = new ActualTripStopInfo(tripOffering1, stop2, "08:05:32", "08:37:56", 19, 0);

        //Add the 1st objects to the database
        stmt = conn.createStatement();
        addTrip(trip1, conn, stmt);
        addBus(bus1, conn, stmt);
        addDriver(driver1, conn, stmt);
        addStop(stop1, conn, stmt);
        addStop(stop2, conn, stmt);
        addTripStopInfo(tripStopInfo1, conn, stmt);
        addTripOffering(tripOffering1, conn, stmt);
        addDrive(actualTripStopInfo1, conn, stmt);
        stmt.executeBatch();
        System.out.println("Ran batch 1. 1st object added.");

        // Second objects created
        Trip trip2 = new Trip(2, "Ontario", "Pomona");
        Bus bus2 = new Bus(2, "School Bus", 2016);
        Driver driver2 = new Driver("Jane Doe", "098-765-4321");
        Stop stop3 = new Stop(3, "Ontario");
        TripStopInfo tripStopInfo2 = new TripStopInfo(trip2, stop2, 2, "1 hour");
        TripOffering tripOffering2 = new TripOffering(trip2, date ,"10:00:00", "11:00:00", driver2, bus2);
        ActualTripStopInfo actualTripStopInfo2 = new ActualTripStopInfo(tripOffering2, stop2, "10:07:39", "11:10:57", 13, 22);

        // Add the 2nd objects to the database
        stmt = conn.createStatement();
        addTrip(trip2, conn, stmt);
        addBus(bus2, conn, stmt);
        addDriver(driver2, conn, stmt);
        addStop(stop3, conn, stmt);
        addTripStopInfo(tripStopInfo2, conn, stmt);
        addTripOffering(tripOffering2, conn, stmt);
        addDrive(actualTripStopInfo2, conn, stmt);
        stmt.executeBatch();
        stmt = null;
        System.out.println("Ran batch 2. 2nd object added.");

        // Third objects created
        Trip trip3 = new Trip(3, "Los Angeles", "Pomona");
        TripStopInfo tripStopInfo3 = new TripStopInfo(trip3, stop1, 3, "45 minutes");
        TripOffering tripOffering3 = new TripOffering(trip3, date ,"12:00:00", "12:45:00", driver1, bus1);
        ActualTripStopInfo actualTripStopInfo3 = new ActualTripStopInfo(tripOffering3, stop1, "12:03:27", "12:42:06", 31, 16);

        // Add the 3rd objects to the database
        stmt = conn.createStatement();
        addTrip(trip3, conn, stmt);
        addTripStopInfo(tripStopInfo3, conn, stmt);
        addTripOffering(tripOffering3, conn, stmt);
        addDrive(actualTripStopInfo3, conn, stmt);
        stmt.executeBatch();
        System.out.println("Ran batch 3. 3rd object added.");

        changeDriver(tripOffering3, driver2, conn, stmt);
        changeBus(tripOffering3, bus2, conn, stmt);

        deleteTripOffering(tripOffering3, conn, stmt);
        // Add more objects here maybe change the date, times, destinations, etc.

        System.out.print("Trip Stop Info for 3.");
        System.out.println(tripStopInfo3.toString());

        System.out.println();
        System.out.print("Driver 1 Schedule for 2024-04-28.");
        driverScheduleForDate(driver1, "2024-04-28", conn);

        deleteBus(bus1, conn, stmt);

        recordActualData(actualTripStopInfo1, conn, stmt);
    
        //Display the full schedule of trips given startlocation, name, and date
        fullSchedule("Pomona", "Los Angeles", "2024-04-28", conn);

        stmt.close();
        conn.close();
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
    * @throws SQLException If there is an error creating the tables.
    */
    public static void createTables(Connection connection) throws SQLException{
        // Create a statement object to execute SQL queries
        Statement statement = null;
        statement = connection.createStatement();
        // Check if the connection and statement are not null
        if (connection != null && statement != null) {
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
            "stopNumber INT PRIMARY KEY," +
            "stopAddress VARCHAR(255) NOT NULL" +
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
            "stopNumber INT NOT NULL," +
            "sequenceNumber INT NOT NULL," +
            "drivingTime VARCHAR(255) NOT NULL," +
            "FOREIGN KEY (tripNumber) REFERENCES Trip(tripNumber)," +
            "FOREIGN KEY (stopNumber) REFERENCES Stop(stopNumber)" +
            ");";

            // Actual Trip Stop Info Table
            String createActualTripStopInfoTable =
            "CREATE TABLE IF NOT EXISTS ActualTripStopInfo (" +
            "tripNumber INT NOT NULL," +
            "date DATE NOT NULL," +
            "scheduledStartTime TIME NOT NULL," +
            "scheduledArrivalTime TIME NOT NULL,"+
            "stopNumber INT NOT NULL," +
            "actualStartTime TIME NULL," +
            "actualArrivalTime TIME NULL," +
            "numberOfPassengerIn INT NULL," +
            "numberOfPassengerOut INT NULL," +
            "PRIMARY KEY (tripNumber, date, scheduledStartTime, stopNumber)," +
            "FOREIGN KEY (tripNumber) REFERENCES Trip(tripNumber)," +
            "FOREIGN KEY (stopNumber) REFERENCES Stop(stopNumber)" +
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
        if (statement != null) {
            statement.close();
        }
        else{
            System.out.println("Statement is null. Tables were not created.");
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
     * @throws SQLException If there is an error executing the SQL query.
     * 
     */
    public static void fullSchedule(String startLoc, String destLoc, String date, Connection connection) throws SQLException{
        // Query the database for the full schedule
        Statement statement = null;
        ResultSet rs = null;
        ResultSetMetaData metaData = null;

        String query = "";
        statement = connection.createStatement();
        query =
        "SELECT * FROM TripOffering " +
        "WHERE tripNumber IN (SELECT tripNumber FROM Trip WHERE startLocationName = '" + startLoc + "' AND destinationName = '" + destLoc + "') " +
        "AND date = '" + date + "';";

        // For some reason rs doesnt return a string so we have to do all this madness...
        rs = statement.executeQuery(query);
        metaData = rs.getMetaData();

        System.out.println();
        System.out.println("Schedule Info For " + startLoc + " " + date);
        for(int i = 1; i <= metaData.getColumnCount(); i++){
            System.out.print(metaData.getColumnName(i) + "\t");
        }

        System.out.println();
        while (rs.next()) {
            for(int i = 1; i <= metaData.getColumnCount(); i++){
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
            
        }
        statement.close();

    }

    /**
     * Adds a Trip object to the database.
     * 
     * @param trip The trip to be added to the database.
     * @param conn The connection object to the database.
     * @param stmt The statement object to execute SQL queries.
     * 
     * @throws SQLException If there is an error executing the SQL query.
     * 
    */
    public static void addTrip(Trip trip, Connection conn, Statement stmt) throws SQLException{
        String query = "INSERT INTO Trip (tripNumber, startLocationName, destinationName) VALUES (" + 
        trip.getTripNumber() + ", '" + 
        trip.getStartLocationName() + "', '" + 
        trip.getDestinationName() + "');";

        stmt.addBatch(query);
        System.out.println("\naddTrip query added to batch:" + trip.toString() +"\n");
    }

    /**
     * Deletes a Trip object from the database.
     * 
     * @param trip The trip to be deleted from the database.
     * @param conn The connection object to the database.
     * @param dltstmt The statement object to execute 'delete' SQL queries.
     * 
     * @throws SQLException If there is an error executing the SQL query.
     * 
    */
    public static void deleteTrip(Trip trip, Connection conn, Statement dltstmt) throws SQLException{
        String query = "DELETE FROM Trip WHERE tripNumber = " + 
        trip.getTripNumber() + ";";

        dltstmt.addBatch(query);
        System.out.println("\nTrip deleted:" + trip.toString() +"\n");
    }

    /**
     * Adds a Stop object to the database.
     * 
     * @param stop The stop to be added to the database.
     * @param conn The connection object to the database.
     * @param stmt The statement object to execute SQL queries.
     * 
     * @throws SQLException If there is an error executing the SQL query.
     * 
    */
    public static void addStop(Stop stop, Connection conn, Statement stmt) throws SQLException{
        String query = "INSERT INTO Stop (stopNumber, stopAddress) VALUES (" + stop.getStopNumber() + ", '" + stop.getStopAddress() + "');";

        stmt.addBatch(query);
        System.out.println("\naddStop query added to batch:"+ stop +"\n");
        
    }

    /**
     * Deletes a Stop object from the database.
     * 
     * @param stop The stop to be deleted from the database.
     * @param conn The connection object to the database.
     * @param dltstmt The statement object to execute 'delete' SQL queries.
     * 
     * @throws SQLException If there is an error executing the SQL query.
    */
    public static void deleteStop(Stop stop, Connection conn, Statement dltstmt)throws SQLException{
        String query = "DELETE FROM Stop WHERE stopNumber = " + stop.getStopNumber() + ";";

        dltstmt.addBatch(query);
        System.out.println("\nStop deleted:" + stop +"\n");
    }

    /**
     * Adds a Driver object to the database.
     * 
     * @param driver The driver to be added to the database.
     * @param conn The connection object to the database.
     * @param stmt The statement object to execute SQL queries.
     * 
     * @throws SQLException If there is an error executing the SQL query.
     * 
    */
    public static void addDriver(Driver driver, Connection conn, Statement stmt)throws SQLException{
        String query = "INSERT INTO Driver (driverName, driverTelephoneNumber) VALUES ('" + 
        driver.getDriverName() + "', '" + driver.getDriverTelephoneNumber() + "');";

        stmt.addBatch(query);
        System.out.println("\naddDriver query added to batch:" + driver +"\n");
    }

    /**
     * Deletes a Driver object from the database.
     * 
     * @param driver The driver to be deleted from the database.
     * @param conn The connection object to the database.
     * @param dltstmt The statement object to execute 'delete' SQL queries.
     * 
     * @throws SQLException If there is an error executing the SQL query.
     * 
    */
    public static void deleteDriver(Driver driver, Connection conn, Statement dltstmt) throws SQLException{
        String query = "DELETE FROM Driver WHERE driverName = '" + driver.getDriverName() + "';";

        dltstmt.addBatch(query);
        System.out.println("\nDriver deleted:" + driver.toString());
    }

    /**
     * Adds a Bus object to the database.
     * 
     * @param bus The bus to be added to the database.
     * @param conn The connection object to the database.
     * @param stmt The statement object to execute SQL queries
     * 
     * @throws SQLException If there is an error executing the SQL query.
     * 
    */
    public static void addBus(Bus bus, Connection conn, Statement stmt) throws SQLException{
        String query = "INSERT INTO Bus (busID, model, year) VALUES (" + bus.getBusID() + ", '" + bus.getModel() + "', " + bus.getYear() + ");";

        stmt.addBatch(query);
        System.out.println("\naddBus query added to batch:" + bus.toString() +"\n");
    }

    /**
     * Deletes a Bus object from the database.
     * 
     * @param bus The bus to be deleted from the database.
     * @param conn The connection object to the database.
     * @param dltstmt The statement object to execute SQL queries.
     * 
     * @throws SQLException If there is an error executing the SQL query.
     * 
    */
    public static void deleteBus(Bus bus, Connection conn, Statement dltstmt)throws SQLException{
        String query = "DELETE FROM Bus WHERE busID = " + bus.getBusID() + ";";

        dltstmt.addBatch(query);
        System.out.println("\nBus deleted:" + bus.toString() +"\n");
    }

    /**
     * Adds a TripStopInfo object to the database.
     * 
     * @param tripStopInfo The trip stop info to be added to the database.
     * @param conn The connection object to the database.
     * @param stmt The statement object to execute SQL queries.
     * 
     * @throws SQLException If there is an error executing the SQL query.
     * 
    */
    public static void addTripStopInfo(TripStopInfo tripStopInfo, Connection conn, Statement stmt)throws SQLException{
        String query = "INSERT INTO TripStopInfo (tripNumber, stopNumber, sequenceNumber, drivingTime) VALUES (" + 
        tripStopInfo.getTrip().getTripNumber() + ", " + 
        tripStopInfo.getStop().getStopNumber() + ", " + 
        tripStopInfo.getSequenceNumber() + ", '" + 
        tripStopInfo.getDrivingTime() + "');";
        
        stmt.addBatch(query);
        System.out.println("\naddTripStopInfo query added to batch:" + tripStopInfo.toString()+ "\n");
    }

    /**
     * Deletes a TripStopInfo object from the database.
     * 
     * @param tripStopInfo The trip stop info to be deleted from the database.
     * @param conn The connection object to the database.
     * @param dltstmt The statement object to execute 'delete' SQL queries.
     * 
     * @throws SQLException If there is an error executing the SQL query.
     * 
    */
    public static void deleteTripStopInfo(TripStopInfo tripStopInfo, Connection conn, Statement dltstmt)throws SQLException{
        String query = "DELETE FROM TripStopInfo WHERE tripNumber = " + 
        tripStopInfo.getTrip().getTripNumber() + " AND stopNumber = " + 
        tripStopInfo.getStop().getStopNumber() + ";";

        dltstmt.addBatch(query);
        System.out.println("\nTripStopInfo deleted for:" + tripStopInfo.toString()+"\n");
        
    }

    /**
     * Adds a TripOffering object to the database.
     * 
     * @param tripOffering The trip offering to be added to the database.
     * @param conn The connection object to the database.
     * @param stmt The statement object to execute SQL queries.
     * 
     * @throws SQLException If there is an error executing the SQL query.
     * 
    */
    public static void addTripOffering(TripOffering tripOffering, Connection conn, Statement stmt)throws SQLException{
        // Convert the strings to Time objects
        Time timess = java.sql.Time.valueOf(tripOffering.getScheduledStartTime());
        Time timesa = java.sql.Time.valueOf(tripOffering.getScheduledArrivalTime());

        String query = "INSERT INTO TripOffering (tripNumber, date, scheduledStartTime, scheduledArrivalTime, driverName, busID) VALUES (" + 
        tripOffering.getTrip().getTripNumber() + ", '" + 
        tripOffering.getDate() + "', '" +
        timess + "', '" +
        timesa + "', '" + 
        tripOffering.getDriver().getDriverName() + "', " +
        tripOffering.getBus().getBusID() + ");";

        stmt.addBatch(query);
        System.out.println("\naddTripOffering query added to batch:" + tripOffering.toString() + "\n");
        
    }

    /**
     * Deletes a TripOffering object from the database.
     * 
     * @param tripOffering The trip offering to be deleted from the database.
     * @param conn The connection object to the database.
     * @param dltstmt The statement object to execute 'delete' SQL queries.
     * 
     * @throws SQLException If there is an error executing the SQL query.
     * 
    */
    public static void deleteTripOffering(TripOffering tripOffering, Connection conn, Statement dltstmt)throws SQLException{
        // Convert the string to Time object
        Time timess = java.sql.Time.valueOf(tripOffering.getScheduledStartTime());

        String query = "DELETE FROM TripOffering WHERE tripNumber = " + 
        tripOffering.getTrip().getTripNumber() + " AND date = '" + 
        tripOffering.getDate() + "' AND scheduledStartTime = '" + 
        timess + "';";

        dltstmt.addBatch(query);
        System.out.println("\nTripOffering deleted for:" + tripOffering.toString() + "\n");
    }
    
    /**
     * Changes the driver of a trip offering.
     * 
     * @param tripOffering The trip offering to change the driver of.
     * @param driver The driver to change the trip offering to.
     * @param conn The connection object to the database.
     * @param stmt The statement object to execute SQL queries.
     * 
     * @throws SQLException If there is an error executing the SQL query.
     * 
    */
    public static void changeDriver(TripOffering tripOffering, Driver driver, Connection conn, Statement stmt)throws SQLException{
        //Get the old driver, set the new
        Driver oldDriver = tripOffering.getDriver();
        tripOffering.setDriver(driver);

        String query = "UPDATE TripOffering SET driverName = '" + 
        driver.getDriverName() + "' WHERE tripNumber = " + 
        tripOffering.getTrip().getTripNumber() + " AND date = '" + 
        tripOffering.getDate() + "' AND scheduledStartTime = '" + 
        tripOffering.getScheduledStartTime() + "';";

        stmt.addBatch(query);
        System.out.println("\nDriver changed from:" + oldDriver + "\nTo:" + tripOffering.toString() + "\nFor trip offering:" + tripOffering.toString()+ "\n");
    }

    /**
     * Changes the bus of a trip offering.
     * 
     * @param tripOffering The trip offering to change the bus of.
     * @param bus The bus to change the trip offering to.
     * @param conn The connection object to the database.
     * @param stmt The statement object to execute SQL queries.
     * 
     * @throws SQLException If there is an error executing the SQL query.
     * 
    */
    public static void changeBus(TripOffering tripOffering, Bus bus, Connection conn, Statement stmt)throws SQLException{
        //Get the old bus, set the new
        Bus oldBus = tripOffering.getBus();
        tripOffering.setBus(bus);

        String query = "UPDATE TripOffering SET busID = " + bus.getBusID() + " WHERE tripNumber = " + tripOffering.getTrip().getTripNumber() +
        " AND date = '" + tripOffering.getDate() + "' AND scheduledStartTime = '" + tripOffering.getScheduledStartTime() + "';";

        stmt.addBatch(query);
        System.out.println("\nBus changed from:"+ oldBus + "\nTo:" + tripOffering.toString() + "\nFor trip offering:" + tripOffering.toString()+"\n");
        

    }

    /**
     * Gets the stops of a given TripStopInfo object
     * 
     * @param tripStopInfo The trip stop info to get the stops of.
     * @param conn The connection object to the database.
     * 
     * @throws SQLException If there is an error executing the SQL query.
     * 
    */
    public static void tripStops(TripStopInfo tripStopInfo, Connection conn) throws SQLException{
        Statement stopStmt = null;
        ResultSet rs = null;
        ResultSetMetaData metaData = null;

        String query = "SELECT * FROM Stop WHERE stopNumber = " + tripStopInfo.getStop().getStopNumber() + ";";


        stopStmt = conn.createStatement();
        rs = stopStmt.executeQuery(query);
        metaData = rs.getMetaData();

        for(int i = 1; i <= metaData.getColumnCount(); i++){
            System.out.print(metaData.getColumnName(i) + "\t");
        }
        System.out.println();

        while (rs.next()) {
            for(int i = 1; i <= metaData.getColumnCount(); i++){
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }


        stopStmt.close();
        
    }

    /**
     * Gets the schedule of a Driver object for a given date.
     * 
     * @param driver The driver to get the schedule of.
     * @param date The date to use on the driver.
     * @param conn The connection object to the database.
     * 
     * @throws SQLException If there is an error executing the SQL query.
     * 
    */
    public static void driverScheduleForDate(Driver driver, String date, Connection conn)throws SQLException{
        Statement driverScheduleStmt = null;
        ResultSet rs = null;
        ResultSetMetaData metaData = null;
        Date dateCorrect = Date.valueOf(date);

        String query = "SELECT * FROM TripOffering WHERE driverName = '" + driver.getDriverName() + "' AND date = '" + dateCorrect + "';";

        
        driverScheduleStmt = conn.createStatement();
        rs = driverScheduleStmt.executeQuery(query);
        metaData = rs.getMetaData();

        for(int i = 1; i <= metaData.getColumnCount(); i++){
            System.out.print(metaData.getColumnName(i) + "\t");
        }
        System.out.println();

        while (rs.next()) {
            for(int i = 1; i <= metaData.getColumnCount(); i++){
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }

        driverScheduleStmt.close();
    }

    /**
     * Adds a drive from a ActualTripStopInfo object.
     * 
     * @param actualTripStopInfo The actual trip stop info to add the drive to.
     * @param conn The connection object to the database.
     * @param stmt The statement object to execute SQL queries.
     * 
     * @throws SQLException If there is an error executing the SQL query.
     * 
    */
    public static void addDrive(ActualTripStopInfo actualTripStopInfo, Connection conn, Statement stmt)throws SQLException{
        //Actual Start and Arrival times are converted to Time objects
        Time timeas = java.sql.Time.valueOf(actualTripStopInfo.getActualStartTime());
        Time timeaa = java.sql.Time.valueOf(actualTripStopInfo.getActualArrivalTime());

        String query = "INSERT INTO ActualTripStopInfo (tripNumber, date, scheduledStartTime, scheduledArrivalTime, stopNumber, actualStartTime, actualArrivalTime, numberOfPassengerIn, numberOfPassengerOut) VALUES (" +
        actualTripStopInfo.getTripOffering().getTrip().getTripNumber() + ", '" +
        actualTripStopInfo.getTripOffering().getDate() + "', '" + 
        actualTripStopInfo.getTripOffering().getScheduledStartTime() + "', '" + 
        actualTripStopInfo.getTripOffering().getScheduledArrivalTime() + "', " + 
        actualTripStopInfo.getStop().getStopNumber() + ", '" + 
        timeas + "', '" + 
        timeaa + "', " + 
        actualTripStopInfo.getNumberOfPassengerIn() + ", " + 
        actualTripStopInfo.getNumberOfPassengerOut() + ");";

        stmt.addBatch(query);

        System.out.println("addDrive query added to batch:" + actualTripStopInfo.toString());
        
    }

    /**
     * Records the actual data of a given TripOffering object. It specifies by its key (tripNumber, date, scheduledStartTime).
     * The actual data is stored in the ActualTripStopInfo table.
     * 
     * @param actualTripStopInfo The actual trip stop info to record the data of.
     * @param conn The connection object to the database.
     * @param stmt The statement object to execute SQL queries.
     * 
     * @throws SQLException If there is an error executing the SQL query.
     *
    */
    public static void recordActualData(ActualTripStopInfo actualTripStopInfo, Connection conn, Statement stmt)throws SQLException{

        //Actual Start and Arrival times are converted to Time objects
        Time timeas = java.sql.Time.valueOf(actualTripStopInfo.getActualStartTime());
        Time timeaa = java.sql.Time.valueOf(actualTripStopInfo.getActualArrivalTime());

        String query = "UPDATE ActualTripStopInfo SET actualStartTime = '" + 
        timeas + "', actualArrivalTime = '" + 
        timeaa + "', numberOfPassengerIn = " + 
        actualTripStopInfo.getNumberOfPassengerIn() + ", numberOfPassengerOut = " + 
        actualTripStopInfo.getNumberOfPassengerOut() + " WHERE tripNumber = " + 
        actualTripStopInfo.getTripOffering().getTrip().getTripNumber() + " AND date = '" + 
        actualTripStopInfo.getTripOffering().getDate() + "' AND scheduledStartTime = '" + 
        actualTripStopInfo.getTripOffering().getScheduledStartTime() + "' AND stopNumber = " + 
        actualTripStopInfo.getStop().getStopNumber() + ";";

        stmt.addBatch(query);
        System.out.println("recordActualData query added to batch of actualTripStopInfo:" + actualTripStopInfo.toString());
        
    }

}
