package meta.projet.classesi.solver.heuristic;

/**
 * The number of squares from 1 to 8 that are miss placed with respect to 
 * the goal.
 */
public class MissPlaced implements Heuristic {
    private short[][] targetState;

    public MissPlaced() {
    }

    public MissPlaced(short[][] targetState) {
        this.targetState = targetState;
    }

    //@Override
    public int score(short[][] state, int level) {
        int cpt = 0;
        for (int i = 0; i < this.targetState.length; ++i)
            for (int j = 0; j < this.targetState[0].length; ++j)
                if (this.targetState[i][j] != 0 
                        && this.targetState[i][j] != state[i][j])
                    cpt = cpt + 1;
        return cpt + level;
    }

    public void setTargetState(short[][] targetState) {
        this.targetState = targetState;
    }
}
