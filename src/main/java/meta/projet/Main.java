package meta.projet;

import meta.projet.classesi.solver.AStar;
import meta.projet.classesi.solver.BFS;
import meta.projet.classesi.solver.Codification;
import meta.projet.classesi.solver.Node;
import meta.projet.classesi.solver.heuristic.MinMoves;

import java.util.Scanner;


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
                    183264705,
                    123804765,
                    new MinMoves()
                );
                if (solver.solve()) {
                    System.out.println("done");
                    for (Node node = solver.getSolution(); node != null; node = node.getParent()) {
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
                int InitialState = 183264705, FinalState = 123804765;
                BFS bfss = new BFS(InitialState, FinalState); 
                Codification.DisplayAsMAtrix(InitialState);
                System.out.println();
                if(bfss.solve()){
                System.out.println();
                bfss.DisplayResolutionPath();
                bfss.DisplayClosed();
                bfss.DisplayOpened();
                Codification.DisplayAsMAtrix(FinalState);
                } else {
                    System.out.println("No solutions found");
                }
                break;
            case 3:
                break;
        }
        sc.close();
    }
}
