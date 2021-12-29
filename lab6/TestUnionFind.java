import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {
    UnionFind t = new UnionFind(7);

    @Test
    public void Test() {
        t.connect(1, 2);
        t.connect(2, 3);
        t.connect(5, 6);
        t.connect(1, 5);
        assertEquals(2, t.find(6));
        assertEquals(2, t.find(5));
        assertEquals(2, t.find(1));
        assertEquals(2, t.find(3));
        assertEquals(2, t.find(2));
        assertEquals(5, t.sizeOf(2));
    }
}
