import java.util.Arrays;

public class TestBus {
    public static void main(String[] args) {
        // create a bus with a capacity of 3
        Bus bus = new Bus(3, "ABC 123", "Toyota", "Coaster", "white");

        // create some passengers
        Person p1 = new Person("Alice", 25, "female");
        Person p2 = new Person("Bob", 30, "male");
        Person p3 = new Person("Charlie", 35, "male");
        Person p4 = new Person("David", 40, "male");

        // add the driver to the bus
        bus.setDriver(p1);

        // add some passengers to the bus
        bus.addPerson(p2);
        bus.addPerson(p3);

        // try to add one more passenger than the bus can hold
        if (!bus.addPerson(p4)) {
            System.out.println("Could not add " + p4.getName() + " to the bus because it is full.");
        }

        // print the number of passengers and occupants on the bus
        System.out.println("Number of passengers on the bus: " + bus.getNumPassengers());
        System.out.println("Number of occupants on the bus: " + bus.getNumOccupants());

        // list the riders on the bus
        System.out.println("Riders on the bus:");
        bus.listRiders();

        // try to remove a passenger who is not on the bus
        if (!bus.removePassenger(p4)) {
            System.out.println("Could not remove " + p4.getName() + " from the bus because they are not on it.");
        }

        // remove a passenger from the bus
        if (bus.removePassenger(p2)) {
            System.out.println("Removed " + p2.getName() + " from the bus.");
        }

        // list the riders on the bus again
        System.out.println("Riders on the bus after removing " + p2.getName() + ":");
        bus.listRiders();

        // print the empty seats on the bus
        int[] emptySeats = bus.getEmptySeats();
        if (emptySeats.length == 0) {
            System.out.println("There are no empty seats on the bus.");
        } else {
            System.out.println("Empty seats on the bus: " + Arrays.toString(emptySeats));
        }
    }
}
