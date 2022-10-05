import MovmentOfEntities.ElevatorsWork;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ElevatorsWork el = new ElevatorsWork();
        el.prepareMovement();
        el.deliverPeopleToAnotherFloor();
    }

}