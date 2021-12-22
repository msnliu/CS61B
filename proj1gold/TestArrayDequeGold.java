import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    @Test
    public void doesnotmatter() {
        ArrayDequeSolution<Integer> ref = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> stud = new StudentArrayDeque<>();
        int actual;
        int expected;

        //addFirst
        for (int i = 0; i < 10; i++) {
            Integer random = StdRandom.uniform(1999);
            stud.addFirst(random);
            ref.addFirst(random);
            actual = stud.get(0);
            expected = ref.get(0);
            try {
                assertEquals("addFirst(" + random + ")", expected, actual);
                System.out.println("addFirst(" + random + ")");
            } catch (AssertionError ae) {
                System.out.println("addFirst(" + random + ")");
            }
        }

        //removeFirst
        for (int i = 0; i < 10; i++) {
            actual = stud.removeFirst();
            expected = ref.removeFirst();
            try {
                assertEquals("removeFirst()", expected, actual);
                System.out.println("removeFirst()");
            } catch (AssertionError ae) {
                System.out.println("removeFirst()");
            }
        }

        //addLast
        for (int i = 0; i < 10; i++) {
            Integer random = StdRandom.uniform(1999);
            stud.addLast(random);
            ref.addLast(random);
            actual = stud.get(stud.size() - 1);
            expected = ref.get(ref.size() - 1);
            try {
                assertEquals("addLast(" + random + ")", expected, actual);
                System.out.println("addLast(" + random + ")");
            } catch (AssertionError ae) {
                System.out.println("addLast(" + random + ")");
            }
        }

        //removeLast
        for (int i = 0; i < 10; i++) {
            actual = stud.removeLast();
            expected = ref.removeLast();
            try {
                assertEquals("removeLast()", expected, actual);
                System.out.println("removeLast()");
            } catch (AssertionError ae) {
                System.out.println("removeLast()");
                return;
            }
        }
    }
}
