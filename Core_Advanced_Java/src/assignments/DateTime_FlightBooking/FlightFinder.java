package assignments.DateTime_FlightBooking;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

public class FlightFinder {

	private Map<String, List<Flight>> allFlights = new HashMap<>();

	public FlightFinder(Map<String, List<Flight>> allFlights) {
		this.allFlights = allFlights;
	}

	public List<NavigableSet<Flight>> findFlights(int dayOfMonth, int month, int year, 
	        int preferredDepartureStartHour, int preferredDepartureEndHour, 
	        String departureCity, String arrivalCity, String finalArrivalCity,
			String departureCityTimeZone, String arrivalCityTimeZone) {
		
		List<NavigableSet<Flight>> result = new ArrayList<>();
        
        // Step 1: Construct ZonedDateTime objects to represent User-specified time interval when flights depart
      
        LocalDateTime dateStartTime = LocalDateTime.of(year,month,dayOfMonth,preferredDepartureStartHour,00);
        LocalDateTime dateEndTime = LocalDateTime.of(year,month,dayOfMonth,preferredDepartureEndHour,00);
    
        ZonedDateTime zoneDateStartTime = ZonedDateTime.of(dateStartTime,ZoneId.of(departureCityTimeZone));
        ZonedDateTime zoneDateEndTime = ZonedDateTime.of(dateEndTime,ZoneId.of(departureCityTimeZone));

        // Step 2: Find departing flights at departureCity
        List<Flight> allDepartingFlights = allFlights.get(departureCity);
        
        NavigableSet<Flight> departingflights = new TreeSet<>();
        
        for(Flight flight:allDepartingFlights){
            if(flight.getArrivalCity().equals(arrivalCity))
            {
               LocalDateTime temp = flight.getDepartureTime();
               ZonedDateTime zoneTemp = ZonedDateTime.of(temp,ZoneId.of(departureCityTimeZone));
                if(zoneTemp.isAfter(zoneDateStartTime) && zoneTemp.isBefore(zoneDateEndTime)){
                     departingflights.add(flight);
                } 
            }
            
        }
                
        
        // Step 3: Find connecting flights
        //   Constraint 1: Departing at arrivalCity (e.g., Dubai) and arrive at finalArrivalCity (e.g., Mumbai)
        //   Constraint 2: Should start at least two hours after the arrival time of the first flight in the above navigable set

        List<Flight> allConnectingFlights = allFlights.get(arrivalCity);        
        
        NavigableSet<Flight> connectingflights = new TreeSet<>();      

        LocalDateTime afterTwoHrs = departingflights.first().getArrivalTime().plusHours(2);
        ZonedDateTime zoneAfterTwoHrs = ZonedDateTime.of(afterTwoHrs,ZoneId.of(arrivalCityTimeZone));
        for(Flight flight : allConnectingFlights){
            if(flight.getArrivalCity().equals(finalArrivalCity)){
                LocalDateTime temp = flight.getDepartureTime();
                ZonedDateTime zoneTemp = ZonedDateTime.of(temp,ZoneId.of(arrivalCityTimeZone));
                if(zoneTemp.isAfter(zoneAfterTwoHrs)){
                    connectingflights.add(flight);
                }
            }
        }  
        
        result.add(departingflights);
        result.add(connectingflights);
        
        return result;
	}
	
	public static void main(String[] args) {
	    Flight f1 = new Flight(1, "1", "Emirates", "New York", "Dubai",
			LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
		Flight f2 = new Flight(2, "2", "Delta", "San Francisco", "Paris",
				LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
		Flight f3 = new Flight(3, "3", "Delta", "San Francisco", "Rome",
				LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
		Flight f4 = new Flight(4, "4", "Emirates", "San Francisco", "Dubai",
				LocalDateTime.of(2017, 12, 20, 8, 0), LocalDateTime.of(2017, 12, 20, 8, 0));
		Flight f5 = new Flight(5, "5", "Emirates", "San Francisco", "Dubai",
			LocalDateTime.of(2017, 12, 20, 9, 30), LocalDateTime.of(2017, 12, 20, 9, 30));
		Flight f6 = new Flight(6, "6", "Delta", "San Francisco", "Dubai",
				LocalDateTime.of(2017, 12, 20, 11, 30), LocalDateTime.of(2017, 12, 20, 11, 30));
		Flight f7 = new Flight(7, "7", "Delta", "San Francisco", "Dubai",
				LocalDateTime.of(2017, 12, 20, 12, 30), LocalDateTime.of(2017, 12, 20, 12, 30));
		Flight f8 = new Flight(8, "8", "Emirates", "San Francisco", "Dubai",
				LocalDateTime.of(2017, 12, 20, 16, 0), LocalDateTime.of(2017, 12, 20, 16, 0));
		Flight f9 = new Flight(9, "9", "Emirates", "Paris", "New Delhi",
			LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
		Flight f10 = new Flight(10, "10", "Delta", "Dubai", "Singapore",
				LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
		Flight f11 = new Flight(11, "11", "Delta", "Dubai", "Mumbai",
				LocalDateTime.of(2017, 12, 20, 9, 30), LocalDateTime.of(2017, 12, 20, 9, 30));
		Flight f12 = new Flight(12, "12", "Emirates", "Dubai", "Mumbai",
				LocalDateTime.of(2017, 12, 20, 10, 30), LocalDateTime.of(2017, 12, 20, 10, 30));
		Flight f13 = new Flight(13, "13", "Delta", "Dubai", "Mumbai",
				LocalDateTime.of(2017, 12, 20, 12, 00), LocalDateTime.of(2017, 12, 20, 12, 00));
		Flight f14 = new Flight(14, "14", "Emirates", "Dubai", "Mumbai",
				LocalDateTime.of(2017, 12, 20, 14, 00), LocalDateTime.of(2017, 12, 20, 14, 00));
		Flight f15 = new Flight(15, "15", "Delta", "Rome", "Mumbai",
				LocalDateTime.of(2017, 12, 20, 14, 00), LocalDateTime.of(2017, 12, 20, 14, 00));
				
				
		
		Map<String, List<Flight>> allFlights = new HashMap<>();
		
		allFlights.put("New York", Arrays.asList(f1));
		allFlights.put("San Francisco", Arrays.asList(f2, f3, f4,f5,f6,f7,f8));
		allFlights.put("Paris",Arrays.asList(f9));
		allFlights.put("Dubai",Arrays.asList(f10,f11,f12,f13,f14));
        allFlights.put("Rome",Arrays.asList(f15));
        
		List<NavigableSet<Flight>> result = new FlightFinder(allFlights).findFlights(20, 12, 2017, 9, 13, "San Francisco", "Dubai", "Mumbai", "America/Los_Angeles", "Asia/Dubai");
		
	}

}