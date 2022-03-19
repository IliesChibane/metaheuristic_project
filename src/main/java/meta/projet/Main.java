package meta.projet;

import meta.projet.classesi.solver.AStar;
import meta.projet.classesi.solver.heuristic.MinMoves;
import meta.projet.classesi.solver.heuristic.MissPlaced;

public class Main {
    public static void main(String[] args) {
        int[] initialStates = {283164705, 364102875, 304165827, 213804675, 420836751, 163408725, 567408321, 527804361};
        int[] solutionsDepth = {5, 10, 13, 18, 20, 24, 30, 30};

        long[] temps_exec = new long[8];

        long start, stop;

        System.out.println("InitialStates ExecutionTime SolutionLevel FoundSolutionLevel NumberOfDevelopedStates OpenedSize ClosedSize");

        for (int j = 0; j < 10; ++j) {
            for (int i = 0; i < initialStates.length; ++i) {
                AStar solver = new AStar(
                    initialStates[i],
                    123804765,
                    new MissPlaced(),
                    -1
                );

                start = System.nanoTime();
                solver.solve();
                stop = System.nanoTime();
                System.out.println(String.format(
                    "%09d %d %d %d %d %d %d",
                    initialStates[i],
                    stop - start,
                    solutionsDepth[i],
                    solver.getSolution().getLevel(),
                    solver.getNumberOfDevelopedStates(),
                    solver.getOpened().size(),
                    solver.getClosed().size()));

                temps_exec[i] += stop - start;
            }
        }

        for (int i = 0; i < initialStates.length; ++i) {
            temps_exec[i] /= 10;
            System.out.println(temps_exec[i]);
        }

        temps_exec = new long[8];
        for (int j = 0; j < 10; ++j) {
            for (int i = 0; i < initialStates.length; ++i) {
                AStar solver = new AStar(
                    initialStates[i],
                    123804765,
                    new MinMoves(),
                    -1
                );

                start = System.nanoTime();
                solver.solve();
                stop = System.nanoTime();
                System.out.println(String.format(
                    "%09d %d %d %d %d %d %d",
                    initialStates[i],
                    stop - start,
                    solutionsDepth[i],
                    solver.getSolution().getLevel(),
                    solver.getNumberOfDevelopedStates(),
                    solver.getOpened().size(),
                    solver.getClosed().size()));

                temps_exec[i] += stop - start;
            }
        }
        
        for (int i = 0; i < initialStates.length; ++i) {
            temps_exec[i] /= 10;
            System.out.println(temps_exec[i]);
        }

    }
}
