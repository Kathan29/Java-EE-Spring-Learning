package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnboundedWildCard {

	public static void main(String[] args) {
		
		List<Integer> l1 = new ArrayList<>(List.of(1,2,3,5,9));
		List<Integer> l2 = new ArrayList<>(List.of(2,4,5,3,6));
		
		getCommonElementsWithWildCard(l1,l2);
		
		List<?> l3 = l2;
		//l3.add(2); //Error but l3.add(null); allowed
		
		
		List<?> l4 = new ArrayList<>(); //Same as above
		
		List<?> l5 = new ArrayList<String>();
		//l5.add("java"); //Error not allowed. 
		
	//	List<Object> l6 = l2; // Error 
		//Because List<Integer> is not subtype of List<Object> , so List<Integer>!=List<Object>, because generics are invariant
		
		List<Object> l7 = new ArrayList<>();
		l7.add("java");
		l7.add(10);
		
	//	List<Object> l8 = new ArrayList<String>(); //Error
		//Same reason List<String> != List<Object>
		
		printList(l1);
		List<String> l9 = new ArrayList<>(Arrays.asList("java","c++"));
		printList(l9);
		
		//printListWithObject(l1); // Error
		//printListWithObject(l9); //Error
		printListWithObject(l7);
		
		getCommonElementsWithMethodGeneric(l1,l2);

	}

	private static void printList(List<?> l) {
		for(var element : l) {
			System.out.println("Element :"+element);
		}
		
	}
	
	private static void printListWithObject(List<Object> l) {
		for(var element : l) {
			System.out.println("Element :"+element);
		}
	}

	private static void getCommonElementsWithWildCard(List<?> l1, List<?> l2) {
		
		var count = 0;
		for(var element : l1) {
			if(l2.contains(element)) {
				count++;
			}
		}
		
		System.out.println("Total Common Elements Count : "+count);
		//l2.add(45); //Not allowed
		//l2.addAll(l1); //Not allowed
		
	}
	
	private static <T> void getCommonElementsWithMethodGeneric(List<T> l1, List<T> l2) {
		System.out.println("In method generic");
		
		var count = 0;
		for(var element : l1) {
			if(l2.contains(element)) {
				count++;
			}
		}
		
		System.out.println("Total Common Elements Count : "+count);
		//l2.add(45); //Not allowed
		//l2.addAll(l1); //Allowed
		//System.out.println(l2);
		
		//This way only ,add is allowed ,otherwise addAll is allowed.
		for (var element : l1) {
		    l2.add(element);
		}
		System.out.println(l2);
		
		
	}

}
