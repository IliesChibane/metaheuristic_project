package meta.projet.solver.heuristic;


/**
 * The minimal sum of movements of each square from 1 to 8, to reach its right
 * place like in the target state.
 */
public class MinMoves implements Heuristic {
    private short[][] targetState;

    public MinMoves() {
    }

    public MinMoves(short[][] targetState) {
        this.targetState = targetState;
    }

    //@Override
    public int score(short[][] state) {
        int sum = 0;
        for (int i = 0; i < this.targetState.length; ++i)
            for (int j = 0; j < this.targetState[0].length; ++j)
                // for each number in target
                // find manhaten distance between
                // it's correct and current position
                if (targetState[i][j] != 0)
                    for (int k = 0; k < state.length; ++k)
                        for (int l = 0; l < state[0].length; ++l)
                            if (targetState[i][j] == state[k][l])
                                sum = sum + Math.abs(i - k) + Math.abs(j - l);
        return sum;
    }

    public void setTargetState(short[][] targetState) {
        this.targetState = targetState;
    }
}
