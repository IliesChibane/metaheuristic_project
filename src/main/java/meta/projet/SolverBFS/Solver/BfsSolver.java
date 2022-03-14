package meta.projet.SolverBFS.Solver;

import java.util.Queue;
import meta.projet.SolverBFS.Codification.*;
public class BfsSolver {
    
    public static Queue<Integer> BFS(int Initial, int Final, Queue<Integer> path,Queue<Integer> vpath)
    {
        int Allpos = Codification.position(Initial);
        int LeftCellVal = (Allpos / 1000) % 10, lcv = 0;
        int RightCellVal = (Allpos / 100) % 10, rcv = 0;
        int UpCellVal = (Allpos / 10) % 10, ucv = 0;
        int DownCellVal = Allpos % 10, dcv = 0;
        
        System.out.println("Current Node : "+Initial);
        System.out.println("Developed children : ");
        if(LeftCellVal != 0){
            lcv = Codification.SwitchCell(Initial,LeftCellVal);
            System.out.println(lcv);
            if(lcv == Final)
            {
                vpath.add(Final);
                return vpath;
            }
            else
            {
                if(!vpath.contains(lcv)){ 
                
                    path.add(lcv);
                    vpath.add(lcv);
                }
            }
        }
        if(RightCellVal != 0){
            rcv = Codification.SwitchCell(Initial,RightCellVal);
            System.out.println(rcv);
            if(rcv == Final)
            {
                vpath.add(Final);
                return vpath;
            }
            else
            {
                if(!vpath.contains(rcv)){
                    path.add(rcv);
                    vpath.add(rcv);
                }
            }
        }
        
        if(UpCellVal != 0){
            ucv = Codification.SwitchCell(Initial,UpCellVal);
            System.out.println(ucv);
            if(ucv == Final)
            {
                vpath.add(Final);
                return vpath;
            }
            else
            {
                if(!vpath.contains(ucv)){
                    path.add(ucv);
                    vpath.add(ucv);
                }
            }
        }

        if(DownCellVal != 0)
        {
            dcv = Codification.SwitchCell(Initial,DownCellVal);
            System.out.println(dcv);
            if(dcv == Final)
            {
                vpath.add(Final);
                return vpath;
            }
            else
            {
                if(!vpath.contains(dcv)){
                    path.add(dcv);
                    vpath.add(dcv);
                }
            }
        }
        
        while((path.size() != 0)&&(!vpath.contains(Final)))
        {
            int state = path.remove();
            BFS(state, Final, path, vpath);
        }

        return vpath;
    }
}
