import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are < end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */

// Hard
public class FlightSolver {

    private int maxCount;
    private PriorityQueue<Flight> start;
    private PriorityQueue<Flight> end;

    public FlightSolver(ArrayList<Flight> flights) {
        Comparator<Flight> startcmptr = (i, j) -> i.startTime - j.startTime;
        Comparator<Flight> endcmptr = (i, j) -> i.endTime - j.endTime;

        start = new PriorityQueue<Flight>(startcmptr);
        end = new PriorityQueue<Flight>(endcmptr);

        start.addAll(flights);
        end.addAll(flights);

        int currCount = 0;
        while (start.peek() != null) {
            if (start.peek().startTime <= end.peek().endTime) {
                currCount += start.poll().passengers;
            } else {
                // invariant 1: start < end
                // invariant 2: start and end are on the same time scale when start > end
                // so we must have accessed the start time of the end
                currCount -= end.poll().passengers;
            }
            if (currCount > maxCount) {
                maxCount = currCount;
            }
        }
    }

    public int solve() {
        /* FIX ME */
        return maxCount;
    }

}
