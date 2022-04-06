package meta.projet.classesi.solver;


import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashSet;

public class DFS extends Solver {

    private Node Initial;
    private int Final;

    private Node solution;
    private ArrayDeque<Node> opened;
    private HashSet<Node> closed;
    private int depthMax;
    private int numberOfDevelopedStates;
    
        /**
     * DFS constructor.
     * @param initialState: the initial state that the solver starts from
     * @param finalState: the target state that the solver needs to reach 
     * @param depthMax: the maximum depth
     */

    public DFS(int initialState, int finalState, int depthMax){
        this.Initial = new Node(initialState,null,0,0);
        this.Final = finalState;
        this.solution = this.Initial;
        this.closed = new HashSet<Node>();
        this.closed.add(this.Initial);
        this.opened = new ArrayDeque<Node>();
        this.depthMax = depthMax;
        this.numberOfDevelopedStates = 0;
    }
    
        /**
     * This method uses the final node stored in the solution parameters and
     * and run through all the nodes until it reaches the initial node storing
     * each of them in a string so we can display the optimal path from the intial
     * state to the final state
     * @preturn a string containing the optimal path each state is separated by a <--
     */

    public String DisplayResolutionPath()
    {
        String FinalPath = String.format("%09d",Final) + " <-- ";
        Node x = solution;
        
         /**
         * Loop through all the nodes of the optimal path and a <-- between each state to
         *separate them
        */
        while(x != null)
        {
            FinalPath += (x.getParent() == null) ? String.format("%09d",(x.getState())) : String.format("%09d",(x.getState())) + " <-- ";
            x = x.getParent();
        }
        //System.out.println(FinalPath);
        //System.out.println();
        return FinalPath;
    }

    /**
     * This method uses DFS algorithm to find the sequence of actions to reach
     * the finalState from the initialState. The solution if found will be 
     * stored in solution member attribute, while opened and closed attributes
     * will contain the nodes to be developed and the developed nodes
     * respectively.
     * @preturn boolean true if the algorithm found a solution, false otherwise
     */
    
    
    @Override
    public boolean solve() {
        this.numberOfDevelopedStates = 0;
    opened.add(this.solution);    
    while((opened.size() != 0) &&(!closed.contains(new Node(Final, solution, 0, solution.getLevel()+1)))){
        //We devellop a new node and run the loop again until oppened is empty or if found the solution
        solution = opened.removeFirst();//the new solution is the head of the stack
        this.numberOfDevelopedStates = this.numberOfDevelopedStates + 1;
        closed.add(solution);
        if (solution.getLevel() > this.depthMax) continue;
        
         /**
          * Retrieve all the adjacent value of the 0 in the matrix
          * (Check the position method in the Codification class for more details)
          */
        int Allpos = Codification.position(this.solution.getState());
        int zpos = (Allpos / 10000) % 10;
        int LeftCellVal = (Allpos / 1000) % 10, lcv = 0;
        int RightCellVal = (Allpos / 100) % 10, rcv = 0;
        int UpCellVal = (Allpos / 10) % 10, ucv = 0;
        int DownCellVal = Allpos % 10, dcv = 0;
        
        //If the adjacent left cell exist
        if(LeftCellVal != 0){
            //We switch the value of the left cell and the 0 to get a new state
            lcv = Codification.SwitchCell(solution.getState(),zpos,zpos - 1,LeftCellVal);
            //if this state is the final state
            if(lcv == Final)
            {//We add the state to closed and we return true
                closed.add(new Node(Final, solution, 0, solution.getLevel()+1));
                return true;
            }
            else
            {//If the state isn't in closed (not a visited node)
                Node n = new Node(lcv, solution, 0, solution.getLevel()+1);
                if(!closed.contains(n)){ 
                //We add the state to opened
                    opened.addFirst(n);
                }
            }
        }
        //If the adjacent right cell exist
        if(RightCellVal != 0){
            //We switch the value of the right cell and the 0 to get a new state
            rcv = Codification.SwitchCell(solution.getState(),zpos,zpos + 1,RightCellVal);
            //if this state is the final state
            if(rcv == Final)
            {//We add the state to closed and we return true
                closed.add(new Node(Final, solution, 0, solution.getLevel()+1));
                return true;
            }
            else
            {//If the state isn't in closed (not a visited node)
                Node n = new Node(rcv, solution, 0, solution.getLevel()+1);
                if(!closed.contains(n)){
                    //We add the state to opened
                    opened.addFirst(n);
                }
            }
        }
        //If the adjacent upper cell exist
        if(UpCellVal != 0){
            //We switch the value of the upper cell and the 0 to get a new state
            ucv = Codification.SwitchCell(solution.getState(),zpos,zpos - 3,UpCellVal);
            //if this state is the final state
            if(ucv == Final)
            {//We add the state to closed and we return true
                closed.add(new Node(Final, solution, 0, solution.getLevel()+1));
                return true;
            }
            else
            {//If the state isn't in closed (not a visited node)
                Node n = new Node(ucv, solution, 0, solution.getLevel()+1);
                if(!closed.contains(n)){
                    //We add the state to opened
                    opened.addFirst(n);

                }
            }
        }
        //If the adjacent left cell exist
        if(DownCellVal != 0)
        {//We switch the value of the left cell and the 0 to get a new state
            dcv = Codification.SwitchCell(solution.getState(),zpos,zpos + 3,DownCellVal);
            //if this state is the final state
            if(dcv == Final)
            {//We add the state to closed and we return true
                closed.add(new Node(Final, solution, 0, solution.getLevel()+1));
                return true;
            }
            else
            {//If the state isn't in closed (not a visited node)
                Node n = new Node(dcv, solution, 0, solution.getLevel()+1);
                if(!closed.contains(n)){
                     //We add the state to opened
                    opened.addFirst(n);
                }
            }
        }
        
        
    }
        return false;
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
    public int getNumberOfDevelopedStates() {
        return this.numberOfDevelopedStates;
    }
}
