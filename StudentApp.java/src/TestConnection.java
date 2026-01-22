import java.sql.*;

public class TestConnection {
    public static void main(String[] args) throws Exception {

        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/studentdb?useSSL=false&serverTimezone=UTC",
                "root",
                "Bharath1234@"
        );

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM students");

        while (rs.next()) {
            System.out.println(
                    rs.getInt("id") + " " +
                            rs.getString("name") + " " +
                            rs.getInt("age")
            );
        }

        con.close();
    }
}
