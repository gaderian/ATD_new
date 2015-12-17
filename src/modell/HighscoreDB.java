package modell;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class: HighscoreDB
 * Class that connects to a database in which it can store a users highscore in a table via SQL and also be able to
 * read that table and return the information
 *
 * Created by Joakim on 2015-12-11.
 */
public class HighscoreDB {

    private Connection conn;
    private Statement st = null;
    private String user = "SA";
    private String password = "";
    private User u;
    final private String sqlCreateTable = "CREATE TABLE HIGHSCORE(id INTEGER IDENTITY, userName VARCHAR(30), level VARCHAR(30), score integer)";
    final private String sqlDeleteTable = "DROP TABLE HIGHSCORE";
    //private String url = "jdbc:hsqldb:hsql://localhost";
    //private String url = "jdbc:hsqldb:file:highscore";
    private String url = "jdbc:hsqldb:hsql://itchy.cs.umu.se:28282/highscore";
    ArrayList<String> highscores = new ArrayList<>();

    public HighscoreDB(User u){

        this.u = u;
        connect();

        //setup();
        //addUser(u);
        //getData();

    }

    public HighscoreDB() {

        connect();
        //delete();

        //setup();

        //addUser(u);
        //getData();
        //testAddUser();

    }

    /**
     * Connects to a certain SQL server
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

        // db writes out to files and performs clean shuts down
        // otherwise there will be an unclean shutdown
        // when program ends
        st.execute("SHUTDOWN");
        conn.close();    // if there are no other open connection
    }

    /**
     * Adds a new user with a score to the highscore table
     *
     */
    public void addUser(String map) {
        /*
        try {
            if (psAddUser == null) {
                psAddUser = conn.prepareStatement(sqlAddUser);
            }
            psAddUser.setString(1, u.getUserName());
            psAddUser.setInt(2, u.getScore());
            psAddUser.setString(3, u.getMap());
            psAddUser.setString(4, u.getCurrentDate());
            psAddUser.executeUpdate();

            */
        String userName = u.getUserName();
        int score = u.getScore();
        // TODO get map from user instead
        //String map = u.getMap();

        String sql = "INSERT INTO HIGHSCORE(userName, level, score) " + "VALUES ('"+userName+"', '"+map+"', '"+score+"')";

        try {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Couldn't create add user");
            e.printStackTrace();
        }
    }

    public void testAddUser() {
        String sql = "INSERT INTO HIGHSCORE(userName, level, score) " + "VALUES ('"+"Pekka"+"', '"+"Joy"+"', '"+"2"+"')";
        try {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Couldn't create add user");
            e.printStackTrace();
        }

    }

    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    /**
     * Gets the data from the highscore table on the sql server
     */
    public ArrayList getData() {
        String sql = "SELECT * FROM HIGHSCORE ORDER BY score DESC LIMIT 5";
        ResultSet rs = null;
        highscores.clear();
        try {
            rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String userName = rs.getString("userName");
                String level = rs.getString("level");
                int score = rs.getInt("score");


                //userName = padRight(userName, 10);
                //level = padRight(level, 10);
                //date = padRight(date, 10);

                //userName = String.format("%15d", userName);




                System.out.print("ID " + id);
                System.out.print(", User: " + userName);
                System.out.print(", Level: " + level);
                System.out.print(", Score: " + score);



                /* TODO pad strings with spaces until fixed length */
                //userName = String.format("%1$-" + 10 + "s", userName)+"S";


                //highscores.add("User: "+userName+" Wave: "+wave+" Level: "+level+" Date: "+date);
                highscores.add(userName+"   "+level+"   "+score+"   ");


                //singleHighscore.clear();

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    return highscores;
    }

    public String formatString(String s, int score) {
        String scoreString = null;

        int size = s.length() + new Integer(score).toString().length();

        if(size < 25){
            int dots = 25 - (s.length() + new Integer(score).toString().length());
            scoreString = new String(s);
            for(int i = 0; i < dots; i++){
                scoreString = new String(scoreString+".");

            }
            scoreString = new String(scoreString+new Integer(score).toString());
        }


        return scoreString;
    }
}
