package records;

import java.time.LocalDate;
import java.util.Objects;

//Immutable Class : Demo of what we need to do for immutable class 
// if we not use record
public final class Student {
	private long id;
	private String name;
	private String dept;
	private LocalDate startDate;
	private LocalDate endDate;
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDept() {
		return dept;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dept, endDate, id, name, startDate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(dept, other.dept) && Objects.equals(endDate, other.endDate) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(startDate, other.startDate);
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", dept=" + dept + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}
	public Student(long id, String name, String dept, LocalDate startDate, LocalDate endDate) {
		super();
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
	

}
