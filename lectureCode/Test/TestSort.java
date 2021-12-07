import org.junit.Test;
import static org.junit.Assert.*;
public class TestSort {
	@Test
	public void testSort() {
		String[] input = {"i", "have", "an", "egg"};
		String[] expected = {"an", "egg", "have", "i"};
		Sort.sort(input);

		assertArrayEquals(expected, input);

		/**
		for (int i = 0; i < input.length; i++) {
			if (!input[i].equals(expected[i])) {
				System.out.println(""Mismatch in position " + i + ", expected: " + expected + ", but got: " + input[i] + ".");
			return
			}
		}
		*/
		
		/**
		BUG: comparing the address of input and expected
		if (input != expected) {
			System.out.println("Error! There seems to be a problem with Sort.sort.");
		}
		if (!java.util.Arrays.equals(input, expected))
			System.out.println("Error! There seems to be a problem with Sort.sort.");
		}
		*/
	}
	@Test
	public void testSwap() {
		String[] input = {"i", "have", "an", "egg"};
		int a = 0;
		int b  = 2;
		String[] expected = {"an", "have", "i", "egg"};
		Sort.swap(input, a, b);

		assertArrayEquals(expected, input);
	}
	@Test
	public void testFindSmallest() {
		String[] input = {"i", "have", "an", "egg"};
		int expected = 2;

		int actual = Sort.findSmallest(input, 0);
		org.junit.Assert.assertEquals(expected, actual);

		String[] input2 = {"there", "are", "many", "pigs"};
		int expected2 = 2;

		int actual2 = Sort.findSmallest(input2, 2);
		assertEquals(expected2, actual2);
	}
}