package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private int state;
    private double factor;
    public AcceleratingSawToothGenerator(int period, double factor) {
        state = 0;
        this.period = period;
        this.factor = factor;
    }

    @Override
    public double next() {
        state += 1;
        if (state % period == 0) {
            period *= factor;
            state = 0;
        }
        return normalize(state % period);
    }

    private double normalize(double num) {
        return 2 * num / (period - 1) - 1;
    }
}
