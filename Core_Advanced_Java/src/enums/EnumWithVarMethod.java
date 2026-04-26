package enums;

public enum EnumWithVarMethod {
	BIOGRAPHY(12),
	HORROR(18);
	
	private EnumWithVarMethod(int minAge) {
		this.minAge = minAge;
	}
	
	private int minAge;
	public int getMinAge() {
		return minAge;
	}
	
	public static void main(String[] args) {
		for(var constants : EnumWithVarMethod.values()) {
			System.out.print("Enum : "+constants);
			System.out.print(" , Name : "+constants.name());
			System.out.print(" , Ordinal : "+constants.ordinal());
			System.out.print(" , Compare To : "+constants.compareTo(HORROR));
			System.out.print(" , Equals : "+constants.equals(HORROR));	
			System.out.println(" , Min Age : "+constants.getMinAge());
		}	
	}
}
