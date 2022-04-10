package meta.projet.solver.heuristic;

public interface Heuristic {
    public int score(short[][] state);
    public void setTargetState(short[][] state);
}
