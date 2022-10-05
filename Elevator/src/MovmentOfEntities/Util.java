package MovmentOfEntities;

import Entities.Elevator;
import Entities.Floor;

import java.util.ArrayList;

public class Util {

    public static Integer getRandomValueOfFloorOrDestination(){
        int num;
        do {
            num = getRandomNumber();
        } while (num < 4 || num > 20);
        return num;
    }

    public static Integer getRandomValueOfPassengers(){
        int num;
        do {
            num = getRandomNumber();
        } while (num < 1 || num > 10);
        return num;
    }

    public static Integer getRandomNumber(){
        return (int) (Math.random() * 100);
    }

    public static int checkNullFloorsWithPassengers(Floor floor){
        try{
            if(floor.getPassengers() != null) return floor.getPassengers().size();
        }catch (NullPointerException e){
            System.out.println("Этаж " + floor.getNumberOfFloor() + " пустой");
        }
        return 0;
    }
    public static int checkNullPassengersSize(Elevator obj){
        try{
            if(obj.getPassengers() != null) return obj.getPassengers().size();
        }catch (NullPointerException e){
            System.out.println("лифт пустой");
        }
        return 0;
    }

    public static int getAbsForPassengers(ArrayList<Integer> passengers, int elevatorPos){
        if(passengers == null){ return elevatorPos;}
        int calc = passengers.get(0);
        int tempGetClosestFloor = calc;

        for (int anotherTemp : passengers) {
            int temp;
            temp = Math.abs(anotherTemp - elevatorPos);
            if (temp < calc) {
                calc = temp;
                tempGetClosestFloor = anotherTemp;
            }
        }
        return tempGetClosestFloor;
    }
}
