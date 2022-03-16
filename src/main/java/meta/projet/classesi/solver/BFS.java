package meta.projet.classesi.solver;


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends Solver {

    private Node Initial;
    private int Final;

    private Node solution;
    private Queue<Node> opened;
    private HashSet<Node> closed;

    public BFS(int initialState, int finalState){
        this.Initial = new Node(initialState,null,0,0);
        this.Final = finalState;
        this.solution = this.Initial;
        this.closed = new HashSet<Node>();
        this.closed.add(this.Initial);
        this.opened = new LinkedList<Node>();
    }

    public void DisplayResolutionPath()
    {
        System.out.println("Resolution path :");
        String FinalPath = "";
        Node x = solution;
        while(x != null)
        {
            FinalPath += (x.getParent() == null) ? Integer.toString(x.getState()) : Integer.toString(x.getState()) + " <-- ";
            x = x.getParent();
        }
        System.out.println(FinalPath);
        System.out.println();
    }

    public void DisplayClosed()
    {
        System.out.println("Affichage de l'ensemble ferme");
        String FinalPath = "";
        Iterator<Node> itr = closed.iterator();
        while(itr.hasNext())
        {
            FinalPath += Integer.toString(itr.next().getState()) + ", ";
        }
        FinalPath = FinalPath.substring(0, FinalPath.length() - 2);  
        System.out.println("{"+FinalPath+"}");
        System.out.println();
    }

    public void DisplayOpened()
    {
        System.out.println("Affichage de l'ensemble ouvert :");
        String FinalPath = "";
        Queue<Node> itr = this.opened;
        while(itr.size() != 0)
        {
            Node x = itr.remove();
            FinalPath += Integer.toString(x.getState()) + ", ";
        }
        FinalPath = FinalPath.substring(0, FinalPath.length() - 2);  
        System.out.println("{"+FinalPath+"}");
        System.out.println();
    }

    @Override
    public boolean solve() {
        int Allpos = Codification.position(this.solution.getState());
        int LeftCellVal = (Allpos / 1000) % 10, lcv = 0;
        int RightCellVal = (Allpos / 100) % 10, rcv = 0;
        int UpCellVal = (Allpos / 10) % 10, ucv = 0;
        int DownCellVal = Allpos % 10, dcv = 0;
        
        System.out.println("Current Node : "+this.solution.getState()+" Level : "+this.solution.getLevel());
        System.out.println("Developed children : ");
        if(LeftCellVal != 0){
            lcv = Codification.SwitchCell(solution.getState(),LeftCellVal);
            System.out.println(lcv);
            if(lcv == Final)
            {
                closed.add(new Node(Final, solution, 0, solution.getLevel()+1));
                return true;
            }
            else
            {
                Node n = new Node(lcv, solution, 0, solution.getLevel()+1);
                if(!closed.contains(n)){ 
                
                    opened.add(n);
                    closed.add(n);
                }
            }
        }
        if(RightCellVal != 0){
            rcv = Codification.SwitchCell(solution.getState(),RightCellVal);
            System.out.println(rcv);
            if(rcv == Final)
            {
                closed.add(new Node(Final, solution, 0, solution.getLevel()+1));
                return true;
            }
            else
            {
                Node n = new Node(rcv, solution, 0, solution.getLevel()+1);
                if(!closed.contains(n)){
                    opened.add(n);
                    closed.add(n);
                }
            }
        }
        
        if(UpCellVal != 0){
            ucv = Codification.SwitchCell(solution.getState(),UpCellVal);
            System.out.println(ucv);
            if(ucv == Final)
            {
                closed.add(new Node(Final, solution, 0, solution.getLevel()+1));
                return true;
            }
            else
            {
                Node n = new Node(ucv, solution, 0, solution.getLevel()+1);
                if(!closed.contains(n)){
                    opened.add(n);
                    closed.add(n);
                }
            }
        }

        if(DownCellVal != 0)
        {
            dcv = Codification.SwitchCell(solution.getState(),DownCellVal);
            System.out.println(dcv);
            if(dcv == Final)
            {
                closed.add(new Node(Final, solution, 0, solution.getLevel()+1));
                return true;
            }
            else
            {
                Node n = new Node(dcv, solution, 0, solution.getLevel()+1);
                if(!closed.contains(n)){
                    opened.add(n);
                    closed.add(n);
                }
            }
        }
        
        boolean b = false;
        while((opened.size() != 0)&&(!closed.contains(new Node(Final, solution, 0, solution.getLevel()+1))))
        {
            solution = opened.remove();
            
            b = solve();
        }

        return b;
    }

    @Override
    public Node getSolution() {
        return this.solution;
    }

    @Override
    public Collection<Node> getOpened() {
        return this.opened;
    }

    @Override
    public Collection<Node> getClosed() {
        return this.closed;
    }
}
