package enums;

public enum EnumDemo {
	BIOGRAPHY,
	HORROR;
	
	public static void main(String[] args) {
		for(var constants : EnumDemo.values()) {
			System.out.print("Enum : "+constants);
			System.out.print(" , Name : "+constants.name());
			System.out.print(" , Ordinal : "+constants.ordinal());
			System.out.print(" , Compare To : "+constants.compareTo(HORROR));
			System.out.println(" , Equals : "+constants.equals(HORROR));		
		}	
	}
}
