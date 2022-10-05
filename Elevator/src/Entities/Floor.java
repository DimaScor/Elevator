package Entities;

import java.util.ArrayList;

public class Floor{
    private Direction direction;
    private int numberOfFloor;
    private ArrayList<Passenger> passengers = new ArrayList<>();

    private int amountOfPassengersOnTheFloor = 0;


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }


    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    public void setNumberOfFloor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    public int getAmountOfPassengersOnTheFloor() {
        return amountOfPassengersOnTheFloor;
    }

    public void setAmountOfPassengersOnTheFloor(int amountOfPassengersOnTheFloor) {
        this.amountOfPassengersOnTheFloor = amountOfPassengersOnTheFloor;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }


}