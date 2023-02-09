import java.sql.Statement;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class Script1{
public static void main(String[] args) {
    Connection connection = null;
    try {
        // create a database connection
        connection = DriverManager.getConnection("jdbc:sqlite:babajava.db");
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30); // set timeout to 30 sec.

        statement.executeUpdate("drop table if exists quotes");
        statement.executeUpdate("create table quotes (id integer primary key autoincrement, quote string)");
        try {
            Scanner scanner = new Scanner(new File("src/quotes.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                statement.executeUpdate("insert into quotes (quote) values ('"+line+"')");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ResultSet rs = statement.executeQuery("select * from quotes");
        while (rs.next()) {
            // read the result set
            System.out.println("quotes = " + rs.getString("quote"));
            System.out.println("id = " + rs.getInt("id"));
        }
    } catch (SQLException e) {
        // if the error message is "out of memory",
        // it probably means no database file is found
        System.err.println(e.getMessage());
    } finally {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }
}
}