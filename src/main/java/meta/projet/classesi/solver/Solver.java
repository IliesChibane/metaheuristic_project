package meta.projet.classesi.solver;

import java.util.Collection;

public abstract class Solver {
    
    private Node solution;
    private Collection<Node> opened;
    private Collection<Node> closed;

    public abstract boolean solve();
    public abstract Node getSolution();
    public abstract Collection<Node> getOpened();
    public abstract Collection<Node> getClosed();
}
