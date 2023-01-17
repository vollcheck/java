import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;


public class JDBC {
    public static void main(String[] args) {
        try {
            String connInfo = "jdbc:sqlite:sample.db";
            Connection connection = DriverManager.getConnection(connInfo, "root", "");
            Statement statement = connection.createStatement();

            statement.executeUpdate("drop table if exists osoba");
            statement.executeUpdate("create table osoba (id integer, imie string, nazwisko string, email string)");
            statement.executeUpdate("insert into osoba (imie, nazwisko, email) values ('leo', 'messi', 'leo@messi.ag')");
            ResultSet resultset = statement.executeQuery("select * from osoba");
            while (resultset.next()) {
                System.out.println(resultset.getString("nazwisko"));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
