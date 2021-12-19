public class TYIterator extends OHIterator {
	public TYIterator(OHRequest queue) {
		super(queue);
	}
	@Override
	public OHRequest next() {
		OHRequest result = super.next();
		/** next advances curr by one */
		if (curr != null && curr.description.contains("thanku")) {
			curr = curr.next;
		}
		return result;
	}
}