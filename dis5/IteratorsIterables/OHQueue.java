import java.util.Iterator;
public class OHQueue implements Iterable<OHRequest> {
	/** need a variable to keep track of the queue or we will lose it after the constructor is over */
	OHRequest queue;

	public OHQueue(OHRequest queue) {
		this.queue = queue;
	}

	@Override
	public Iterator<OHRequest> iterator() {
		return new OHIterator(queue);
	}

	public static void main(String [] args) {
		OHRequest s5 = new OHRequest("I deleted all of my files", "Alex", null);
		OHRequest s4 = new OHRequest("conceptual: what is Java", "Omar", s5);
		OHRequest s3 = new OHRequest("git: I never did lab 1", "Connor", s4);
		OHRequest s2 = new OHRequest("help", "Hug", s3);
		OHRequest s1 = new OHRequest("no I haven't tried stepping through", "Itai", s2);
		OHQueue q = new OHQueue(s1);
		for (OHRequest o : q) {
			/** this for each loop automatically calls the iterator function */
			System.out.println(o.name);
		}
	}
}
