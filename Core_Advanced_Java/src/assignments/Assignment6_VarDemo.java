package assignments;

import java.util.HashMap;
import java.util.Map;


public class Assignment6_VarDemo {
	private static void hashMapDemo() {
		System.out.println("\n\nInside hashMapDemo ...");

		var map1 = new HashMap<String, Integer>();
		map1.put("Raj", 29);
		map1.put("John", 25);
		map1.put("Anita", null);
		System.out.println(map1);

		// Modifying value
		map1.put("Anita", 23);
		System.out.println(map1);

		System.out.println("Contains John? " + map1.containsKey("John"));
		System.out.println("John's age: " + map1.get("John"));

		System.out.println("Iterating using keySet ...");
		var names = map1.keySet();
		for (var name : names) {
			System.out.println("Name: " + name + ", Age: " + map1.get(name));
		}

		System.out.println("Iterating using entrySet ...");
		var mappings = map1.entrySet();
		for (var mapping : mappings) {
			System.out.println("Name: " + mapping.getKey() + ", Age: " + mapping.getValue());
		}

		names.remove("Anita");
		System.out.println(map1);

		var userProfile = new HashMap<String, Map<String, Object>>();

		var profile = new HashMap<String, Object>();
		profile.put("age", 25);
		profile.put("dept", "CS");
		profile.put("city", "New York");

		userProfile.put("John", profile);

		profile = new HashMap<>();
		profile.put("age", 29);
		profile.put("dept", "CS");
		profile.put("city", "New York");

		userProfile.put("Raj", profile);

		System.out.println("userProfile: " + userProfile.toString());

		var profile1 = userProfile.get("John");
		int age = (Integer) profile1.get("age");
		System.out.println("Age: " + age);
	}

	public static void main(String[] args) {
		hashMapDemo();
	}
}
