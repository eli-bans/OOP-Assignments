public class Bus {
    private int busCapacity;
    private String licensePlate;
    private String busMake;
    private String busModel;
    private String busColor;
    private Person driver;
    private Person[] passengers;
    private int numberOfPassengers =0;

    public Bus (int busCapacity,String licensePlate, String busMake, String busModel,String busColor){
        if (busCapacity<0){
            return;
        }
        this.busCapacity= busCapacity;
        this.licensePlate= licensePlate;
        this.busMake = busMake;
        this.busModel = busModel;
        this.busColor = busColor;
        this.driver = null;
        this.passengers = new Person[busCapacity];
    }

    public int getBusCapacity() {
        return busCapacity;
    }
    public String getLicensePlate() {
        return licensePlate;
    }

    public String getBusColor() {
        return busColor;
    }

    public String getBusModel() {
        return busModel;
    }

    public String getBusMake() {
        return busMake;
    }

    public boolean setDriver(Person person){
        if (person.canDrive()){
            this.driver = person;
            return true;
        }else{
            return false;
        }
    }

    public boolean hasDriver(){
        return this.driver != null;
    }

    public boolean addPerson(Person person){
        if (numberOfPassengers < busCapacity){
            this.passengers[numberOfPassengers]= person;
            numberOfPassengers++;
            return true;
        }else{
            return false;
        }
    }

    public boolean removePassenger(Person person){
        for (int passenger = 0; passenger < this.passengers.length; passenger++){
            if (person.equals(this.passengers[passenger])){
              this.passengers[passenger] = null;
              return true;
            }
        }
        return false;

    }

    public int getNumPassengers(){
        return numberOfPassengers;
    }

    public int getNumOccupants(){
        if (this.hasDriver() && hasPassengers()){
            return numberOfPassengers + 1;
        }
        else if(this.hasDriver() && !hasPassengers()){
            return 1;
        }
        else{
            return numberOfPassengers;
        }
    }

    public boolean hasPassengers(){
        if (numberOfPassengers ==0){
            return false;
        }else{
            return true;
        }
    }

    public boolean isEmpty(){
        if (!(this.hasDriver() && this.hasPassengers())){
            return true;
        }else{
            return false;
        }
    }

    public boolean isFull(){
        if ((this.numberOfPassengers == this.busCapacity)){
            return true;
        }else
        return false;
    }
    public void listRiders() {
        if (this.hasDriver()) {
            System.out.println("The driver's name is " + this.driver.getName() + ".\n" + this.driver.getName() +
                    " is a " + this.driver.getAge() + "-year old " + this.driver.getGender());
        }
        if (numberOfPassengers == 0) {
            System.out.println("There are no passengers on the bus.");
        } else {
            System.out.println("Passengers on the bus:");
            for (int i = 0; i < busCapacity; i++) {
                Person passenger = passengers[i];
                if (passenger != null) {
                    System.out.println("There is a passenger called " + passenger.getName() + " who is a " +
                            passenger.getAge() + "-year old " + passenger.getGender());
                }
            }
        }
    }


    public int[] getEmptySeats(){
        int[] emptySeats = new int[busCapacity-numberOfPassengers];
        int emptySeatCount =0;
        System.out.println(numberOfPassengers);
        for (int seat=0; seat < (busCapacity-numberOfPassengers); seat++){
            if (this.passengers[seat] == null){
                emptySeats[emptySeatCount]=seat;
                emptySeatCount++;
            }

        }
        return emptySeats;
    }

}
