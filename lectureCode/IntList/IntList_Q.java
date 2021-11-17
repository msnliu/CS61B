public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

	/** Return the size of the list using... recursion! */
	public int size() {
		if (rest == null) {
			return 1;
		}
		/** difference between this in java and self in python: don't have to pass the this explicitly */
		return 1 + rest.size();
	}

	/** Return the size of the list using no recursion! */
	public int iterativeSize() {
		IntList p = this;
		int totalsize = 0;
		while (p != null) {
			totalsize += 1;
			p = this.rest;
		}
		return totalsize;
	}

	/** Returns the ith value in this list.*/
	public int get(int i) {
		if (i == 0) {
			return first;
		}
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
        /** recursion on rest */
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
        if (L != null) {
        	L.first = L.first + x;
        	dincrList(L.rest, x);
        }
        return L;
    }

	public static void main(String[] args) {
		IntList L = new IntList(15, null);
		L = new IntList(10, L);
		L = new IntList(5, L);

		System.out.println(L.get(1));
        System.out.println(incrList(L, 3));
        System.out.println(L);
        System.out.println(dincrList(L, 3));   
        System.out.println(L);

	}
} 