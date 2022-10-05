package Entities;

import java.util.ArrayList;

public class Building {

    private Elevator elevator;
    private int amountOfFloors;

    private ArrayList<Floor> floors = new ArrayList<>();

    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }


    public Elevator getElevator() {
        return elevator;
    }

    public int getAmountOfFloors() {
        return amountOfFloors;
    }

    public void setAmountOfFloors(int amountOfFloors) {
        this.amountOfFloors = amountOfFloors;
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }

    public void setFloors(ArrayList<Floor> floors) {
        this.floors = floors;
    }

}
