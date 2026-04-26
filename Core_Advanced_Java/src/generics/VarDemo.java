package generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class VarDemo {

	public static void main(String[] args) {
		
		coreVarDemo();
		advancedVarDemo();

	}

	private static void advancedVarDemo() {
		Map<String,List<Integer>> student = new HashMap<>();
		
		List<Integer> marks = new ArrayList<>();
		marks.add(90);
		marks.add(98);
		
		student.put("Kathan", marks);
		
		System.out.println("\n Map Demo without var : ");
		for(Entry<String, List<Integer>> stud : student.entrySet()) {
			String name = stud.getKey();
			List<Integer> markList = stud.getValue();
			System.out.println(name + " -> "+markList);
		}
		
		//Using Var
		var varStudent = new HashMap<String,List<Integer>>();
		var varMarks = new ArrayList<Integer>();
		varMarks.addAll(marks);
		varStudent.put("Kathan", varMarks);
		System.out.println("\n Map Demo with var : ");
		for(var stud : varStudent.entrySet()) {
			var name = stud.getKey();
			var markList = stud.getValue();
			System.out.println(name + " -> "+markList);
		}
		
		
	}

	private static void coreVarDemo() {
		var student = new Student();
		System.out.println(student);
		
		var studentNames = new String[] {"Dhoni","Kohli"};
		var firstStudent = studentNames[0];
		System.out.println("First Student Name : "+firstStudent);
		
		System.out.println("Enhanced for each loop : ");
		for(var name : studentNames) {
			System.out.println(name);
		}
		
		System.out.println("Traditional for loop : ");
		for(var i = 0;i<studentNames.length;i++) {
			System.out.println(studentNames[i]);
		}
		
		var id = 2;
		var greeting = "Hello";
		System.out.println("Id : "+id);
		System.out.println("Greeting : "+greeting);
		
	}

}

class User{}
class Student extends User{}
