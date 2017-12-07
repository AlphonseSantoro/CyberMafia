import java.util.ArrayList;

public class Car {
    private int value;
    private ArrayList carType;
    private RandomValue rand;

    public Car(){
        getMinValueFromDB();
        //getMaxValueFromDB();
    }

    public Car(int min, int max){
        //Create DB Connection

    }

    public void addCar(String name){
        carType.add(name);
    }

    public void getRandomCar(){

    }

    public void setValue(int value){
        this.value = value;
    }

    public void getMinValueFromDB(){

    }
}
