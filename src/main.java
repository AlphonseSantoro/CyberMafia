public class main {

    public static void main(String[] args){
        //Player player = new Player();
        //player.deposit(500);
        GenerateIP ip = new GenerateIP();
        UserHandling regUser = new UserHandling("TestUser3", "123456", "test@someemail.com", ip.getRandomIP());
        //Gui gui = new Gui();
        //DBConnect con = new DBConnect();
        UserHandling user = new UserHandling("TestUser3", "123456");


    }
}
