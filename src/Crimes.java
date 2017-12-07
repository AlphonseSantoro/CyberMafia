
public class Crimes {
    private int cash, randMin, randMax;
    private RandomValue randHackCrime;

    public Crimes(int randMin, int randMax){
        this.randHackCrime = new RandomValue(randMin, randMax);
    }

    public int hackBankAccount(){
        return randHackCrime.getRandomValue();
    }

    public Car remoteStealCar(){
        Car car = new Car();
        return car;
    }

    public void setMaxMinRandHackCrime(int randMin, int randMax){
        randHackCrime.setMin(randMin);
        randHackCrime.setMax(randMax);
    }
}
