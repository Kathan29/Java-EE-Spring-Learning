package enums;

public class EnumWithSpecificBehaviour {
	 public enum BookGenre{
		 /*
			BIOGRAPHY(12),
			HORROR(18);
		*/
		 
		 
// One way to ensure Constant specific behavior		 
		 BIOGRAPHY(12){
			 boolean isKidFriendly(int age) {
				 return age >= minAge;
			 }
		 },
		 HORROR(18){
			 boolean isKidFriendly(int age) {
				 return age >= minAge;
			 }
		 };
		 
		 abstract boolean isKidFriendly(int age);
		 
			private BookGenre(int minAge) {
				this.minAge = minAge;
			}
			
			protected int minAge;
			public int getMinAge() {
				return minAge;
			}	
			
//One more way to ensure constant specific behavior 
			/* 
			public boolean isKidFriendly(int age) {
				switch(this) {
				case BIOGRAPHY : return age >= minAge;
				case HORROR : return false;
				}
				return false;
			}
			*/
	 }
	 
	 public static void main(String[] args) {
		 for(var constants : BookGenre.values()) {
				System.out.print("Enum : "+constants);
				System.out.print(" , Min Age : "+constants.getMinAge());
				System.out.println(" , isKidFriendly : "+constants.isKidFriendly(20));
			}
	}
}
