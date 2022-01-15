package bearmaps.proj2c;

import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;

// a conceptual mistake made: removeSmallest is equivalent to adding to the path
// THE ABOVE IS REFERRING TO KRUSKAL'S ALGORITHM FOR MST
// in order to get solution, must use edgeTo

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private List<Vertex> solution;
    private double solutionWeight;
    private double timeSpent;
    private int numOfDequeue;
    private Map<Vertex, Double> distTo;
    private Map<Vertex, Vertex> edgeTo;
    private DoubleMapPQ<Vertex> pq;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        solution = new LinkedList<>();
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        pq = new DoubleMapPQ<>();

        Stopwatch sw = new Stopwatch();
        pq.add(start, input.estimatedDistanceToGoal(start, end));
        distTo.put(start, 0.0);
        while (pq.size() != 0) {
            Vertex p = pq.removeSmallest();
            // success
            if (p.equals(end)) {
                outcome = SolverOutcome.SOLVED;
                getSolution(start, end);
                solutionWeight = distTo.get(end);
                return;
            } else {
                numOfDequeue += 1;
            }
            // solving in process
            for (WeightedEdge<Vertex> i : input.neighbors(p)) {
                relax(i, input, end);
            }
            // check runtime
            timeSpent = sw.elapsedTime();
            if (timeSpent > timeout) {
                outcome = SolverOutcome.TIMEOUT;
                return;
            }
        }
        // solving finishes with no outcome
        outcome = SolverOutcome.UNSOLVABLE;
    }

    public void getSolution(Vertex start, Vertex end) {
        solution.add(end);
        while (!end.equals(start)) {
            end = edgeTo.get(end);
            solution.add(0, end);
        }
    }

    public void relax(WeightedEdge<Vertex> e, AStarGraph<Vertex> g, Vertex goal) {
        Vertex p = e.from();
        Vertex q = e.to();
        double w = e.weight();
        if (!distTo.containsKey(q)) {
            distTo.put(q, Double.POSITIVE_INFINITY);
        }
        double heuristicDist = distTo.get(p) + w + g.estimatedDistanceToGoal(q, goal);
        double currDist = distTo.get(p) + w;
        if (currDist < distTo.get(q)) {
            distTo.put(q, currDist);
            edgeTo.put(q, p);
            if (pq.contains(q)) {
                pq.changePriority(q, heuristicDist);
            } else {
                pq.add(q, heuristicDist);
            }
        }
    }

    public SolverOutcome outcome() {
        return outcome;
    }
    public List<Vertex> solution() {
        return solution;
    }
    public double solutionWeight() {
        return solutionWeight;
    }
    public int numStatesExplored() {
        return numOfDequeue;
    }
    public double explorationTime() {
        return timeSpent;
    }
}
