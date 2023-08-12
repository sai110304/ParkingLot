import java.util.*;
import java.time.*;

class User {
    private String name;
    private String phNo;
    private String vehicleType;
    private String vehicleSize;
    private int vehicleSlotNo;
    private int userId;
    private int floorNo;
    private int rate;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getVehicleSlotNo() {
        return vehicleSlotNo;
    }

    public void setVehicleSlotNo(int vehicleNo) {
        this.vehicleSlotNo = vehicleNo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(String vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    Instant inTime;
    Instant outTime;
    // Used to take the current time from System

    void printTicket(){
        //Displays the User's ticket on Screen
        System.out.println("__________Your Ticket__________");
        System.out.println("Name- " + getName() + "\nPhone Number- " + getPhNo());
        System.out.println("Vehicle- " + getVehicleType() + "\nVehicle Size- " + getVehicleSize());
        System.out.println("Vehicle parked in floor- " + getFloorNo());
        System.out.println("UserId- " + getUserId());
        System.out.println("Vehicle Slot- " + this.getVehicleSlotNo());
        System.out.println("Thank You..\n\n");
    }
}


class Floor {
    boolean[] flr= new boolean[100];
    //A boolean array to determine whether the particular slot in the floor is empty or not
    int availability(int size, int start, int end){
        //Checks for availability of User's vehicle in the given floor and returns the position of empty slot
        if(size==1){
            for(int j=start; j<=end; j++){
                if(!flr[j]){
                    return j;
                }
            }
        }
        if(size==2) {
            for(int j=start; j<end; j++) {
                if (!flr[j] && !flr[j + 1]) {
                    return j;
                }
                if(j==end-1){
                    return -1;
                }
            }
        }
        return -1;
    }

    void SlotAllotment(int j, int size) {
        // Changes the boolean array's elements to true for occupied slots
        flr[j]=true;
        if(size==2) flr[j+1]=true;
    }

    void SlotDeAllocation(int j, int size) {
        // Changes the boolean array's elements to false for emptied slots
        flr[j]=false;
        if(size==2) flr[j+1]=false;
    }
}


abstract class Vehicle {
    //Defines start index and end index of each vehicle in floor's boolean array
    //Defines amount of each vehicle in respective inherited class
    int startIndex;
    int endIndex;
    int amount;
}

class Car extends Vehicle{
     Car() {
        startIndex= 40;
        endIndex= 79;
        amount=40;
    }
}
class ElectricCar extends Vehicle{

    ElectricCar() {
        startIndex= 80;
        endIndex= 99;
        amount=70;
    }
    boolean Charge;
}
class Truck extends Vehicle{

    Truck() {
        startIndex= 0;
        endIndex= 39;
        amount=80;
    }
}
class MotorCycle extends Vehicle{

    MotorCycle() {
        startIndex= 0;
        endIndex= 39;
        amount=20;
    }
}
class Handicapped extends Vehicle{

    Handicapped() {
        startIndex= 40;
        endIndex= 79;
        amount=10;
    }
}

class Payment {

    long calculateDuration(Instant inTime, Instant outTime) {
        //Calculates the duration of vehicle in Parking Lot using in-Time and out-Time and returns time in Minutes
        return Duration.between(inTime,outTime).toMinutes()+1;
    }

    double calculateAmount(long time, int rate){
        // Calculates the amount user should pay using duration of stay using "per-hour parking fee model"
        long t=time;
        int count=1;
        double x=0,temp;
        while(t>0){
            if(count==1){
                temp=t*rate;
                x+= temp/60;
                count++;
                rate=rate/2;
                t=t-60;
            }
            else if(count==2){
                temp=t*rate;
                x+= temp/60;
                count++;
                rate=rate/2;
                t=t-60;
            }
            else {
                temp=t*rate;
                x+= temp/60;
                count++;
            }
        }
        return x;
    }
    Scanner sc= new Scanner(System.in);
    void cashPayment() {
        //payment using cash
        System.out.println("Pay cash at Exit-1 to attendant");
        System.out.println("Thank You..!!!\n\n\n");
    }
    void creditCardPayment() throws InterruptedException {
        //payment using card
        System.out.println("Pay money at Exit-1 counter");
        Thread.sleep(1000);
        System.out.println("Enter PIN");
        int pin= sc.nextInt();
        Thread.sleep(1000);
        System.out.println("Thank You..!!!\n\n\n");
    }
}

class CostumerInfoPortal extends User{
    //payment of fees through costumer-info portal
    Scanner sc= new Scanner(System.in);
    void NetBanking() throws InterruptedException {
        //online payment using net banking
        System.out.println("Enter Bank Name-");
        String bankName= sc.nextLine();
        System.out.println("Directing to Payment Page...");
        Thread.sleep(1000);
        System.out.println("Enter User Id");
        String usId= sc.next();
        System.out.println("Enter Password");
        String password= sc.next();
        Thread.sleep(1000);
        System.out.println("Payment Successful");
        System.out.println("Exit at 2nd Gate");
        System.out.println("Thank You..!!!\n\n\n");
    }
    void UPI() throws InterruptedException {
        //online payment using UPI
        System.out.println("Enter UPI Id");
        String upiId= sc.next();
        Thread.sleep(1000);
        System.out.println("Enter OTP");
        int otp= sc.nextInt();
        Thread.sleep(1000);
        System.out.println("Enter UPI PIN");
        int pin= sc.nextInt();
        Thread.sleep(1000);
        System.out.println("Payment Successful\n");
        System.out.println("Exit at 2nd Gate");
        System.out.println("Thank You..!!!\n\n\n");
    }
}


public class parkingLot {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc=new Scanner(System.in);
        boolean[] userData= new boolean[300]; //boolean array to check if the particular index of array is empty
        User[] u= new User [300];
        for(int i=0; i<300; i++){
            u[i]= new User();
        }
        //array of user class to store the users' data

        Floor floor1= new Floor();
        Floor floor2= new Floor();
        Floor floor3= new Floor();

        Car c= new Car();
        MotorCycle mc= new MotorCycle();
        Truck tr= new Truck();
        ElectricCar ec= new ElectricCar();
        Handicapped hc= new Handicapped();

        int x=0;
        while(true){

            System.out.println("Select the action you want to perform-\n1.Park Vehicle\n2.Departure");
            int action= sc.nextInt();
            if(action==1){
                for(int i=0; i<300; i++){
                    if(!userData[i]){
                        userData[i]=true;
                        x=i; //assigning index in array for user
                        break;
                    }
                }
                u[x].setUserId(x+1);
                System.out.println("Enter your Details-\nName- ");
                String s = sc.next();
                u[x].setName(s);
                System.out.println("Phone Number-");
                s = sc.next();
                u[x].setPhNo(s);
                System.out.println("Select your vehicle type-\n1.Motor Cycle\n2.Car\n3.Truck\n4.Electrical Car\n5.Handicapped Bike");
                int n= sc.nextInt();
                int si=0,ei=0;
                if(n==1){
                    si= mc.startIndex;
                    ei=mc.endIndex;
                    u[x].setRate(mc.amount);
                    u[x].setVehicleType("Motor Cycle");
                }
                if(n==2) {
                    si= c.startIndex;
                    ei= c.endIndex;
                    u[x].setRate(c.amount);
                    u[x].setVehicleType("Car");
                }
                if(n==3) {
                    si= tr.startIndex;
                    ei=tr.endIndex;
                    u[x].setRate(tr.amount);
                    u[x].setVehicleType("Truck");
                }
                if(n==4) {
                    si= ec.startIndex;
                    ei=ec.endIndex;
                    u[x].setVehicleType("E-car");
                    System.out.println("Do you want to charge your Car?\n1.Yes\n2.No");
                    int temp= sc.nextInt();
                    if(temp==1) {
                        ec.Charge= true;
                        u[x].setRate(ec.amount);
                    }
                    else {
                        ec.Charge= false;
                        u[x].setRate(ec.amount-30);
                    }
                }
                if(n==5) {
                    si= hc.startIndex;
                    ei=hc.endIndex;
                    u[x].setVehicleType("Handicapped");
                }

                System.out.println("Enter the size of vehicle-\n1.Small\n2.Large");
                int size = sc.nextInt();
                if (size == 1) u[x].setVehicleSize("Small");
                if (size == 2) u[x].setVehicleSize("Large");

                System.out.println("Slots are available in");
                if(n==3 || n==4 || n==5){
                    //trucks and handicapped in Floor-1
                    if(floor1.availability(size, si, ei)!=-1) System.out.println("Floor 1");
                }
                if(n==1 || n==2 || n==4){
                    //bikes, cars in Floor-2 and Floor-3
                    //electric cars in every floor
                    if(floor2.availability(size, si, ei)!=-1) System.out.println("Floor 2");
                    if(floor3.availability(size, si, ei)!=-1) System.out.println("Floor 3");
                }

                System.out.println("Enter the floor you want to choose-");
                int f=sc.nextInt();
                u[x].setFloorNo(f);
                if(f==1 && (n==3 || n==5)){
                    u[x].setVehicleSlotNo(floor1.availability(size,si,ei));
                    floor1.SlotAllotment(u[x].getVehicleSlotNo(),size);
                }
                else if(f==2 && n!=3 && n!=5){
                    u[x].setVehicleSlotNo(floor2.availability(size,si,ei));
                    floor2.SlotAllotment(u[x].getVehicleSlotNo(),size);
                }
                else if(f==3 && n!=3 && n!=5){
                    u[x].setVehicleSlotNo(floor3.availability(size,si,ei));
                    floor3.SlotAllotment(u[x].getVehicleSlotNo(),size);
                }

                u[x].inTime= Instant.now();
                u[x].printTicket();
            }

            else if(action==2){
                Payment pay= new Payment();
                CostumerInfoPortal cip= new CostumerInfoPortal();
                System.out.println("Enter your UserId-");
                int k= sc.nextInt();
                k--;
                u[k].outTime= Instant.now();
                long time= pay.calculateDuration(u[k].inTime,u[k].outTime);
                System.out.println("Total Duration of Parking- "+ time+ " minutes");
                int rate= u[k].getRate();
                double cost= pay.calculateAmount(time,rate);
                System.out.println("\nTotal Payable Amount = "+ cost);
                System.out.println("From where would you like to pay the fees\n1.Costumer's Info Portal\n2.At Exit Gate");
                int p= sc.nextInt();
                if(p==1){
                    System.out.println("Select payment option-\n1.Net Banking\n2.UPI");
                    int d= sc.nextInt();
                    if(d==1) cip.NetBanking();
                    else if(d==2) cip.UPI();
                }
                else if(p==2){
                    System.out.println("Select payment option-\n1.Cash\n2.Credit Card");
                    int d= sc.nextInt();
                    if(d==1) pay.cashPayment();
                    else if(d==2) pay.creditCardPayment();
                }

                userData[k]= false;
                int tempS;
                if(Objects.equals(u[k].getVehicleSize(), "Small")) tempS=1;
                else tempS=2;

                if(u[k].getFloorNo()==1) floor1.SlotDeAllocation(u[k].getVehicleSlotNo(),tempS);
                else if(u[k].getFloorNo()==2) floor1.SlotDeAllocation(u[k].getVehicleSlotNo(),tempS);
                else if(u[k].getFloorNo()==3) floor1.SlotDeAllocation(u[k].getVehicleSlotNo(),tempS);
            }
        }
    }
}