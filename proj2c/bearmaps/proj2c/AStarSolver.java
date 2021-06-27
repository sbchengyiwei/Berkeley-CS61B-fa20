package bearmaps.proj2c;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private final SolverOutcome outcome;
    private LinkedList<Vertex> solution = new LinkedList<>();
    private final double solutionWeight;
    private int numStatesExplored;
    private double explorationTime;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        ArrayHeapMinPQ<Vertex> pq = new ArrayHeapMinPQ<>();
        Map<Vertex, Double> distToStart = new HashMap<>();
        Map<Vertex, Double> distToEnd = new HashMap<>();
        Map<Vertex, Vertex> edgeTo = new HashMap<>();

        Stopwatch sw = new Stopwatch();
        distToStart.put(start, 0.0);
        pq.add(start, distToStart.get(start));

        while (pq.size() != 0) {

            if (pq.getSmallest().equals(end)) {
                outcome = SolverOutcome.SOLVED;
                solutionWeight = distToStart.get(end);

                Vertex curVertex = pq.getSmallest();
                solution.addFirst(curVertex);
                while (!curVertex.equals(start)) {
                    solution.addFirst(edgeTo.get(curVertex));
                    curVertex = edgeTo.get(curVertex);
                }

                explorationTime = sw.elapsedTime();
                return;
            }

            List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(pq.removeSmallest());
            numStatesExplored += 1;

            explorationTime = sw.elapsedTime();
            if (explorationTime > timeout) {
                outcome = SolverOutcome.TIMEOUT;
                solution = new LinkedList<>();
                solutionWeight = 0;
                return;
            }

            for (WeightedEdge<Vertex> edge : neighborEdges) {
                Vertex source = edge.from();
                Vertex dest = edge.to();
                double weight = edge.weight();

                if (!distToStart.containsKey(dest)) {
                    double inf = Double.POSITIVE_INFINITY;
                    distToStart.put(dest, inf);
                }

                if (!distToEnd.containsKey(dest)) {
                    distToEnd.put(dest, input.estimatedDistanceToGoal(dest, end));
                }

                if (distToStart.get(source) + weight < distToStart.get(dest)) {
                    distToStart.put(dest, distToStart.get(source) + weight);

                    edgeTo.put(dest, source);

                    if (pq.contains(dest)) {
                        pq.changePriority(dest, distToStart.get(dest) + distToEnd.get(dest));
                    } else {
                        pq.add(dest, distToStart.get(dest) + distToEnd.get(dest));
                    }
                }
            }
        }
        outcome = SolverOutcome.UNSOLVABLE;
        solution = new LinkedList<>();
        solutionWeight = 0;
        explorationTime = sw.elapsedTime();
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
        return numStatesExplored;
    }

    public double explorationTime() {
        return explorationTime;
    }
}
