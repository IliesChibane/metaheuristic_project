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
        
        return this.state == other.state;
    }

    //@Override
    public int compareTo(Node o) {
        return this.getScore() - o.getScore();
    }

    @Override
    public int hashCode() {
        //return this.state;
        /**
         * the 1st digit divids the array into 9 chunks of 8! numbers
         *  the 2nd digit divids the array into 8 chunks of 7! numbers
         *   the 3rd ..                          7 chunks of 6! numbers
         *    ...
         *     the 8th digit divids the array into 3 chunks of 1! numbers
         *      the 9th digit 
         */

        int tmp = this.state;

        // 8!, 7!, 6!, 5!, 4!, 3!, 2!, 1!
        int[] chunkSize = {40320, 5040, 720, 120, 24, 6, 2, 1};
        short[] digits = new short[8];
        int key = 0;
        int chunkIndex;

        for (int i = 0; i < 8; ++i) {
            digits[i] = (short)(tmp % 10);
            tmp = tmp / 10;

            chunkIndex = digits[i];

            for (int j = i - 1; j >= 0; --j)
                if (digits[j] < digits[i])
                    --chunkIndex;

            key = key + chunkSize[i] * chunkIndex;    
        }

        return key;
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
