package meta.projet;

import meta.projet.classesi.solver.AStar;
import meta.projet.classesi.solver.Node;
import meta.projet.classesi.solver.heuristic.MissPlaced;
import meta.projet.classesi.solver.heuristic.MinMoves;

public class Main {
    public static void main(String[] args) {
        AStar solver = new AStar(
            283164705,
            123804765,
            new MinMoves()
        );

        Node solution = solver.solve();
        if (solution != null) {
            System.out.println("done");
            for (Node node = solution; node != null; node = node.getParent()) {
                System.out.print(String.format("%09d ", node.getState()));
                if (node.getParent() != null)
                    System.out.print("<--");
            } 
            System.out.println();
        } else {
            System.out.println("nope");
        }
    }
}
