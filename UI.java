
/**
 * Write a description of class Car here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

class Car
{
    // instance variables - replace the example below with your own
    private String registrationNumber;
    private String owner;
    private LocalDateTime arrivalTime;
    private double rateToBeCharged;
    
    /**
     * Constructor for objects of class Car
     *
     */
    public Car(String regNum, String ownerName)
    {
        // initialise instance variables
        registrationNumber = regNum;
        owner = ownerName;
        arrivalTime = LocalDateTime.now();
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String getRegistrationNumber()
    {
        // put your code here
        return registrationNumber;
    }
    
    public String getOwner()
    {
        // put your code here
        return owner;
    }
    
    public LocalDateTime getArrivalTime()
    {
        // put your code here
        return arrivalTime;
    }
    
    public long getTimeIn()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-L-yyyy");
        return arrivalTime.until(LocalDateTime.now(),ChronoUnit.MINUTES);
    }
    
    public double getTotalCharge()
    {
        return rateToBeCharged * ((getTimeIn()/60)+1); 
    }
    
    public void setChargedRate(double rate)
    {
        rateToBeCharged = rate;
    }
    
    public double getChargedRate()
    {
        return rateToBeCharged;
    }
}








//SPOT
class Spot 
{
    // instance variables - replace the example below with your own
    private String id;
    private Car carInSpot;
    private double hourlyRate;
    private boolean isOccupied;
    /**
     * Constructor for objects of class Spot
     */
    public Spot(String sId, double aHourlyRate)
    {
        // initialise instance variables
        id = sId;
        hourlyRate = aHourlyRate;
        isOccupied = false;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int addCar(Car aCar)
    {
        carInSpot = aCar;
        isOccupied = true;
        return 1;
    }
    
    public String getSpotId()
    {
        return id;
    }
    public Car removeCar()
    {
        isOccupied = false;
        return carInSpot;
    }
    
    public Car getCar()
    {
        return carInSpot;
    }
    
    public double getParkingCharges()
    {
        return hourlyRate;
    }
    
    public boolean getIsOccupied()
    {
        return isOccupied;
    }
    
    public String toString()
    {
        return "Spot Id:"+id+"\nCar In Spot:"+carInSpot+"\nhorly rate:"+hourlyRate+"\nisOccupied:"+isOccupied;
    }
}












//CARPARK
class CarPark
{
    // instance variables - replace the example below with your own
    private ArrayList<Spot> spots;
    
    /**
     * Constructor for objects of class CarPark
     */
    public CarPark()
    {
        // initialise instance variables
        spots = new ArrayList<Spot>();
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void addParkingSpot(Spot aSpot)
    {
        // put your code here
        spots.add(aSpot);
    }
    
    public boolean removeParkingSpot(String id)
    {
        for(Spot aSpot : spots)
        {
            if(aSpot.getSpotId().equalsIgnoreCase(id) && aSpot.getIsOccupied()==false)
            {
                spots.remove(aSpot);
                return true;
            }
            
        }
        return false;
    }
    
    public String showAvailableSpots()
    {
        String availableSpots = "no spots available!";
        for(Spot aSpot : spots)
        {
            if(aSpot.getIsOccupied())
                {availableSpots += aSpot.getSpotId()+",";}
        }
        if(availableSpots.equalsIgnoreCase("no spots available!"))
            return availableSpots;
        else
            return availableSpots.substring(availableSpots.length()-2);
    }
    
    public Spot findCar(String regNum)
    {
        Spot foundSpot = null;
        for(Spot aSpot : spots)
        {
            if(aSpot.getCar().getRegistrationNumber().equals(regNum))
            {
                foundSpot = aSpot;
                break;
            }    
        }
        return foundSpot;
    }
    
    public String showOccupiedSpots()
    {
        String occupiedSpots = "none of the spots are occupied!";
        for(Spot aSpot : spots)
        {
            if(!aSpot.getIsOccupied())
                {occupiedSpots += aSpot.getSpotId()+",";}
        }
        
        if(occupiedSpots.equalsIgnoreCase("none of the spots are occupied!"))
            return occupiedSpots;
        else
            return occupiedSpots.substring(occupiedSpots.length()-2);
    }
    
    public ArrayList<Spot> showAllSpots()
    {
        return spots;
    }
    
}










//IOSUPPORT
class IOSupport
{
    // instance variables - replace the example below with your own
    static Scanner scan ;
    
    /**
     * Constructor for objects of class IOSupport
     */
    public IOSupport()
    {
        // initialise instance variables
        
    
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public static String getString(String display)
    {
        // put your code here
        System.out.println(display);
		scan = new Scanner(System.in);
        String temp = scan.nextLine();
        
        if(temp.equals(""))
        {
            temp = "empty";
        }
        
        return temp;
    }
    
    public static double getDouble(String display)
    {
        // put your code here
        System.out.println(display);
        scan = new Scanner(System.in);
        double temp ;
        
        try
        {
            temp = scan.nextDouble();
        }
        catch(Exception e)
        {
            return -1;
        }
        return temp;
    }
	public static void printIt(String text)
	{
		System.out.println(text);
	}
}






//UI
public class UI
{
    private CarPark carPark;
    public UI()
    {
        carPark = new CarPark();
    }
	public static void main(String args[])
	{
        
		UI ui = new UI();
		
		ui.run();
	}
	public void run()
	{
		int getOut=0;
		while(getOut!=-1)
		{
			switch(menu())
			{
				case "1":addSpot();break;
				case "2":deleteSpot();break;
				case "3":listOccupiedSpot();break;
				case "4":parkCar();break;
				case "5":collectCar();break;
				case "6":findCar();break;
				case "7":moveCar();break;
				case "8":getOut=-1;break;
				default:IOSupport.printIt("\n***\nPlease enter a valid choice!\n***\n");
			}	
		}
	}
	public String menu()
	{
		System.out.println("1)Add Spot");
		System.out.println("2)Delete Spot");
		System.out.println("3)List Occupied Spots");
		System.out.println("4)Park Car");
		System.out.println("5)Collect Car");
		System.out.println("6)Find Car");
		System.out.println("7)Move Car");
		System.out.println("8)Exit");
		return IOSupport.getString("What would you like to do?").trim();
	}
	public void addSpot()
	{
		String id = IOSupport.getString("Enter id of spot:");
        if (id == "")
        {
            IOSupport.printIt("dont leave the id blank!");
        }
        else
        {
            double hourlyRate = IOSupport.getDouble("Enter hourly rate: $");
            if(hourlyRate < 0)
            {
                IOSupport.printIt("please enter a valid amount!");
            }
            else
            {
                Spot aSpot = new Spot(id,hourlyRate);
                carPark.addParkingSpot(aSpot);
                IOSupport.printIt("***********\n"+carPark.showAllSpots()+"\n**********");
                /*for(Spot tempSpot : carPark.showAllSpots())
                {
                    IOSupport.printIt(tempSpot.getCar().toString());
                }*/


            }
        }
	}
	public void deleteSpot()
	{
        String tempId = IOSupport.getString("Enter SpotId:");
        
        if(carPark.removeParkingSpot(tempId))
        {   
            IOSupport.printIt("deleted spot!");
        }
        else
        {
            IOSupport.printIt("spot not found or is not empty!");
        }
            
    }
		
	
	public void listOccupiedSpot()
	{
		
	}
	public void parkCar()
	{
		
	}
	public void collectCar()
	{
		
	}
	public void findCar()
	{
		
	}
	public void moveCar()
	{
		
	}
	
}




