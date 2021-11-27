public class SLList<LochNess> {

	private static class StuffNode {
		/** doubly linked list or DLList */
		public StuffNode prev;
		public LochNess item;
		public StuffNode next;
		public StuffNode (LochNess i, StuffNode n) {
			item = i;
			next = n;
		}
	} 

	/** L.first.next.next = L.first.next infinite list */
	/** The first item (if its exists) is at sentinel.next */
	private StuffNode sentinel;
	private StuffNode last;
	private int size;

	/** Creates an empty SLList. */
	public SLList() {
		sentinel = new StuffNode(63, null);
		size = 0;
	}

	/** L points to first
	and then first points to the item in the first Node */

	/** L points to sentinel
	and then the first item */

	public SLList(int x) {
		sentinel = new StuffNode(63, null);
		sentinel.next = new StuffNode(x, null);
		size = 1;
	}

	public static void main(String[] args) {
		SLList L = new SLList();
		L.addLast(20);
		System.out.println(L.getFirst());
		System.out.println(L.size());
	}

	/** missing void */
	public void addFirst(LochNess x) {
		/** first becomes next
		evaluate right hand side first
		*/
		sentinel.next = new StuffNode(x, sentinel.next);
		size += 1;
	}

	public int getFirst() {
		return sentinel.next.item;
	}

	/** Iterative */
	/** missing void */
	public void addLast(LochNess x) {

		/** we always have sentinel
		if (first == null) {
			first = new StuffNode(x, null);
		}
		*/

		/** missing StuffNode*/
		StuffNode p = sentinel;
		while (p.next != null) {
			/** missing semicolon */
			p = p.next;
		}
		/** missing StuffNode instantiation*/
		/** missing new */
		/** adding an integer is equivalent to adding an StuffNode to SLList*/
		p.next = new StuffNode(x, null);
		size += 1;
	}

	/** faster way of implementing addlast */
	public void addLastSpeed(LochNess x) {
		last.next = new StuffNode(x, null);
		last = last.next;
		size += 1;
	}

	/** 
	after cashing, this can be deleted
	can't do 
	helper method returns the size of the list that starts at StuffNode P 
	private static int size(StuffNode p){
		if (p.next == null) {
			return 1;
		}

		return 1 + size(p.next);
	}
	*/

	/** faster way of retrieving size of the list */
	public int size() {
		return size;
	}
}