package generics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class GenericMethodDemo {

	public static void main(String[] args) {

// Understanding type arguments from method arguments
		typeArgMethodArg(22.0);
		typeArgMethodArg("java");

// Checking type arguments for target type
		Double d1 = typeArgTargetType();

// Checking type arguments when both method argument and target type are there.
		String s1 = typeArgMethodArgTargetArg("abc");
		// String s2 = typeArgMethodArgTargetArg(10); // Here it is compiler error ,
													  // because method arg and target type are
													  // different, then what to consider , so here we understood it is
													  // based on method arg only, i.e. Here it will infer Integer
		Integer i1 = typeArgMethodArgTargetArg(10);
		// Integer i2 = typeArgMethodArgTargetArg("abc"); //Here also based on method
														  // arg, it will infer String , hence error

// Understanding type arguments inference when more than one method argument.
		String s3 = typeArgMethodArg2("a", "b"); // Here both arg string so String.
		Serializable o1 = typeArgMethodArg2("a", 10); // Here one is Integer , one is String => Most common is
														// Serializable, we can also write Object here.
		Serializable o2 = typeArgMethodArg2("a", new ArrayList());
		Number n1 = typeArgMethodArg2(2,22.0);
		List l1 = typeArgMethodArg2(new ArrayList(),new LinkedList());
		Collection c1 = typeArgMethodArg2(new ArrayList(),new HashSet());
		
//Understanding type arguments in method invocation context.
		targetTypeInvoker1(typeArgInferenceFromTargetType2()); //It will infer List<String> because from inner List<T> 
															   // and outer is List<String> in parameter , hence T = String.
		targetTypeInvoker1(new ArrayList<>()); // here also type is infered from context, outer needs List<string>, so ArrayList<String> works
		targetTypeInvoker2(typeArgInferenceFromTargetType2()); //Both generic , hence infer as Object
		List<String> l = targetTypeInvoker2(typeArgInferenceFromTargetType2()); //Here target type is there, hence infer T = string.
		targetTypeInvoker2(new ArrayList<>()); //No context , hence again infer as Object
		List<String> l2 = targetTypeInvoker2(new ArrayList<>()); //Here target type is given, hence T = string.
	

	}
	
	private static <T> List<T> typeArgInferenceFromTargetType2() {	
		return null;
	}
	private static void targetTypeInvoker1(List<String> list) {
		for (String s : list) {
			System.out.println("Element: " + s);
		}
	}	
	
	private static <T> List<T> targetTypeInvoker2(List<T> list) {
		return list;
	}

	// Here whatever will be most common , it will be T
	private static <T> T typeArgMethodArg2(T value1, T value2) {
		// TODO Auto-generated method stub
		return null;
	}

	// So here based on method arg, compiler find out what will be T.
	private static <T> T typeArgMethodArgTargetArg(T value) {
		// TODO Auto-generated method stub
		return value;
	}

	// This method is generally not useful in real life, without no arguments, just
	// for demo to understand target type.
	private static <T> T typeArgTargetType() {
		// TODO Auto-generated method stub
		return null;
	}

	private static <T> void typeArgMethodArg(T val) {

		System.out.println("Type Argument: " + val.getClass().getName());
	}

}



