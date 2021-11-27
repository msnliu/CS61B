public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

	public int size(){
		/** recursive structure always needs a base case */
		if (rest == null) {
			return 1;
		}
		return 1 + this.rest.size();
	}

    public void addFirst(int x) {
        /** 
        rest = this;
        first = x;
        would not work stackoverflow 
        IntList temp = this;
        rest = temp;
        first = x;
        would not work stackoverflow 
        */

        rest = new IntList(first, rest);
        first = x;
    }

	public int iterativeSize() {
        /** can't reassign this for ex. this = this.rest */
		IntList p = this;
		int totalSize = 0;

		/** Use while instead of if */

		while (p != null){
			p = p.rest;
			totalSize += 1;
		}
		/** missing return */
		return totalSize;
	}

	public int get(int i){
		/** no this */
		if (i == 0) {
			return first;
		}
		/** no return */
		return rest.get(i - 1);
	}

	/** Returns an IntList identical to L, but with
      * each element incremented by x. L is not allowed
      * to change. */
    public static IntList incrList(IntList L, int x) { 
    	if (L == null) {
    		return null;
    	}

        IntList incrementedList = new IntList(L.first + x, null);
        incrementedList.rest = incrList(L.rest, x);

        return incrementedList;        
    }

    /** Returns an IntList identical to L, but with
      * each element incremented by x. Not allowed to use
      * the 'new' keyword. */
    public static IntList dincrList(IntList L, int x) {
        if (L == null) {
        	return null;
        }

        L.first = L.first + x;
        dincrList(L.rest, x);
        /** don't actually care about the return value here */
        return L;
    }

    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L.rest = new IntList(7, null);
        L.rest.rest = new IntList(9, null);
        L.addFirst(1);

        System.out.println(L.size());
        System.out.println(L.iterativeSize());

        // Test your answers by uncommenting. Or copy and paste the
        // code for incrList and dincrList into IntList.java and
        // run it in the visualizer.
        System.out.println(L.get(1));
        /** L stores an address shouted by new */
        System.out.println(L);
        System.out.println(incrList(L, 3));
        System.out.println(dincrList(L, 3));
        /** check for addFirst */
        System.out.println(L.get(0));
    }
}