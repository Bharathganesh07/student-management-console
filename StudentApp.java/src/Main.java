import java.sql.*;
import java.util.Scanner;

public class Main {

    static final String URL =
            "jdbc:mysql://localhost:3306/studentdb?useSSL=false&serverTimezone=UTC";
    static final String USER = System.getenv("DB_USER");
    static final String PASS = System.getenv("DB_PASS");
    // change later

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        while (true) {
            System.out.println("\n--- Student Management (MySQL) ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Update Student");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudent();
                case 4 -> deleteStudent();
                case 5 -> updateStudent();
                case 6 -> {
                    System.out.println("Bye");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }

        }
    }

    static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    static void addStudent() throws Exception {

        System.out.print("Enter Name: ");
        String name = sc.next();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        Connection con = getConnection();

        PreparedStatement ps =
                con.prepareStatement("INSERT INTO students(name, age) VALUES(?,?)");

        ps.setString(1, name);
        ps.setInt(2, age);


        ps.executeUpdate();
        con.close();

        System.out.println("Student Added");
    }


    static void viewStudents() throws Exception {
        Connection con = getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM students");

        while (rs.next()) {
            System.out.println(
                    rs.getInt("id") + " " +
                            rs.getString("name") + " " +
                            rs.getInt("age"));
        }

        con.close();
    }

    static void searchStudent() throws Exception {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        Connection con = getConnection();
        PreparedStatement ps =
                con.prepareStatement("SELECT * FROM students WHERE id=?");

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println(
                    rs.getInt("id") + " " +
                            rs.getString("name") + " " +
                            rs.getInt("age"));
        } else {
            System.out.println("Student not found");
        }

        con.close();
    }
    static void updateStudent() throws Exception {

        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();

        System.out.print("Enter New Name: ");
        String name = sc.next();

        System.out.print("Enter New Age: ");
        int age = sc.nextInt();

        Connection con = getConnection();

        PreparedStatement ps =
                con.prepareStatement("UPDATE students SET name=?, age=? WHERE id=?");

        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setInt(3, id);

        int res = ps.executeUpdate();

        if(res > 0)
            System.out.println("Student Updated");
        else
            System.out.println("Student not found");

        con.close();
    }

    static void deleteStudent() throws Exception {
        System.out.print("Enter ID to Delete: ");
        int id = sc.nextInt();

        Connection con = getConnection();
        PreparedStatement ps =
                con.prepareStatement("DELETE FROM students WHERE id=?");

        ps.setInt(1, id);

        int res = ps.executeUpdate();

        if (res > 0)
            System.out.println("Student Deleted");
        else
            System.out.println("Student not found");

        con.close();
    }
}
