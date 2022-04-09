package meta.projet.classesi.solver.heuristic;

public interface Heuristic {
    public int score(short[][] state, int level);
    public void setTargetState(short[][] state);
}
