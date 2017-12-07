import javax.crypto.Cipher;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;


public class RegisterNewUser {
    private DBConnect connect;
    public RegisterNewUser(String userName, String password, String email){
        connect = new DBConnect("UserAdmin", "%sSGRQq+@9AfP9_d%B4vGmeg2tmM22PkVNZ72m3@L+mGD4FPSE3&DWwf&EjbBvyZedzQ9T6Va3#JD-HWh*h@*nJLjMLcJtwG-LX*xNV5nQdvWvRxyQK_xZXCjYH%T6Dd");
        insertNewUserIntoPlayerTable(userName, password, email);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public void insertNewUserIntoPlayerTable(String newUserName, String password, String email){
        String table = "Player";
        String sha256hex = "";
        String salt = "mXkHy_h@7=qdA7aR";
        String saltplusspass = salt + password;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(
                    saltplusspass.getBytes(StandardCharsets.UTF_8)
            );
            sha256hex = bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException err) {
            err.getStackTrace();
        }


        String sqlInsertStatement = "INSERT INTO " + table + "(username, password, email, wallet, bank, bullets) " +
                                        "VALUES ('" + newUserName + "', '" + sha256hex + "', '" + email + "', 10000, 0, 0 );";
        connect.insertStatement(sqlInsertStatement);
    }
}
