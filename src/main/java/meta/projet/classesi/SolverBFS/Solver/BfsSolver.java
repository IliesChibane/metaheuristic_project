package meta.projet.classesi.SolverBFS.Solver;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import meta.projet.classesi.SolverBFS.Codification.*;
import meta.projet.classesi.solver.Node;
public class BfsSolver {
    
    public static Queue<Node> BFS(Node Initial, int Final, Queue<Node> path,Queue<Node> vpath)
    {
        int Allpos = Codification.position(Initial.getState());
        int LeftCellVal = (Allpos / 1000) % 10, lcv = 0;
        int RightCellVal = (Allpos / 100) % 10, rcv = 0;
        int UpCellVal = (Allpos / 10) % 10, ucv = 0;
        int DownCellVal = Allpos % 10, dcv = 0;
        
        System.out.println("Current Node : "+Initial.getState()+" Level : "+Initial.getLevel());
        System.out.println("Developed children : ");
        if(LeftCellVal != 0){
            lcv = Codification.SwitchCell(Initial.getState(),LeftCellVal);
            System.out.println(lcv);
            if(lcv == Final)
            {
                vpath.add(new Node(Final, Initial, 0, Initial.getLevel()+1));
                return vpath;
            }
            else
            {
                Node n = new Node(lcv, Initial, 0, Initial.getLevel()+1);
                if(!vpath.contains(n)){ 
                
                    path.add(n);
                    vpath.add(n);
                }
            }
        }
        if(RightCellVal != 0){
            rcv = Codification.SwitchCell(Initial.getState(),RightCellVal);
            System.out.println(rcv);
            if(rcv == Final)
            {
                vpath.add(new Node(Final, Initial, 0, Initial.getLevel()+1));
                return vpath;
            }
            else
            {
                Node n = new Node(rcv, Initial, 0, Initial.getLevel()+1);
                if(!vpath.contains(n)){
                    path.add(n);
                    vpath.add(n);
                }
            }
        }
        
        if(UpCellVal != 0){
            ucv = Codification.SwitchCell(Initial.getState(),UpCellVal);
            System.out.println(ucv);
            if(ucv == Final)
            {
                vpath.add(new Node(Final, Initial, 0, Initial.getLevel()+1));
                return vpath;
            }
            else
            {
                Node n = new Node(ucv, Initial, 0, Initial.getLevel()+1);
                if(!vpath.contains(n)){
                    path.add(n);
                    vpath.add(n);
                }
            }
        }

        if(DownCellVal != 0)
        {
            dcv = Codification.SwitchCell(Initial.getState(),DownCellVal);
            System.out.println(dcv);
            if(dcv == Final)
            {
                vpath.add(new Node(Final, Initial, 0, Initial.getLevel()+1));
                return vpath;
            }
            else
            {
                Node n = new Node(dcv, Initial, 0, Initial.getLevel()+1);
                if(!vpath.contains(n)){
                    path.add(n);
                    vpath.add(n);
                }
            }
        }
        
        while((path.size() != 0)&&(!vpath.contains(new Node(Final, Initial, 0, Initial.getLevel()+1))))
        {
            Node state = path.remove();
            BFS(state, Final, path, vpath);
        }

        return vpath;
    }

    public static Stack<Node> ResolutionPath(Queue<Node> vpath)
    {
        Stack<Node> RP = new Stack<Node>();
        LinkedList<Node> inter = (LinkedList<Node>)vpath;
        Node x = inter.getLast();
        RP.push(x);
        while(x.getLevel()!=0)
        {
            x = x.getParent();
            RP.push(x);
        }


        return RP;
    }

    public static void DisplayResolutionPath(Stack<Node> RP)
    {
        System.out.println("Resolution path :");
        String FinalPath = "";
        while(!RP.empty())
        {
            Node x = RP.pop();
            FinalPath += (RP.empty()) ? Integer.toString(x.getState()) : Integer.toString(x.getState()) + " --> ";
        }
        System.out.println(FinalPath);
        System.out.println();
    }

    public static void AllNodeVisited(Queue<Node> vpath)
    {
        System.out.println("Tout les noeuds vistes en utilisant l'algorithme BFS (Breadth first search):");
        String FinalPath = "";
        while(vpath.size() != 0)
        {
            Node x = vpath.remove();
            FinalPath += (vpath.size() == 0) ? Integer.toString(x.getState()) : Integer.toString(x.getState()) + " --> ";
        }
        System.out.println(FinalPath);
        System.out.println();
    }
}
