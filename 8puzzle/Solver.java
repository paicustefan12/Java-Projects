/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {

    private SearchNode solution;
    private MinPQ<SearchNode> PQ;
    private int minimumMoves;
    private boolean isSolvable;

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("arg null");
        PQ = new MinPQ<>();
        PQ.insert(new SearchNode(initial, 0, null));
        while (true) {
            SearchNode root = PQ.delMin();
            if (root.isGoal()) {
                isSolvable = true;
                solution = root;
                break;
            }

            if (root.getCurrent().twin().isGoal()) {
                isSolvable = false;
                break;
            }

            Iterable<Board> neighbors;
            neighbors = root.getCurrent().neighbors();
            for (Board item : neighbors) {
                SearchNode aux = new SearchNode(item, root.getMoves() + 1, root);
                if (root.previous != null) {
                    if (!aux.getCurrent().equals(root.previous.getCurrent())) {
                        PQ.insert(aux);
                    }
                }
                else PQ.insert(aux);
            }
        }
    }

    public boolean isSolvable() {
        return isSolvable;
    }


    public Iterable<Board> solution() {
        if (!isSolvable())
            return null;
        Stack<Board> sol = new Stack<>();
        SearchNode root = solution;
        while (root != null) {
            sol.push(root.getCurrent());
            root = root.previous;
        }
        return sol;
    }

    public int moves() {
        SearchNode root = solution;
        minimumMoves = 0;
        while (root != null) {
            root = root.previous;
            minimumMoves++;
        }
        return minimumMoves - 1;
    }

    private class SearchNode implements Comparable<SearchNode> {

        private Board current;
        private SearchNode previous;
        private int moves;

        public SearchNode(Board current, int moves, SearchNode previous) {
            this.current = current;
            this.previous = previous;
            this.moves = moves;
        }

        public int priority() {
            return current.manhattan() + moves;
        }

        public int getMoves() {
            return moves;
        }

        public boolean isGoal() {
            return current.isGoal();
        }

        public Board getCurrent() {
            return current;
        }

        public int compareTo(SearchNode searchNode) {
            return Integer.compare(this.priority(), searchNode.priority());
        }
    }

    public static void main(String[] args) {
        // In in = new In(args[0]);
        // int n = in.readInt();
        // int[][] tiles = new int[n][n];
        // for (int i = 0; i < n; i++)
        //     for (int j = 0; j < n; j++)
        //         tiles[i][j] = in.readInt();
        // Board initial = new Board(tiles);
        // StdOut.println("Hamming: " + initial.hamming());
        // StdOut.println("Manhatan: " + initial.manhattan());
        // StdOut.println("Twin: " + initial.twin());
        //
        // // solve the puzzle
        // Solver solver = new Solver(initial);
        //
        // // print solution to standard output
        // if (!solver.isSolvable())
        //     StdOut.println("No solution possible");
        // else {
        //     StdOut.println("Minimum number of moves = " + solver.moves());
        //     for (Board board : solver.solution())
        //         StdOut.println(board);
        // }
    }
}
