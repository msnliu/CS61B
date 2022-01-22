package lab14;

import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    private int period;
    private int state;
    public SawToothGenerator(int period) {
        state = 0;
        this.period = period;
    }

    @Override
    public double next() {
        state += 1;
        return normalize(state % period);
    }

    private double normalize(double num) {
        return 2 * num / (period - 1) - 1;
    }
}
