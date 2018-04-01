package cybermafia;

public class Player {

    private Crimes crime;
    private int cash, bank;
    private String userName, password;
    private static String currentUser;

    public Player(){
        userName = "";
        password = "";
    }

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(String currentUser) {
        Player.currentUser = currentUser;
    }

    public void deposit(int value){

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
