package meta.projet;

import meta.projet.solver.GASolver;
import meta.projet.solver.heuristic.MinMoves;

public class App {
    public static void main( String[] args ) {
        //short[][] initialState = {{2, 8, 3}, {1, 6, 4}, {7, 0, 5}}; // 5
        //short[][] initialState = {{3, 6, 4}, {1, 0, 2}, {8, 7, 5}}; // 10
        short[][] initialState = {{3, 0, 4}, {1, 6, 5}, {8, 2, 7}}; // 13
        //short[][] initialState = {{2, 1, 3}, {8, 0, 4}, {6, 7, 5}}; // 18
        //short[][] initialState = {{4, 2, 0}, {8, 3, 6}, {7, 5, 1}}; // 20
        //short[][] initialState = {{1, 6, 3}, {4, 0, 8}, {7, 2, 5}}; // 24
        //short[][] initialState = {{5, 6, 7}, {4, 0, 8}, {3, 2, 1}}; // 30
        //short[][] initialState = {{5, 2, 7}, {8, 0, 4}, {3, 6, 1}}; // 30
        short[][] targetState = {{1, 2, 3}, {8, 0, 4}, {7, 6, 5}};

        GASolver solver = new GASolver(
            initialState, // initialState
            targetState, // targetState
            15, // populationSize
            new MinMoves(), // heuristic
            4, // initialSequenceLength
            100, // maxIter
            0.5f, // selectionRation
            10, // numberOfCrossovers
            40, // numberOfMutations
            0.5f // crossoverRatio
        );

        solver.solve();
    }
}
