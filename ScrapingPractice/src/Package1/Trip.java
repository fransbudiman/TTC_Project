package Package1;

import java.util.ArrayList;

public class Trip {
	
	 String tripName;
	 ArrayList<BusStop> busStopArray;
	 
	 public Trip(String tripName) {
		 this.tripName = tripName;
		 busStopArray = new ArrayList<BusStop>();
		 
		 
	 }
	 
	 public void addStop(String busID, String stopID, Boolean direction) {
		 
		 BusStop obj = new BusStop(busID, stopID, direction);
		 if (obj.jsonClass.docString.strip().equals("[]")) {
			 System.out.println("Wrong Bus or Stop ID");
		 }else {
		 busStopArray.add(obj);
		 }
	 }
	 
	 public void removeStop(String busID, String stopID, Boolean direction) {
		 BusStop compare = new BusStop(busID, stopID, direction);
		 boolean found = false;
		 for(BusStop n: busStopArray) {
			 if(n.equals(compare)) {
				 busStopArray.remove(n);
				 found = true;
			 }
		 }
		 
		 if (found) {
			 System.out.println("Delete Successful");
		 }else {
			 System.out.println("Bus or Stop not found");
		 }

	 }
	 
	 public void executeTrip() throws WrongInputException {
		 
		 for (BusStop n : busStopArray) {
			 System.out.println("Bus: " + n.busID + "   |   " + "Stop: " + n.stopID);
			 System.out.println("---------------------------");
			 n.displayTime();
			 System.out.println("\n");
		 }
		 
		 
		 
	 }
	 
}
