import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBConnect {
    private Connection con;
    private ResultSet rs;

    public DBConnect(){

        try {
            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            encryptor.setPassword("");
            Properties prop = new EncryptableProperties(encryptor);
            prop.load(new FileInputStream("data\\cyberMafia.properties"));
            String host = prop.getProperty("dataSource.host");
            String uName = prop.getProperty("dataSource.username");
            String uPass = prop.getProperty("dataSource.password");
            con = DriverManager.getConnection(host, uName, uPass);
        } catch (IOException err) {
            System.out.println(err.getMessage());
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

    public ResultSet selectStatement(String sqlSelectStatement){
        try {
            PreparedStatement selectStatement = con.prepareStatement(sqlSelectStatement);
            rs = selectStatement.executeQuery();
        } catch (SQLException err){
            System.out.println(err.getMessage());
        }
        return rs;
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
