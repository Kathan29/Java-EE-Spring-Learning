package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class ArraysDemo {
	
	public static void main(String[] args) {
		//sequentialDemo();
		parallelizedDemo();
	}

	private static void parallelizedDemo() {
		
		//For large Arrays on multicore , then only it will work. Min size atleast 8192 elements
		int[] arr = {14,2,7};
		Arrays.parallelSort(arr);
		System.out.println("Parallel Sort : "+Arrays.toString(arr));
		
		IntBinaryOperatorImpl binaryOperator = new IntBinaryOperatorImpl();
		Arrays.parallelPrefix(arr, binaryOperator);
		System.out.println("Parallel Prefix : "+Arrays.toString(arr));
		
		IntUnaryOperatorImpl unaryOperator = new IntUnaryOperatorImpl();
		unaryOperator.setArray(arr);
		Arrays.setAll(arr, unaryOperator);
		System.out.println("Parallel Set all : "+Arrays.toString(arr));
	}
	

	private static void sequentialDemo() {
		
		//Some arrays most commonly used methods :
//1) List<T> asList(T ...)
		String[] strArr = new String[] {"Dhoni","Kohli"};
		
		List<String> strings = Arrays.asList(strArr);
		System.out.println(strings);
		
		//This strings list is fixed size , we cannot remove or add elements
			//strings.remove(0); //Exception : Unsupported Operation Exception
			//strings.add("john"); //Exception : Unsupported Operation Exception
		strings.set(1, "Rohit");
		System.out.println("Original Array is also changed : "+Arrays.toString(strArr));
		
		//Second way to create List from arrays
		strings = Arrays.asList("Dhoni","Kohli");
		
		//To create Modifiable Array
		strings = new ArrayList<>(Arrays.asList(strArr));
		strings.remove(0);
		System.out.println("Modifiable List : "+strings+ " , original string array : "+Arrays.toString(strArr));
		
		//Suppose if we want to create fix size list
		List<String> fixedList = Arrays.asList(new String[3]);
		//fixedList.add("a"); //Exception : Fixed size is there, so again we cannot add or remove
		fixedList.set(0, "a");
		System.out.println(fixedList); //O/p : [a,null,null]
		
		//So to create Modifiable fix list
		fixedList = new ArrayList<>(Arrays.asList(new String[3]));

		// recall from autoboxing that arrays are not auto-boxeable
		// List<Integer> fixedList2 = Arrays.asList(new int[2]); //Exception, so solution : 
		List<int[]> fixedList2 = Arrays.asList(new int[2]);
		System.out.println("Size of fixed List : "+fixedList2.size());

//2) Arrays.sort and Arrays.binarySearch
		int[] arr1 = new int[] {14,2,59,3};
		Arrays.sort(arr1);  	//[2,3,14,59]
		System.out.println(Arrays.toString(arr1));
		
		System.out.println(Arrays.binarySearch(arr1,14)); // ans : 2
		System.out.println(Arrays.binarySearch(arr1,4)); // here it will give , position where it can set so : -3
		
//3) Copying and comparing array
		int[] newArray = Arrays.copyOf(arr1, 6); //Create new array of length 6, and copy elements from old array
		System.out.println("newArray: " + Arrays.toString(newArray)); //[2,3,14,59,0,0]
		
		int[] newArray1 = new int[6];
		System.arraycopy(arr1, 0, newArray1, 0, arr1.length); //arraycopy(src array,src position,des array, des pos, length)
		System.out.println("newArray1: " + Arrays.toString(newArray1));
		
		Arrays.fill(newArray, 13);
		System.out.println("Fill with 13: " + Arrays.toString(newArray)); //Fill all values with 13
		
		System.out.println("Equals? " + Arrays.equals(arr1, newArray));

//4) Arrays.deepEquals(Object[], Object[]);
			//  Returns true if arrays are deeply equal to one another. 
			//  Appropriate for nested arrays
		int[][][] deepArray1 = { { {1, 2, 3}, {4, 5, 6} } };
		int[][][] deepArray2 = { { {1, 2, 3}, {4, 5, 6} } };
		System.out.println("Deep Array Equals? " + Arrays.deepEquals(deepArray1, deepArray2));
		
		//int[] deepArray1 = {1, 2, 3}; // Covariance: Does not work as int[] is not a subtype of Object[]
		//int[] deepArray2 = {1, 2, 3}; 
		//Exception : Now allowed
		
		int[][] deepArray3 = {{1, 2, 3}};
		int[][] deepArray4 = {{1, 2, 31}};
		System.out.println("Deep Array Equals? " + Arrays.deepEquals(deepArray3, deepArray4));
			
	}
	
	static class IntBinaryOperatorImpl implements IntBinaryOperator{

		@Override
		public int applyAsInt(int left, int right) {
			// TODO Auto-generated method stub
			
			return left+right;
		}
		
	}
	
	static class IntUnaryOperatorImpl implements IntUnaryOperator{

		int[] iArray;
		
		public void setArray(int[] iArray) {
			this.iArray = iArray;
		}
		
		@Override
		public int applyAsInt(int operand) {
			// TODO Auto-generated method stub
			if(iArray!=null) {
				return iArray[operand]+5;	
			}else {
			return operand;
			}
	    }
		
	}
}
