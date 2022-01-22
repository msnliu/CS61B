package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private int period;
    private int state;
    private int temp;
    public StrangeBitwiseGenerator(int period) {
        state = 0;
        this.period = period;
    }

    @Override
    public double next() {
        state += 1;
        temp = state & (state >> 3) & (state >> 8) % period;
        return normalize(temp);
    }

    private double normalize(double num) {
        return 2 * num / (period - 1) - 1;
    }
}
