public class Dog{
   public void bark(){
     System.out.println("Moo");
   }
   public static void runFast(){
     System.out.println("Ruff Run");
   }
   public static void main(String[] args) {
   	Dog poppa = new Dog();
	System.out.println("no error");
	poppa.bark();
	System.out.println("no error");
	Dog.bark();
	System.out.println("error");
	poppa.runFast();
	System.out.println("no error inappropriate way");
	Dog.runFast();
	System.out.println("no error correct way");
   }
 }