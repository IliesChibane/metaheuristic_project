package meta.projet.classesi.solver;

import meta.projet.classesi.solver.heuristic.Heuristic;

import java.util.TreeSet;
import java.util.HashSet;

public class AStar implements Solver {
    private int initialState;
    private int finalState;
    private Heuristic heuristic;

    private TreeSet<Node> opened;
    private HashSet<Node> closed;

    public AStar(int initialState, int finalState, Heuristic heuristic) {
        this.initialState = initialState;
        this.finalState = finalState;
        this.heuristic = heuristic;

        short[][] targetState = new short[3][3];
        intToMatrix(this.finalState, targetState);
        this.heuristic.setTargetState(targetState);
    }

    public Node solve() {
        Node currentNode;
        short[][] matrixState = new short[3][3];
        Node[] children;

        this.opened = new TreeSet<Node>();  
        this.closed = new HashSet<Node>();  

        intToMatrix(this.initialState, matrixState);
        this.opened.add(
            new Node(
                this.initialState, 
                null, 
                this.heuristic.score(matrixState, 0),
                0
            )
        );

        // main loop
        while (!this.opened.isEmpty()) {
            currentNode = this.opened.pollFirst(); 

            System.out.println(String.format("Current Node : %09d with level %d and score %d", 
                        currentNode.getState(), currentNode.getLevel(), currentNode.getScore()));

            // test if the current node is a goal
            if (currentNode.getState() == this.finalState) {
                return currentNode;
            }

            // develop children of current node
            if (!this.closed.contains(currentNode)) {
                this.closed.add(currentNode);

                // generate children
                intToMatrix(currentNode.getState(), matrixState);
                children = getChildren(matrixState, currentNode);
                System.out.println("Developed children : ");
                for (Node child : children) {
                    if (child != null) {
                        System.out.println(String.format("%09d (%d)", child.getState(), child.getScore()));
                        this.opened.add(child);
                    }
                }
            }
        }

        return null;
    }

    public Node[] getChildren(short[][] state, Node parent) {
        Node[] children = new Node[4];
        int zi = 0, zj = 0;
        
        // find 0 position
        for (int i = 0; i < state.length; ++i) {
            for (int j = 0; j < state[0].length; ++j) {
                if (state[i][j] == 0) {
                    zi = i;
                    zj = j;
                }
            }
        }

        // up
        if (zi != 0) {
            state[zi][zj] = state[zi - 1][zj];
            state[zi - 1][zj] = 0;
            children[0] = new Node(
                matrixToInt(state),
                parent,
                this.heuristic.score(state, parent.getLevel() + 1),
                parent.getLevel() + 1
            );
            state[zi - 1][zj] = state[zi][zj];
            state[zi][zj] = 0;
        }

        // down
        if (zi != state.length - 1) {
            state[zi][zj] = state[zi + 1][zj];
            state[zi + 1][zj] = 0;
            children[1] = new Node(
                matrixToInt(state),
                parent,
                this.heuristic.score(state, parent.getLevel() + 1),
                parent.getLevel() + 1
            );
            state[zi + 1][zj] = state[zi][zj];
            state[zi][zj] = 0;
        }
        
        // left
        if (zj != 0) {
            state[zi][zj] = state[zi][zj - 1];
            state[zi][zj - 1] = 0;
            children[2] = new Node(
                matrixToInt(state),
                parent,
                this.heuristic.score(state, parent.getLevel() + 1),
                parent.getLevel() + 1
            );
            state[zi][zj - 1] = state[zi][zj];
            state[zi][zj] = 0;
        }
        
        // right
        if (zj != state.length - 1) {
            state[zi][zj] = state[zi][zj + 1];
            state[zi][zj + 1] = 0;
            children[3] = new Node(
                matrixToInt(state),
                parent,
                this.heuristic.score(state, parent.getLevel() + 1),
                parent.getLevel() + 1
            );
            state[zi][zj + 1] = state[zi][zj];
            state[zi][zj] = 0;
        }

        return children;
    }

    public static void intToMatrix(int state, short[][] matrix) {
        for (int i = matrix.length - 1; i >= 0; --i) {
            for (int j = matrix[0].length - 1; j >= 0 ; --j) {
                matrix[i][j] = (short)(state % 10);
                state = state / 10;
            }
        }
    }
    
    public static int matrixToInt(short[][] matrix) {
        int p = matrix.length * matrix[0].length - 1;
        int state = 0;

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                state = state + (int)Math.pow(10, p) * matrix[i][j];
                p = p - 1;
            }
        }
        return state;
    }
}
