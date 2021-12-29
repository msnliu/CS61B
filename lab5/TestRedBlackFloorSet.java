import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestRedBlackFloorSet {
    @Test
    public void randomizedTest() {
        AListFloorSet AFS = new AListFloorSet();
        RedBlackFloorSet RBFS = new RedBlackFloorSet();
        for (int i = 0; i < 1000000; i += 1) {
            double num = StdRandom.uniform(-5000, 5000);
            AFS.add(num);
            RBFS.add(num);
        }
        for (int i = 0; i < 100000; i += 1) {
            assertEquals(AFS.floor(i), RBFS.floor(i), 0.000001);
        }
    }
}
