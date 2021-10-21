public class maxfor {
    /** Returns the maximum value from m. */
    public static int maxfor(int[] m) {
        int temp = m[0];
        int max = m[0];
    	for (int i = 0; i < m.length - 1; i += 1) {
    		if (m[i] < m[i + 1]) {
    			temp = m[i + 1];
    		} else {
    			temp = m[i];
    		}
            if (max < temp) {
                max = temp;
            }
    	}
        System.out.println(max);
        return 0;
    }
    public static void main(String[] args) {
       int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};    
       maxfor(numbers);  
    }
}