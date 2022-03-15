package meta.projet;

import meta.projet.classesi.solver.AStar;
import meta.projet.classesi.solver.Node;
import meta.projet.classesi.solver.heuristic.MinMoves;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import meta.projet.classesi.SolverBFS.Codification.*;
import meta.projet.classesi.SolverBFS.Solver.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Choisir un algo de resolution");
        System.out.println("1) Algorithme A*");
        System.out.println("2) Algorithme BFS");
        System.out.println("3) Algorithme DFS");
        int choix;
        Scanner sc = new Scanner(System.in);
        choix = sc.nextInt();
        switch(choix)
        {
            case 1:
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

            case 2:
                int InitialState = 283164705, FinalState = 123804765;
                Node InitialNode = new Node(InitialState, null, 0, 0);
                Queue<Node> path = new LinkedList<Node>();
                Queue<Node> vpath = new LinkedList<Node>();
                vpath.add(InitialNode);
                Codification.DisplayAsMAtrix(InitialState);
                System.out.println();
                vpath = BfsSolver.BFS(InitialNode, FinalState, path, vpath);
                System.out.println();
                BfsSolver.DisplayResolutionPath(BfsSolver.ResolutionPath(vpath));
                BfsSolver.AllNodeVisited(vpath);
                Codification.DisplayAsMAtrix(FinalState);
                break;
            case 3:
                break;
        }
        sc.close();
    }
}
