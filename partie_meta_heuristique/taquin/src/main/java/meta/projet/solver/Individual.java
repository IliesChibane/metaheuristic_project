package meta.projet.solver;

public class Individual {
    private float[] sequence;

    public Individual(float[] sequence) {
        this.sequence = sequence;
    }

    public float[] getSequence() {
        return this.sequence;
    }

    public void print() {
        for (int k = 0; k < getSequence().length; ++k) {
            System.out.print(getSequence()[k] + ", ");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;

        Individual ind = (Individual) obj;

        if (this.getSequence().length != ind.getSequence().length)
            return false;

        for (int i = 0; i < this.getSequence().length; ++i)
            if (this.getSequence()[i] != ind.getSequence()[i])
                return false;
        return true;
    }
}
