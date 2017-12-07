import java.util.Random;

public class RandomValue {
    private int max, min, randomValue;
    Random rand;

    public RandomValue(int min, int max){
        setMax(max);
        setMin(min);
        rand = new Random();
        randomValue = min + rand.nextInt(max);
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getRandomValue(){
        return randomValue = min + rand.nextInt(max);
    }
}
