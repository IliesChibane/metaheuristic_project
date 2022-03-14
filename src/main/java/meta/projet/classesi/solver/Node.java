/**
 * TODOs :
 * - implement a proper hashCode method 
 */

package meta.projet.classesi.solver;

public class Node implements Comparable<Node> {
    private int state;
    private Node parent;
    private int score;
    private int level;
    
    public Node(int state, Node parent, int score, int level) {
        this.state = state;
        this.parent = parent;
        this.score = score;
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || o.getClass() != this.getClass())
            return false;

        Node other = (Node) o;
        
        return this.state == other.state
            && this.score == other.score;
    }

    @Override
    public int compareTo(Node o) {
        return this.getScore() - o.getScore();
    }

    @Override
    public int hashCode() {
        return this.state;
    }

    public int getState() {
        return this.state;
    }
    public Node getParent() {
        return this.parent;
    }
    public int getScore() {
        return this.score;
    }
    public int getLevel() {
        return this.level;
    }
}
