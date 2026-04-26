package records;

import java.time.LocalDate;

//Record can have static and instance methods and also only static fields
public record StudentRecord(long id,String name,String dept,LocalDate startDate,LocalDate endDate) {

	//One way is this , if we want some change constructor but here we need to initialize fields
	/*
	public StudentRecord(long id,String name,String dept,LocalDate startDate,LocalDate endDate) {
		if(startDate.isAfter(endDate)) {
			throw new IllegalArgumentException();
		}
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	*/
	
	//Better way : Explicit Canonical Constructor , implicitly it will initialize fields 
	//Also called compact constructor
	public StudentRecord{
		if(startDate.isAfter(endDate)) {
			throw new IllegalArgumentException();
		}
	}
	
	//This is non canonical constructor, and it should call canonical constructor
	//It doesnt matter if above explicit canonical constructor is there or not, if not present then this will call implicit canonical constructor
	public StudentRecord(long id,String name,String dept,LocalDate startDate) {
		this(id,name,dept,startDate,LocalDate.of(2099, 10, 10));
	}
	
	public static void main(String[] args) {
		StudentRecord stud1 = new StudentRecord(1,"Kathan","CSE",LocalDate.of(2024, 8, 12),LocalDate.of(2026,6,10));
		System.out.println(stud1);
		System.out.println(stud1.name());
		//stud1.name = "Modi"; //Give compiler error because it is immutable
		
		//New student with no end date
		StudentRecord stud2 = new StudentRecord(2,"Modi","CSE",LocalDate.of(2026, 8, 12));
		System.out.println(stud2);
		
		//Will generate exception as start date is after end date
		/*
		StudentRecord stud3 = new StudentRecord(3,"random","CSE",LocalDate.of(2026, 8, 12),LocalDate.of(2026,6,10));
		System.out.println(stud3);
		*/
	}
}
