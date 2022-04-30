package meta.projet.solver;

public class Particle {
    private float[] position;
    private float velocity;
    private float[] pBest;
    private float bestEvaluation;


    public Particle(float[] position, float velocity) {
        this.position = position;
        this.velocity = velocity;
        this.pBest = position;
        this.bestEvaluation = positionMean();
    }

    public float[] getPosition() {
        return this.position;
    }

    public void setPosition(float[] position) {
        this.position = position;
        if(positionMean() > this.bestEvaluation)
        {
            this.pBest = position;
            this.bestEvaluation = positionMean();
        }
    }

    public float positionMean() {
        float sum = 0;
        for (int i = 0; i < this.position.length; ++i) {
            sum += this.position[i];
        }
        return sum / this.position.length;
    }

    public float getVelocity() {
        return this.velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public float[] getP_best() {
        return this.pBest;
    }

    public void setP_best(float[] pBest) {
        this.pBest = pBest;
    }

    public float getBestEvaluation() {
        return this.bestEvaluation;
    }

    public void setBestEvaluation(float best_evaluation) {
        this.bestEvaluation = best_evaluation;
    }
}