package Entities;

import java.util.ArrayList;

public class Elevator{
    private final int maxNumberOfPeople = 5;
    private int currentFloor = 1;

    private ArrayList<Passenger> passengers;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    private Direction direction = Direction.UP;


    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

}
