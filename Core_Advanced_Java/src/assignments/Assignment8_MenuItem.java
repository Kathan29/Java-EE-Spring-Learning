package assignments;

record CafeMenuItem(String name,String category,double price) {
	CafeMenuItem{
		 if (name == null || name.isBlank()) {
	            throw new IllegalArgumentException("Menu item name cannot be blank");
	        }
	        if (category == null || category.isBlank()) {
	            throw new IllegalArgumentException("Category cannot be blank");
	        }
	        if (price <= 0) {
	            throw new IllegalArgumentException("Price must be positive");
	        }
	}
}

class Assignment8_MenuItem{
	public static void main(String[] args) {
		
		try {
		CafeMenuItem item1 = new CafeMenuItem("Fried Rice",null,150.0);
		System.out.println(item1);
		System.out.println(item1.name());
		System.out.println(item1.category());
		System.out.println(item1.price());
		}catch (Exception e) {
			System.out.println("Caught Exception : "+e.getMessage());
		}
		
		try {
		CafeMenuItem item2 = new CafeMenuItem("Oreo Icecream", "Desert",-50.0);
		System.out.println(item2);
		System.out.println(item2.name());
		System.out.println(item2.category());
		System.out.println(item2.price());
		}catch (Exception e) {
			System.out.println("Caught Exception : "+e.getMessage());
		}
		
		try {
		CafeMenuItem item3 = new CafeMenuItem("", "Desert",50.0);
		System.out.println(item3);
		System.out.println(item3.name());
		System.out.println(item3.category());
		System.out.println(item3.price());
		}catch (Exception e) {
			System.out.println("Caught Exception : "+e.getMessage());
		}
	
	}
}
