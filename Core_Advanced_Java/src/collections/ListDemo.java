package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListDemo {

	public static void main(String[] args) {

		//List<Integer> list = arrayListDemo();
		//iterableDemo(list);
		listIterableDemo();
	}

	private static void listIterableDemo() {
		List<String> list = new ArrayList<>();
		list.addAll(List.of("a", "b", "c"));

		// Displaying current elements of List with listIterator
		for (ListIterator<String> iterator = list.listIterator(); iterator.hasNext();) {
			System.out.println(iterator.nextIndex() + " " + iterator.next());
		}
		System.out.println();

		// Demonstrate add , set , remove in list iterator
		for (ListIterator<String> iterator = list.listIterator(); iterator.hasNext();) {
			System.out.println(iterator.nextIndex() + " " + iterator.next());

			if (iterator.nextIndex() == 1) {
				System.out.println("Adding the element at index 1");
				iterator.add("test"); // After adding test , iterator has moved ahead, so now nextIndex will print 2
				System.out.println(iterator.nextIndex() + " " + iterator.next());

				System.out.println("Removing the element that was added at index 1");
				// As above 2 and b is printed, now to remove test, we need to comeback
				iterator.previous(); // b
				// Suppose if we do remove now , it will remove b
				iterator.previous(); // test
				iterator.remove(); // Here test is removed

				System.out.println("Testing the set with list Iterator");
				// Commenting this part , as it will create error,
				// because prior to set, only next/previous/set can be there
				// but last statement is remove, so error.
				// iterator.set("test");
				System.out.println(iterator.nextIndex() + " " + iterator.next()); // so now 1 b : printed
				iterator.set("test");
				System.out.println(
						"Setting element at index 1 as test1 to show that two set operations can be invoked sequentially");
				iterator.set("test1");
			}
		}

		System.out.println();
		// Displaying current elements of List with listIterator
		for (ListIterator<String> iterator = list.listIterator(); iterator.hasNext();) {
			System.out.println(iterator.nextIndex() + " " + iterator.next());
		}

	}

	private static void iterableDemo(List<Integer> list) {

		System.out.println("Before iterator : Original List = " + list);
		Iterator<Integer> iterable = list.iterator();
		while (iterable.hasNext()) {
			int element = iterable.next();
			System.out.println(element);

			if (element == 8) {
				iterable.remove();
				System.out.println("Removed element : " + element);
			}
		}
		System.out.println("In iterable demo : " + list);
	}

	private static List<Integer> arrayListDemo() {
		List<Integer> list = new ArrayList<>();

		list.add(1);
		list.add(2);
		// list.addAll(new ArrayList<>() {3,4}); //Not Allowed in Java
		// So instead of this there are 3 possible options

		/*
		 * List<Integer> list2 = new ArrayList<>(); //Option 1 list2.add(3);
		 * list2.add(4); list.addAll(list2);
		 */

		// list.addAll(Arrays.asList(3,4)); //Option 2
		list.addAll(List.of(3, 4)); // Option 3

		System.out.println(list);
		// Output : [1,2,3,4]

		list.get(1); // Returns value at that index
		list.add(2, 10); // It inserts value 10 at index 2, shifting all elements to it right
		System.out.println(list);
		// Output : [1,2,10,3,4]

		list.set(2, 1); // It doesn't insert new value, just update value at index 2
		System.out.println(list);
		// Output : [1,2,1,3,4]

		list.remove(2); // Removes value at index 2
		System.out.println(list);
		// Output : [1,2,3,4]

		list.remove(Integer.valueOf(2)); // Removes value 2 if it is present
		System.out.println(list);
		// Output : [1,3,4]

		System.out.println(list.contains(2));
		// Output : false

		System.out.println(list.contains(3));
		// Output : true

		System.out.println(list.size());
		// Output : 3

		System.out.println(list.indexOf(1)); // One more method is lastIndexOf()
		// Output : 0

		List<Integer> list2 = new ArrayList<>();
		list2.add(13);
		list2.add(14);
		list.addAll(list2);
		System.out.println(list);

		list.removeAll(list2);
		System.out.println(list);
		// If list was [1,3,4,3,4] and list2 = [3,4] then removeAll : [1]
		// If list was [1,3,4,13,14] and list2 = [3,4] then removeAll: [1,3,4]

		list2.add(3);
		list.retainAll(list2); // Retain all just retain values which match list2 , all other values are
								// removed.
		System.out.println(list);
		// Output : [3]

		list.addAll(list2);
		System.out.println(list); // Output : [3,13,14,3]

		List<Integer> list3 = list.subList(2, 3); // Range-view: subList (new list is backed by original)
		System.out.println(list3); // Output : [14]
		list3.set(0, 6);
		System.out.println(list); // Output : [3,13,6,3] as 0 index in list 3 is 2 index in list
		list3.add(0, 7);
		System.out.println(list); // Output : [3,13,7,6,3]

		list.set(2, 8);
		System.out.println(list + " " + list3); // Output : list : [3,13,8,6,3] and list3 : [8,6]
		list.add(0, 9);
		System.out.println(list);
		// System.out.println(list3); //Error

		/*
		 * for (int element : list) { System.out.println("element: " + element);
		 * 
		 * // Generates ConcurrentModificationException if (element == 9) {
		 * list.remove(Integer.valueOf(element)); // valueOf is used due to its caching
		 * } }
		 */
		return list;

	}

}
