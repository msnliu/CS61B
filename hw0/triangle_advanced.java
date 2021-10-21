public class triangle_advanced {
	public static void drawTriangle(int N) {
		int row = 0;
		int SIZE = N;
		while (row < SIZE) {
			int col = 0;
			while (col <= row) {
				System.out.print("*");
				col = col + 1;
			}
			row = row + 1;
			System.out.println();
	}
}
	public static void main(String[] args) {
		drawTriangle(10);
	}
}