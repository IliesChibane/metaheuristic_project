package meta.projet.classesi.solver.heuristic;

public class BreadthFirst implements Heuristic {

    //@Override
    public int score(short[][] state, int level) {
        return level;
    }

    public void setTargetState(short[][] targetState) {
    }
}
