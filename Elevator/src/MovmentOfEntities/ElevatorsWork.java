package MovmentOfEntities;

import Entities.*;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Чем ниже строки кода, тем выше эволюция, с самого первого момента старался упростить многое.
 */
public class ElevatorsWork {
    private Building building;

    public void prepareMovement(){
        building = new Building();
        building.setElevator(new Elevator());
        building.setAmountOfFloors(Util.getRandomValueOfFloorOrDestination());
        int amountOfFloors = building.getAmountOfFloors();
        ArrayList<Floor> tempFloorList = new ArrayList<>();

        for (int i = 0; i < amountOfFloors; i++) {
            Floor fl = new Floor();
            fl.setNumberOfFloor(i+1);
            fl.setAmountOfPassengersOnTheFloor(Util.getRandomValueOfPassengers());
            ArrayList<Passenger> tempPassengers = new ArrayList<>();
            for(int pass = 0; pass < fl.getAmountOfPassengersOnTheFloor(); pass++){
                Passenger ps = new Passenger();
                boolean fragenBogen = true;
                while (fragenBogen){
                    int easy = Util.getRandomValueOfFloorOrDestination();
                    if (easy <= amountOfFloors && easy > 0 && easy != fl.getNumberOfFloor()){
                        ps.setDestinationFloor(easy);
                        tempPassengers.add(ps);
                        fragenBogen = false;
                    }
                }
            }
            fl.setPassengers(tempPassengers);
            tempFloorList.add(fl);

        }
        building.setFloors(tempFloorList);
        System.out.println("Количество этажей:" + building.getAmountOfFloors());
    }

    public void estimateDirectionForPassengerOnFloors(){
        for(int checkedFloors = 0; checkedFloors < building.getAmountOfFloors(); checkedFloors++){
            Floor checkFloor = building.getFloors().get(checkedFloors);
            int up = 0;
            int down =0;
            if(Util.checkNullFloorsWithPassengers(checkFloor) != 0) {
                for (Passenger passenger : checkFloor.getPassengers()) {
                    if (passenger.getDestinationFloor() > checkFloor.getNumberOfFloor()){
                        up++;
                    }else{
                        down++;
                    }
                }
                if (up > 0 && up > down) {
                    checkFloor.setDirection(Direction.UP);
                }
                if (down > 0 && down > up) {
                    checkFloor.setDirection(Direction.DOWN);
                }
                if (up == down && up != 0){
                    if (building.getElevator().getDirection().equals(Direction.UP)) {
                        checkFloor.setDirection(Direction.UP);
                    } else {
                        checkFloor.setDirection(Direction.DOWN);
                    }
                }
            } else {
                checkFloor.setDirection(Direction.WAIT);
                checkFloor.setAmountOfPassengersOnTheFloor(0);
                System.out.println("установка для этажа " + checkFloor.getNumberOfFloor() + ". Направление: " + checkFloor.getDirection().toString());

            }
        }
    }

    public void deliverPeopleToAnotherFloor() throws InterruptedException {
        boolean move = true;
        int numberOFIteration = 0;
        System.out.println("********************** BEGIN *********************");

        while(move){
            numberOFIteration++;
            System.out.println("***************** ITERATION: "+numberOFIteration+" *****************");
            int currentLocationOfLift = building.getElevator().getCurrentFloor();
            Floor floor = building.getFloors().get(currentLocationOfLift-1);
            System.out.println("Лифт на этаже № " + currentLocationOfLift);
            int elevatorCapacity = building.getElevator().getMaxNumberOfPeople();
            int elevatorOccupancy = Util.checkNullPassengersSize(building.getElevator());
            estimateDirectionForPassengerOnFloors();
            int nextFloorForPassenger;
            if(elevatorOccupancy < elevatorCapacity && Util.checkNullFloorsWithPassengers(floor) > 0) {
                ArrayList<Passenger> tempPassList = new ArrayList<>(floor.getPassengers());
                ArrayList<Passenger> resultList = new ArrayList<>();
                int temp = 0;
                while (elevatorOccupancy < elevatorCapacity
                        && temp < tempPassList.size())
                {
                    Passenger pass = tempPassList.get(temp);

                    if (pass.getDestinationFloor() > currentLocationOfLift
                            && building.getElevator().getDirection().equals(Direction.UP))
                    {
                        floor.getPassengers().remove(pass);
                        resultList.add(pass);
                        ++elevatorOccupancy;
                    }

                    if (pass.getDestinationFloor() < currentLocationOfLift &&
                            building.getElevator().getDirection().equals(Direction.DOWN)
                    )
                    {
                        floor.getPassengers().remove(pass);
                        resultList.add(pass);
                        ++elevatorOccupancy;
                    }
                    temp++;
                }

                //???????????????????????????????????????????????????????????????????????????????????????!!!!
                if (Util.checkNullPassengersSize(building.getElevator()) == 0 ){
                    building.getElevator().setPassengers(resultList);
                }else {
                    building.getElevator().getPassengers().addAll(resultList);
                }

                building.getFloors().set(currentLocationOfLift-1, floor);
                System.out.println("количество пассажиров  в лифте: " + Util.checkNullPassengersSize(building.getElevator()));
                System.out.println("количество пассажиров на этаже: " +
                        Util.checkNullFloorsWithPassengers(building.getFloors().get(currentLocationOfLift-1)));
            }
            nextFloorForPassenger = estimateNextFloorForNotFullElevator(building.getElevator().getDirection());
            changeFloor(nextFloorForPassenger);
            System.out.println("Перемещение на этаж: " + nextFloorForPassenger
                    + " в лифте "+ Util.checkNullPassengersSize(building.getElevator())
            );

            removePassengers();

            estimateElevatorDirection();

            checkFloorsAreTheyEmpty();

            System.out.println("************* END OF ITERATION: "+numberOFIteration+" **************");
            Thread.sleep(1000);

            if(building.getElevator().getDirection().equals(Direction.WAIT)){
                move = false;
            }
        }

        System.out.println("*********************   END   ********************");
    }

    public void changeElevatorDirection(Direction direction){
        if(!building.getElevator().getDirection().equals(direction)){
            building.getElevator().setDirection(direction);
            System.out.println("Выбранно направление: " + direction.name());
        }else {
            System.out.println("Direction didn't change, it's same");
        }
    }

    public boolean quickAttemptToEstimateFloorWithInhabitantsForElevatorOnTheSameFloor(){
        int currentFloorOfElevator = building.getElevator().getCurrentFloor();
        Floor tempFloor = building.getFloors().get(currentFloorOfElevator -1);
//        Floor tempFloor = arrayList.get(building.getElevator().getCurrentFloor()-1);
        if(Util.checkNullFloorsWithPassengers(tempFloor) > 0){
            int up = 0;
            int down = 0;
            ArrayList<Passenger> passengers = new ArrayList<>(tempFloor.getPassengers());
            for (Passenger passenger : passengers) {
                if (passenger.getDestinationFloor() > currentFloorOfElevator) {
                    up++;
                } else if (passenger.getDestinationFloor() < currentFloorOfElevator) {
                    down++;
                }
            }
            if(up > 0 && up > down){
                changeElevatorDirection(Direction.UP);
            }
            else if(down > 0 && down > up){
                changeElevatorDirection(Direction.DOWN);
            } else {
                return false;
            }
            return true;
        }
        return false;
    }

    public void estimateElevatorDirection(){
        if(Util.checkNullPassengersSize(building.getElevator()) != 0) return;
        if(quickAttemptToEstimateFloorWithInhabitantsForElevatorOnTheSameFloor()) return;

        int closestFloorForDirectionUp = estimateNextFloorForNotFullElevator(Direction.UP);
        int closestFloorForDirectionDown = estimateNextFloorForNotFullElevator(Direction.DOWN);
        int currentFloorOfElevator = building.getElevator().getCurrentFloor();
        int lastFloor = building.getAmountOfFloors();
        int tempAbsForUp = Math.abs(currentFloorOfElevator - closestFloorForDirectionUp);
        int tempAbsForDown = Math.abs(currentFloorOfElevator - closestFloorForDirectionDown);

        if( tempAbsForUp > tempAbsForDown && currentFloorOfElevator != lastFloor) {
            changeElevatorDirection(Direction.UP);
            changeFloor(closestFloorForDirectionUp);
        }

        if( tempAbsForUp < tempAbsForDown && currentFloorOfElevator != 1) {
            changeElevatorDirection(Direction.DOWN);
            changeFloor(closestFloorForDirectionDown);
        }
    }

    public void checkFloorsAreTheyEmpty(){
        int countOfWaitFloor = 0;
        for(Floor floor : building.getFloors()){
            if(floor.getDirection().equals(Direction.WAIT)){
                countOfWaitFloor++;
            }
        }
        if(building.getAmountOfFloors() == countOfWaitFloor &&
                Util.checkNullPassengersSize(building.getElevator()) == 0)
            building.getElevator().setDirection(Direction.WAIT);
    }

    public void removePassengers(){
        ArrayList<Passenger> passengers = new ArrayList<>(building.getElevator().getPassengers());
        int currentFloor = building.getElevator().getCurrentFloor();
        int countOfExitedPassengers = 0;
        for (Passenger passenger : passengers) {
            if (passenger.getDestinationFloor() == currentFloor) {
                countOfExitedPassengers++;
                building.getElevator().getPassengers().remove(passenger);
            }
        }
        if(countOfExitedPassengers > 0){
            System.out.println("На этаже - "+ currentFloor +" вышло:"+ countOfExitedPassengers + " пассажиров");
        }
    }

    public void changeFloor(int nextFloor){
        if (Util.checkNullPassengersSize(building.getElevator())
                <= building.getElevator().getMaxNumberOfPeople() && nextFloor != 0) {
            building.getElevator().setCurrentFloor(nextFloor);
        }
    }

    public int estimateNextFloorForNotFullElevator(Direction dir) {
        int elevatorPos = building.getElevator().getCurrentFloor();
        int tempGetClosestFloor = elevatorPos;
        int size = Util.checkNullPassengersSize(building.getElevator());
        if (size > 0) tempGetClosestFloor = getDestinationFloorElevatorPassengers();

        if (size < building.getElevator().getMaxNumberOfPeople()) {
            ArrayList<Floor> floors = building.getFloors();
            ArrayList<Integer> staff = new ArrayList<>();

            int checkFloor;
            for (int i = 0; i < building.getAmountOfFloors(); i++) {
                Floor floor = floors.get(i);
                checkFloor = floor.getNumberOfFloor();
                if (checkFloor != elevatorPos && floor.getDirection().equals(dir)) {
                    staff.add(checkFloor);
                }
            }

            if(staff.size() > 0){
                for (Passenger doubleCheck : building.getElevator().getPassengers()) {
                    staff.add(doubleCheck.getDestinationFloor());
                }
                tempGetClosestFloor = Util.getAbsForPassengers(staff, elevatorPos);
            }
        }

        return tempGetClosestFloor;
    }

    public int getDestinationFloorElevatorPassengers(){
        int anotherTemp;
        ArrayList<Integer> getSomething = new ArrayList<>();
        int currentElevatorFloor = building.getElevator().getCurrentFloor();
        for(Passenger passenger : building.getElevator().getPassengers()){
            getSomething.add(passenger.getDestinationFloor());
        }
        Collections.sort(getSomething);
        System.out.println(getSomething);
        anotherTemp = getSomething.size() > 0 ? Util.getAbsForPassengers(getSomething, currentElevatorFloor) : currentElevatorFloor;
        return anotherTemp;
    }

}