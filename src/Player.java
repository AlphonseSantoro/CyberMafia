public class Player {

    private Crimes crime;
    private int cash, bank;
    private String userName, password;
    DBConnect connect;

    public Player(){
        userName = "";
        password = "";
        connect = new DBConnect();
    }

    public void deposit(int value){
        String table = "Player";
        String sqlUpdateStatement = "UPDATE " + table +
                "SET bank = bank + " + value + ", wallet - " + value + " WHERE userName = '" + userName + "';";
        connect.updateStatement(sqlUpdateStatement);
    }

    public void depositAll(){
        bank += cash;
        cash = 0;
    }

    public void withdraw(int value){
        bank -= value;
        cash += value;
    }

    public void withdrawAll(){
        cash += bank;
        bank = 0;
    }

    public int getBalanceBank(){
        return bank;
    }

    public int getBalanceCash(){
        return cash;
    }

    public void performCrime(){
        cash += crime.executeCrime();
    }
}
