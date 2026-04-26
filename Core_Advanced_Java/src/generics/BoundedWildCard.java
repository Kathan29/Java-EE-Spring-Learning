package generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoundedWildCard {

	public static void main(String[] args) {
		
		invMethodGenWithBoundedType(new ArrayList<Number>());
		
		//invMethodGenWithBoundedType(new ArrayList<String>()); //Error because we have bounded it to extend number or it subtypes only.
		
		var list = new ArrayList<Integer>();
		invMethodGenWithBoundedType(list); 
		//Integer data = list.get(0); //Here because of double code in method, gives runtime error
		
//Showing usecase of Upper Bounded Wildcard / Method generic with Bounded Type parameter as producer
		var list1 = new ArrayList<Integer>(List.of(1,3,2,6));
		display(list1);
		
		var list2 = new ArrayList<Double>(List.of(2.4,5.7,8.9));
		display(list2);
		
//Showing use case of lower bounded wildcard as consumer		
		var list3 = joinTwoList(list1,list2,new ArrayList<Number>());
		//joinTwoList(list1,list2,new ArrayList<String>()); //Will not allow ,give compiler error
		                                  //Because we have written l1 and l2 extends E, l3 super E
										  //But List<Integer> and List<Double> does not extend List<String>.
		
//Showing use case of both producer and consumer : needs exact match, no wildcard.
		replaceAll(list3,3,5);

//Showing use case as produces data but dont care about type argument.
		System.out.println(disjoint(list1,list2));
	}

	
	private static boolean disjoint(List<?> list1,List<?> list2) {
		
		var iterate = (list1.size() > list2.size()) ? list1 : list2;
		var container = (iterate.equals(list1)) ? list2 : list1;
		
		for(var element : iterate) {
			if(container.contains(element)){
				return false;
			}
		}
		return true;
	}
	
	private static <T> void replaceAll(List<T> list, T oldVal, T newVal) {
		for(var index = 0;index<list.size();index++) {
			if(oldVal.equals(list.get(index))) {
				list.set(index, newVal);
			}
		}
	}


	
	
	/* Same method with method generics bounded type
	private static <E3,E1 extends E3,E2 extends E3> List<? super E>  joinTwoList(List<E1> list1, List<E2> list2, List<E3> list3) {
		list3.addAll(list1);
		list3.addAll(list2);
		System.out.println(list3);
		return list3;
	}
	*/
	private static <E> List<? super E> joinTwoList(List<? extends E> list1, List<? extends E> list2, List<? super E> list3) {
		list3.addAll(list1);
		list3.addAll(list2);
		System.out.println(list3);
		return list3;
	}

	
	
	
	/* 
	//We can also use this display method which is with method generics bounded type parameter, 
	// we have commented this because same name , as internally compiler will do type erasure(remove generics) hence it will be same
	// method signature.
	private static <T extends Number> void display(List<T> list) {
		for(var element : list) {
			System.out.println(element);
		}
	}
	*/	
	private static void display(List<? extends Number> list) {
		for(var element : list) {
			System.out.println(element);
		}
	}
	

	
		
	private static <T extends Number> void invMethodGenWithBoundedType(List<T> list) {
		//list.add(Double.valueOf(23.3)); //Error because T can be Integer also
		
		// We should not add like this , but showing just a work around.
		var element = (T) Double.valueOf(23.3);
		list.add(element); //Here we have type casted and then added , hence no compiler error but it can give Runtime error
						   // Runtime error as ClassCastException is possible when T is Integer.
		//System.out.println(list);
		
	}

}
