/** SList with additional operation printLostItems() which prints all items
  * that have ever been deleted. */
public class VengefulSLList<Item> extends SLList<Item> {
    SLList<Item> deletedItems;

    public VengefulSLList() {
        /** all constructors must always call a SLList (superclass's) constructor */
        /** If you don't explicitly call the constructor with super, java will do it automatically for you */
        super();
        deletedItems = new SLList<>();
    }

    public VengefulSLList(Item x) {
        /** implicit call to the superclass's constructor is super() no argument */
        super(x);
        deletedItems = new SLList<>();
    }

    @Override
    public Item removeLast() {
        /** used deletedItems instead did not use super
         we are NOT working on the deletedItems */
        Item x = super.removeLast();
        deletedItems.addFirst(x);
        return x;
    }

    public void printLostItems() {
        deletedItems.print();
    }

    public static void main(String[] args) {

		VengefulSLList<Integer> vs1 = new VengefulSLList<Integer>(0);
		vs1.addLast(1);
		vs1.addLast(5);
		vs1.addLast(10);
		vs1.addLast(13);
		// vs1 is now: [1, 5, 10, 13] 


		vs1.removeLast();
		vs1.removeLast();
		// After deletion, vs1 is: [1, 5]

		// Should print out the numbers of the fallen, namely 10 and 13.
		System.out.print("The fallen are: ");
		vs1.printLostItems();
	}
} 