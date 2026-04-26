package lambda_streams;

import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConstructorReference {
	public static void main(String[] args) {
		
		//Supplier<String> s1 = () -> new String();
		Supplier<String> s1 = String :: new; //returns string object
		System.out.println("Supplier : "+s1.get());
		
		//Function<String, String> s2 = s -> new String(s);
		Function<String, String> s2 = String :: new; //So returns string object with default java
		System.out.println("Function : "+s2.apply("java")); 
		
		//BiFunction<Integer, Float,HashMap> s4 = (d,c) -> new HashMap(d,c);
		BiFunction<Integer, Float,HashMap> s4 = HashMap :: new;
		System.out.println("Bifunction : "+s4.apply(5, 0.2f));
		
	}
}
