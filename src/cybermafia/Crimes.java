package cybermafia;

public class Crimes{
    private int cash, randMin, randMax;
    private RandomValue randHackCrime;

    public Crimes(int randMin, int randMax){
        this.randMin = randMin;
        this.randMax = randMax;
    }

    public int executeCrime(){
        return randHackCrime.getRandomValue(randMin, randMax);
    }
}
