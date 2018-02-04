public class main {

    public static void main(String[] args){
        //Player player = new Player();
        //player.deposit(500);
        IPHandling ip = new IPHandling();
        UserHandling regUser = new UserHandling("TestUser3", "123456", "test@someemail.com", ip.generateIPv7());
        //Gui gui = new Gui();
        //DBConnect con = new DBConnect();
        //UserHandling user = new UserHandling("TestUser3", "123456");

        //RandomValue randomLetters = new RandomValue();
        //System.out.print(ip.generateIPv7());


    }
}
