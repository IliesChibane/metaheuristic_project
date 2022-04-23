package meta.projet;

import java.io.FileWriter;

import meta.projet.solver.GASolver;
import meta.projet.solver.heuristic.MinMoves;

public class App {
    public static void main( String[] args ) throws Exception {
        short[] solutionsLengths = {5, 10, 13, 18, 20, 24, 30, 30};

        short[][][] initialStates = {
            {{2, 8, 3}, {1, 6, 4}, {7, 0, 5}}, // 5
            {{3, 6, 4}, {1, 0, 2}, {8, 7, 5}}, // 10
            {{3, 0, 4}, {1, 6, 5}, {8, 2, 7}}, // 13
            {{2, 1, 3}, {8, 0, 4}, {6, 7, 5}}, // 18
            {{4, 2, 0}, {8, 3, 6}, {7, 5, 1}}, // 20
            {{1, 6, 3}, {4, 0, 8}, {7, 2, 5}}, // 24
            {{5, 6, 7}, {4, 0, 8}, {3, 2, 1}}, // 30
            {{5, 2, 7}, {8, 0, 4}, {3, 6, 1}}, // 30
        };

        int[] initialStatesInt = {
            283164705, // 5
            364102875, // 10
            304165827, // 13
            213804675, // 18
            420836751, // 20
            163408725, // 24
            567408321, // 30
            527804361 // 30
        };

        short[][] targetState = {{1, 2, 3}, {8, 0, 4}, {7, 6, 5}};

        int[] populationSizes = {10, 50, 100};
        int[] numberOfCrossovers = {20, 30, 50};
        int[] numberOfMutations = {20, 50, 100, 150};
        int[] tolerances = {30, 60, 100};

        long startTime, endTime;

        FileWriter results = new FileWriter("results.csv");

        System.out.println("initial state; best solution length; population size; initial sequence length; max iterations; selection ratio; number of crossovers; number of mutations; crossover ratio; tolerance; solution best fitness; solution iteration; solution length; execution time");
        results.write("initial state; best solution length; population size; initial sequence length; max iterations; selection ratio; number of crossovers; number of mutations; crossover ratio; tolerance; solution best fitness; solution iteration; solution length; execution time\n");
        for (int i = 0; i < initialStates.length; ++i) {
            for (int j = 0; j < populationSizes.length; ++j) {
                for (int k = 0; k < numberOfCrossovers.length; ++k) {
                    for (int l = 0; l < numberOfMutations.length; ++l) {
                        for (int m = 0; m < tolerances.length; ++m) {
                            GASolver solver = new GASolver(
                                initialStates[i], // initialState
                                targetState, // targetState
                                populationSizes[j], // populationSize
                                new MinMoves(), // heuristic
                                1, // initialSequenceLength
                                5000, // maxIter
                                0.5f, // selectionRation
                                numberOfCrossovers[k], // numberOfCrossovers
                                numberOfMutations[l], // numberOfMutations
                                0.5f, // crossoverRatio
                                tolerances[m] // tolerance 
                            );
                            startTime = System.nanoTime();
                            solver.solve();
                            endTime = System.nanoTime();

                            System.out.println(initialStatesInt[i] + "; " +
                                solutionsLengths[i] + "; " +
                                populationSizes[j] + "; " +
                                "1; " +
                                "5000; " +
                                "0.5; " +
                                numberOfCrossovers[k] + "; " +
                                numberOfMutations[l] + "; " +
                                "0.5; " +
                                tolerances[m] + "; " +
                                solver.getBestFitness() + "; " +
                                solver.getSolutionIteration() + "; " +
                                solver.getSolution().length + "; " +
                                (endTime - startTime)
                            );
                            results.write(initialStatesInt[i] + "; " +
                                solutionsLengths[i] + "; " +
                                populationSizes[j] + "; " +
                                "1; " +
                                "5000; " +
                                "0.5; " +
                                numberOfCrossovers[k] + "; " +
                                numberOfMutations[l] + "; " +
                                "0.5; " +
                                tolerances[m] + "; " +
                                solver.getBestFitness() + "; " +
                                solver.getSolutionIteration() + "; " +
                                solver.getSolution().length + "; " +
                                (endTime - startTime) + "\n"
                            );
                        }
                    }
                }
            }
        }

        results.close();


    }
}
