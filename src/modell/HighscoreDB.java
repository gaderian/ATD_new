package modell;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class: HighscoreDB
 * Class that connects to a database in which it can store a users highscore in a table via SQL and also be able to
 * read that table and return the information
 * <p>
 * Created by id12jzn on 2015-12-11.
 */
public class HighscoreDB {

    private Connection conn;
    private Statement st = null;
    private String user = "SA";
    private String password = "";
    private User u;
    final private String sqlCreateTable = "CREATE TABLE HIGHSCORE(id INTEGER IDENTITY, userName VARCHAR(30), level " +
            "VARCHAR(30), score integer)"; // SQL code used to create a table
    final private String sqlDeleteTable = "DROP TABLE HIGHSCORE"; // SQL code used to delete a table
    private String url = "jdbc:hsqldb:hsql://itchy.cs.umu.se:28282/highscore"; // URL to where the database is located
    ArrayList<String> highscores = new ArrayList<>();

    public HighscoreDB(User u) {
        this.u = u;
        connect();
        //delete();
        //setup();
    }

    public HighscoreDB() {
        connect();
        //delete();
        //setup();
    }

    /**
     * Connects to a certain SQL server using the hsqldb drivers
     */
    public void connect() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            conn = DriverManager.getConnection(url, user, password);
            st = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("ERROR: Connection to SQL server failed");
            e.printStackTrace();
            return;
        } catch (Exception e) {
            System.out.println("ERROR: failed to load HSQLDB JDBC driver.");
            e.printStackTrace();
        }
    }

    /**
     * Creates the SQL table for the database, should only have to run this once and the table is created
     */
    public void setup() {
        try {
            st.execute(sqlCreateTable);
        } catch (SQLException e) {
            System.err.println("Something went horrible wrong during setup");
            e.printStackTrace();
        }
    }

    /**
     * Deletes the SQL table for the database, only run this when you want to clear databasse
     */
    public void delete() {
        try {
            st.execute(sqlDeleteTable);
        } catch (SQLException e) {
            System.err.println("Something went horrible wrong during setup");
            e.printStackTrace();
        }
    }

    /**
     * Shuts down the connection to the SQL server
     *
     * @throws SQLException
     */
    public void shutdown() throws SQLException {
        Statement st = conn.createStatement();
        st.execute("SHUTDOWN");
        conn.close();
    }

    /**
     * Adds a new user with a score to the highscore table
     */
    public void addUser(String map) {
        String userName = u.getUserName();
        int score = u.getScore();
        // TODO get map from user instead

        String sql = "INSERT INTO HIGHSCORE(userName, level, score) " + "VALUES ('" + userName + "', '" + map + "', " +
                "'" + score + "')";

        try {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Couldn't create add user");
            e.printStackTrace();
        }
    }

    /**
     * Gets the data from the highscore table on the sql server and returns it in an ArrayList
     */
    public ArrayList getData() {
        String sql = "SELECT * FROM HIGHSCORE ORDER BY score DESC LIMIT 5";
        ResultSet rs;
        highscores.clear();

        try {
            rs = st.executeQuery(sql);

            while (rs.next()) {
                String userName = rs.getString("userName");
                String level = rs.getString("level");
                int score = rs.getInt("score");
                /* TODO pad strings with spaces until fixed length */
                highscores.add(userName + "   " + level + "   " + score + "   ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return highscores;
    }
}
