public class IntList {
	public int first;
	public IntList rest;
	public IntList (int f, IntList r) {
		this.first = f;
		this.rest = r;
	}

	@Override
	public boolean equals(Object o) { ... }
	public static IntList list(int ... args) { ... }

	public void skippify() {
		IntList p = this;
		int n = 1;
		while (p != null) {
			IntList next = p.rest;
			for (int i = 0; i  < n;  i++) {
				if (next == null) {
					break;
				}
				next = next.rest;
			}
			p.rest = next;
			p = next;
			n += 1;
		}
	}

	public static void evenOdd(IntList lst) {
		if (lst == null || lst.rest == null || lst.rest.rest == null) {
			return;
		}

		IntList second = lst.rest;

		/** used to help us find the last even indexed link */
		int index = 0;

		/** while condition exit while loop if we are at last even indexed link */
		while (!(index % 2 == 0 && ( lst.rest == null || lst.rest.rest == null))) {
			IntList temp = lst.rest;
			lst.rest = lst.rest.rest;
			lst = temp;
			index += 1;
		}
		lst.rest = second;
	}
}