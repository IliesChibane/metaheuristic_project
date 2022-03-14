package meta.projet;

import meta.projet.classesi.solver.AStar;
import meta.projet.classesi.solver.Node;
import meta.projet.classesi.solver.heuristic.MissPlaced;
import meta.projet.classesi.solver.heuristic.MinMoves;

import java.util.LinkedList;
import java.util.Queue;

import meta.projet.SolverBFS.Codification.*;
import meta.projet.SolverBFS.Solver.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Choisir un algo de resolution");
        System.out.println("1) Algorithme A*");
        System.out.println("2) Algorithme BFS");
        System.out.println("3) Algorithme DFS");
        int choix = 1;
        switch(choix)
        {
            case 0:
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
                break;

            case 1:
                int InitialState = 283164705, FinalState = 123804765;
                Queue<Integer> path = new LinkedList<Integer>();
                Queue<Integer> vpath = new LinkedList<Integer>();
                vpath.add(InitialState);
                Codification.DisplayAsMAtrix(InitialState);
                System.out.println();
                System.out.println("Resolution en utilisant l'algorithme BFS (Breadth first search):");
                vpath = BfsSolver.BFS(InitialState, FinalState, path, vpath);
                String FinalPath = "";
                while(vpath.size() != 0)
                {
                    int x = vpath.remove();
                    FinalPath += (x == FinalState) ? Integer.toString(x) : Integer.toString(x) + " --> ";
                }
                System.out.println(FinalPath);
                System.out.println();
                Codification.DisplayAsMAtrix(FinalState);
                break;
            case 2:
                break;
        }
    }
}
