import java.sql.*;

public class DBConnect {
    static int security = 0;
    private Statement st;
    private ResultSet set;
    private static final String JDBC_URL = "jdbc:mysql://145.14.144.155:3306/";
    private static final String JDBC_DB = "id6887252_hallo";
    private static final String JDBC_USER = "id6887252_hallo";
    private static final String JDBC_PASSWORD = "hallo";
    private static Connection connection;

    public static void connect() {
        try {
            connection = DriverManager.getConnection(
                    JDBC_URL + JDBC_DB + "?useSSL=false",
                    JDBC_USER,
                    JDBC_PASSWORD);
            System.out.println("Verbinding is succesvol!");
        } catch (SQLException e) {
            System.out.println("Verbinding error: " + e);
        }
    }


    public boolean getData(String userMail, String userPassword) {
        if (security < 3) {
            security++;
            try {
                String query = "select * from userdata";
                st = connection.createStatement();
                set = st.executeQuery(query);
                while (set.next()) {
                    String mail = set.getString("mail");
                    String password = set.getString("password");
                    if (userMail.equalsIgnoreCase(mail) && userPassword.equals(password)) {
                        System.out.println("Je hebt toegang verkregen!");
                        return true;
                    }

                    if (userMail.equalsIgnoreCase(mail) && !userPassword.equals(password)) {
                        System.out.println("Wachtwoord komt niet overeen!");
                        return false;
                    }

                }
                System.out.println("Geen account gevonden!");
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        System.out.println("Te vaak foutief ingelogd");
        return false;
    }

    public boolean comparePasswords(String userPassword1, String userPassword2) {
        return userPassword1.equals(userPassword2);
    }

    public boolean mailCheck(String userMail) {
        try {
            String query = "select mail from userdata where mail like " + userMail;
            st = connection.createStatement();
            set = st.executeQuery(query);
            return !set.next();
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    public boolean accountInsert(String userPassword1, String userMail) {
        try {
            String query = "INSERT INTO `userdata` (`id`, `mail`, `password`) VALUES (NULL, '" + userMail + "', '" + userPassword1 + "')";
            st = connection.createStatement();
            set = st.executeQuery(query);

        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }
}
