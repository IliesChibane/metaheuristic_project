package meta.projet;

import meta.projet.classesi.solver.AStar;
import meta.projet.classesi.solver.DFS;
import meta.projet.classesi.solver.BFS;
import meta.projet.classesi.solver.Codification;
import meta.projet.classesi.solver.Node;
import meta.projet.classesi.solver.heuristic.MissPlaced;

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
                    283164705,
                    123804765,
                    new MissPlaced(),
                    -1
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
                System.out.println("Opened size : " + solver.getOpened().size() + "\nClosed size : " + solver.getClosed().size());
                break;

            case 2:
                int InitialState = 283164705, FinalState = 123804765;
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
            int InitState = 283164705, LastState = 123804765;
            DFS dfss = new DFS(InitState, LastState,4); 
            Codification.DisplayAsMAtrix(InitState);
            System.out.println();
            boolean bool = dfss.solve();
            System.out.println(bool);
            if(bool){
            System.out.println();
            dfss.DisplayResolutionPath();
            dfss.DisplayClosed();
            dfss.DisplayOpened();
            Codification.DisplayAsMAtrix(LastState);
            }
                break;
        }
        sc.close();
    }
}
