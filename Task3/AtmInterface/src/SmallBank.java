
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SmallBank {
    private static final String url = "jdbc:mysql://localhost:3306/bank";
    private static final String name = "root";
    private static final String pass = "@shutosH10";

    public static void main(String[] args) {

        try {

            Connection con = DriverManager.getConnection(url, name, pass);
            Scanner sc = new Scanner(System.in);
            System.out.println("Select the following option : ");
            System.out.println("1: Create New Account ");
            System.out.println("2: Debit Money ");
            System.out.println("3: Credit Money ");
            System.out.println("4: Check Account Status ");
            System.out.println("5: Exit ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createAccount(con);

                    break;
                case 2:
                    debitMoney(con);

                    break;
                case 3:
                    creditMoney(con);

                    break;
                case 4:
                    checkStatus(con);

                    break;
                case 5:
                    System.out.println("Thank You for using Bank ... ");
                    sc.close();
                    con.close();

                    break;
                default:
                    System.out.println("Insufficient Choice : ");
                    sc.close();
                    return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static void createAccount(Connection conn) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Your Name : ");
            String name = sc.next();
            for (char c : name.toCharArray()) {
                if (Character.isDigit(c) && Character.isLetter(c) && Character.isWhitespace(c)) {
                    System.out.println("Invalid Name ");
                    sc.close();
                    conn.close();
                    return;
                }
            }
            String query = "Insert into accounts(name)values(?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            int r = ps.executeUpdate();
            if (r > 0)
                System.out.println("Account Created ... ");
            else
                System.out.println("Account Not created ... ");
            conn.close();
            sc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void debitMoney(Connection conn) {
        try {
            conn.setAutoCommit(false);
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Your Account Number : ");
            int accont_number = sc.nextInt();
            System.out.print("Enter Your Money You Want to debit : ");
            double balance = sc.nextDouble();
            if (balance < 0) {
                System.out.println("Wrong Amount ...");
                sc.close();
                conn.close();
                return;
            }
            String query = "Update accounts set balance=balance-? where account_number=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1, balance);
            ps.setInt(2, accont_number);
            ps.executeUpdate();
            if (isSufficient(conn, accont_number, balance)) {
                conn.commit();
                System.out.println("Transcation Successful ...");

            } else {
                conn.rollback();
                System.out.println("Insufficient balance transcation failed ... ");
            }

            sc.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static boolean isSufficient(Connection conn, int account_no, double amount) {
        try {
            String Query = "select balance from accounts where account_number=?";
            PreparedStatement ps = conn.prepareStatement(Query);
            ps.setInt(1, account_no);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double cur_balance = rs.getDouble("balance");
                if (amount > cur_balance)
                    return false;
                else
                    return true;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    static void creditMoney(Connection conn) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Your Account Number : ");
            int accont_number = sc.nextInt();
            System.out.print("Enter Your Money You Want to credit : ");
            double balance = sc.nextDouble();
            String query = "Update accounts set balance=balance+? where account_number=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1, balance);
            ps.setInt(2, accont_number);
            int r = ps.executeUpdate();
            if (r > 0)
                System.out.println("Money creadited Successfully ... ");
            else
                System.out.println("Money is not credited ...");

            sc.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static void checkStatus(Connection conn) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Your Account Number : ");
            int accont_number = sc.nextInt();

            String query = "select * from accounts where account_number=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, accont_number);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Account_Number : " + rs.getInt("account_number"));
                System.out.println("Name : " + rs.getString("name"));
                System.out.println("Balance : " + rs.getDouble("balance"));
            }

            sc.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
