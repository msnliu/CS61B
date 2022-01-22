import edu.princeton.cs.algs4.Quick;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {
    private List<Bear> sortedBears;
    private List<Bed> sortedBeds;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        // TODO: Fix me.
        Pair<List<Bear>, List<Bed>> solutions = QuickSort(bears, beds);
        sortedBears = solutions.first();
        sortedBeds = solutions.second();
    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        // TODO: Fix me.
        return sortedBears;
    }

    /**
     * Returns List of Beds such that the ith Bed is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        // TODO: Fix me.
        return sortedBeds;
    }

    private <T> List<T> catenate(List<T> less, List<T> equal, List<T> greater) {
        List<T> catenated = new LinkedList<>();
        for (T i : less) {
            catenated.add(i);
        }
        for (T i : equal) {
            catenated.add(i);
        }
        for (T i : greater) {
            catenated.add(i);
        }
        return catenated;
    }

    private <T extends Comparable<Q>, Q extends Comparable<T>> void partition(List<T> unsorted, Q pivot, List<T> less, List<T> equal, List<T> greater) {
        for (T i : unsorted) {
            int cmp = i.compareTo(pivot);
            if (cmp < 0) {
                less.add(i);
            } else if (cmp == 0) {
                equal.add(i);
            } else {
                greater.add(i);
            }
        }
    }

    // simultaneous quicksort
    private Pair<List<Bear>, List<Bed>> QuickSort(List<Bear> bears, List<Bed> beds) {

        // Base case
        // bear.size() < 1 implies beds.size() should also be less than 1
        if (bears.size() < 1) {
            return new Pair<>(bears, beds);
        }

        List<Bear> lessBears = new LinkedList<>();
        List<Bear> equalBears = new LinkedList<>();
        List<Bear> greaterBears = new LinkedList<>();
        partition(bears, beds.get(0), lessBears, equalBears, greaterBears);

        List<Bed> lessBeds = new LinkedList<>();
        List<Bed> equalBeds = new LinkedList<>();
        List<Bed> greaterBeds = new LinkedList<>();
        // equalBears.get(0) ensures correspondence
        partition(beds, equalBears.get(0), lessBeds, equalBeds, greaterBeds);

        Pair<List<Bear>, List<Bed>> less = QuickSort(lessBears, lessBeds);
        lessBears = less.first();
        lessBeds = less.second();

        Pair<List<Bear>, List<Bed>> greater = QuickSort(greaterBears, greaterBeds);
        greaterBears = greater.first();
        greaterBeds = greater.second();

        return new Pair<>(catenate(lessBears, equalBears, greaterBears), catenate(lessBeds, equalBeds, greaterBeds));
    }

}
