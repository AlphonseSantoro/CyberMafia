import java.sql.*;

public class DBConnect {

    private String host, uName, uPass, table, sqlUpdateStatement;
    private Connection con;

    public DBConnect(String uName, String uPass){

        try {
            String host = "jdbc:mysql://localhost:3306/CyberMafia?allowPublicKeyRetrieval=true";
            //String uName = "MafiaUser";
            //String uPass = "1234";
            // Initialize a connection to the database
            con = DriverManager.getConnection(host, uName, uPass);

            /*  SELECT STATEMENT!!!
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Cars";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                String carID = rs.getString("carID");
                String carName = rs.getString("carName");
                String tier = rs.getString("tier");
                String carMinValue = rs.getString("carMinValue");
                String carMaxValue = rs.getString("carMaxValue");

                System.out.println(carID + " " + carName + " " + tier + " " + carMinValue + " " + carMaxValue);
            }
            */
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

    public void insertStatement(String sqlInsertStatement){
        try {
            PreparedStatement insertStatement = con.prepareStatement(sqlInsertStatement);
            insertStatement.executeUpdate();
        }  catch (SQLException err){
            System.out.println(err.getMessage());
        }
    }

    public void updateStatement(String sqlUpdateStatement){
        try {
            Statement updateStatement = con.createStatement();
            updateStatement.executeUpdate(sqlUpdateStatement);
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }
}
