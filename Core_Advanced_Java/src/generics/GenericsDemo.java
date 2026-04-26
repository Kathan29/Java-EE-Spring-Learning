package generics;

import java.util.ArrayList;
import java.util.List;

public class GenericsDemo {
	
	public static void main(String[] args) {
		Container<String> stringStore = new Store<>();
		stringStore.set("java");
		// stringStore.set(1); //Compiler Error
		System.out.println(stringStore.get());
		
		Container<Integer> integerStore = new Store<>();
		integerStore.set(1);
		System.out.println(integerStore.get());
		
		//Container<int> intStore = new Store<>(); //Compiler Error : Primitive type cannot be used
		
		List<Number> listStore = new ArrayList<>();
		listStore.add(Integer.valueOf(1));
		listStore.add(Double.valueOf(2.22));
		//listStore.add("1"); //Compiler Error
		System.out.println(listStore);
		
		
	}
	
}

interface Container<T>{
	void set(T a);
	T get();
}

class Store<T> implements Container<T>{
	private T a;
	
	@Override
	public void set(T a) {
		this.a = a;
	}

	@Override
	public T get() {
		return a;
	}
	
}
