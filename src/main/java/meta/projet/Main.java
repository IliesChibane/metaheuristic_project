package meta.projet;

<<<<<<< HEAD
import meta.projet.classesi.solver.AStar;
import meta.projet.classesi.solver.DFS;
import meta.projet.classesi.solver.heuristic.MinMoves;
import meta.projet.classesi.solver.heuristic.MissPlaced;
=======
//import meta.projet.classesi.solver.AStar;
import meta.projet.classesi.solver.BFS;
//import meta.projet.classesi.solver.heuristic.MinMoves;
//import meta.projet.classesi.solver.heuristic.MissPlaced;
>>>>>>> 3baf00a978b45563ba29815e8d08bb75209e2abd

public class Main {
    public static void main(String[] args) {
        int[] initialStates = {283164705, 364102875, 304165827, 213804675, 420836751, 163408725, 567408321, 527804361};
        int[] solutionsDepth = {5, 10, 13, 18, 20, 24, 30, 30};

        long[] temps_exec = new long[8];

        long start, stop;
        int b = 0;

        System.out.println("Initial--++States ExecutionTime SolutionLevel FoundSolutionLevel NumberOfDevelopedStates OpenedSize ClosedSize");

        for (int j = 0; j < 10; ++j) {
            for (int i = 0; i < initialStates.length; ++i) {
<<<<<<< HEAD
                DFS solver = new DFS(
                    initialStates[i],
                    123804765,
                    150
=======
                BFS solver = new BFS(
                    initialStates[i],
                    123804765
>>>>>>> 3baf00a978b45563ba29815e8d08bb75209e2abd
                );

                start = System.nanoTime();
                solver.solve();
                stop = System.nanoTime();
                System.out.println(String.format(
                    "%09d %d %d %d %d %d %d",
                    initialStates[i],
                    stop - start,
                    solutionsDepth[i],
                    solver.getSolution().getLevel() + 1,
                    solver.getNumberOfDevelopedStates(),
                    solver.getOpened().size(),
                    solver.getClosed().size()));
                    b++;

                temps_exec[i] += stop - start;
            }
            System.out.println("---------------------------------");
        }

        for (int i = 0; i < initialStates.length; ++i) {
            temps_exec[i] /= 10;
            System.out.println(temps_exec[i]);
        }
<<<<<<< HEAD
        System.out.println(b);

=======
>>>>>>> 3baf00a978b45563ba29815e8d08bb75209e2abd
    }
}
