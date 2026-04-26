package nestedClass;

public class NonStaticMemberClass {
	int a = 20;
	
	public DemoClass getNestedClassInstance() {
		return new DemoClass();
	}
	
	private class DemoClass{
		int a = 10;
		static int b = 2;
		void display() {
			System.out.println("\nInside Inner class display method");
			System.out.println("Inner a : "+this.a);
			System.out.println("Inner a: "+a);
			System.out.println("b : "+b);
			System.out.println("Outer a: "+NonStaticMemberClass.this.a);
		}
		
	}
	
	void display() {
		System.out.println("\nInside Outer class display method");
		System.out.println("Outer a : "+this.a);
		System.out.println("Outer a: "+a);
		System.out.println("Outer a: "+NonStaticMemberClass.this.a);
		System.out.println("Static Inner b : "+NonStaticMemberClass.DemoClass.b);
		//Here display method is instance method, hence it does not need outer class object explicitly
		DemoClass d = new DemoClass(); // It internally does DemoClass d = this.new DemoClass();
		System.out.println("Inner a: "+d.a);
	}
	
	
	public static void main(String[] args) {
		NonStaticMemberClass n1 = new NonStaticMemberClass();
		
		//As main method is static method, it does not have this for outer object , hence we need outer object to access inner object.
		DemoClass d1 = n1.new DemoClass();
		d1.display();
		
		DemoClass d2 = n1.getNestedClassInstance();
		d2.display();
		
		n1.display();
	}
}
