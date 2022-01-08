package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import edu.princeton.cs.algs4.Stopwatch;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    Random rand = new Random(500);
    private static KDTree buildLectureTree() {
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        return kd;
    }

    @Test
    public void testNearestDemoSlides() {
        KDTree kd = buildLectureTree();
        Point actual = kd.nearest(0, 7);
        Point expected = new Point(1, 5);
        assertEquals(actual, expected);
    }

    @Test
    public void testnearest() {
        testnearestlaucher(10000, 100);
    }

    public void testnearestlaucher(int total, int subgroup) {
        List<Point> points = generatePoints(total);
        List<Point> goals = generatePoints(subgroup);
        KDTree kd = new KDTree(points);
        NaivePointSet nps = new NaivePointSet(points);
        for (Point p : goals) {
            Point actual = kd.nearest(p.getX(), p.getY());
            Point expected = nps.nearest(p.getX(), p.getY());
            assertEquals(actual, expected);
        }
    }

    public List<Point> generatePoints(int N) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            points.add(generatePoint());
        }
        return points;
    }

    public Point generatePoint() {
        return new Point(rand.nextDouble(), rand.nextDouble());
    }

    @Test
    public void time() {
        List<Point> goals = generatePoints(1000000);
        for (int i = 125; i <= 1000; i *= 2) {
            List<Point> points = generatePoints(i);
            KDTree kd = new KDTree(points);
            NaivePointSet nps = new NaivePointSet(points);
            Stopwatch sw = new Stopwatch();
            for (Point p : goals) {
                kd.nearest(p.getX(), p.getY());
            }
            double timekd = sw.elapsedTime();
            System.out.println("kdtree");
            System.out.println(timekd);
            System.out.println("naivepointset");
            Stopwatch swnps = new Stopwatch();
            for (Point p : goals) {
                nps.nearest(p.getX(), p.getY());
            }
            double timenps = swnps.elapsedTime();
            System.out.println(timenps);
            System.out.println("---");
        }
    }
}

