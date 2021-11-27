public class SumComLinArgs {
	public static void main(String[] args) {
		int N = args.length;
		int sum = 0;
		for (int i = 0; i < N, i++) {
			sum += args[i];
		}
		System.out.println(sum);
	}
}