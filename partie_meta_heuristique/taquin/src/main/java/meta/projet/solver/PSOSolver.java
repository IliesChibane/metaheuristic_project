package meta.projet.solver;

import java.util.LinkedList;
import java.util.Random;

public class PSOSolver {
    private float w, c1,c2;
    private float[] gBest;
    private short currentBestEvaluation;

    private int numParticles;
    private int sequenceSize;
    private int maxSequenceSize;
    private int maxIteration;
    private int tol;
    
    private LinkedList<Particle> particles;
    private float initialVelocity;

    private short[][] initialState;
    private short[][] targetState;

    private static int[][] numberOfMovesPerTile;
    private static Movement[][][] authorizedMovesPerTile;
    private static Movement lastMove;

    private int solutionIteration;

    static {
        numberOfMovesPerTile = new int[][]{{2, 3, 2}, {3, 4, 3}, {2, 3, 2}};

        authorizedMovesPerTile = new Movement[3][3][4];
        authorizedMovesPerTile[0][0][0] = Movement.RIGHT;
        authorizedMovesPerTile[0][0][1] = Movement.DOWN;

        authorizedMovesPerTile[0][1][0] = Movement.RIGHT;
        authorizedMovesPerTile[0][1][1] = Movement.DOWN;
        authorizedMovesPerTile[0][1][2] = Movement.LEFT;

        authorizedMovesPerTile[0][2][0] = Movement.DOWN;
        authorizedMovesPerTile[0][2][1] = Movement.LEFT;

        authorizedMovesPerTile[1][0][0] = Movement.UP;
        authorizedMovesPerTile[1][0][1] = Movement.RIGHT;
        authorizedMovesPerTile[1][0][2] = Movement.DOWN;

        authorizedMovesPerTile[1][1][0] = Movement.UP;
        authorizedMovesPerTile[1][1][1] = Movement.RIGHT;
        authorizedMovesPerTile[1][1][2] = Movement.DOWN;
        authorizedMovesPerTile[1][1][3] = Movement.LEFT;

        authorizedMovesPerTile[1][2][0] = Movement.UP;
        authorizedMovesPerTile[1][2][1] = Movement.DOWN;
        authorizedMovesPerTile[1][2][2] = Movement.LEFT;

        authorizedMovesPerTile[2][0][0] = Movement.UP;
        authorizedMovesPerTile[2][0][1] = Movement.RIGHT;

        authorizedMovesPerTile[2][1][0] = Movement.UP;
        authorizedMovesPerTile[2][1][1] = Movement.RIGHT;
        authorizedMovesPerTile[2][1][2] = Movement.LEFT;

        authorizedMovesPerTile[2][2][0] = Movement.UP;
        authorizedMovesPerTile[2][2][1] = Movement.LEFT;
    }

    /**
     * 
     * @param initialState
     * @param targetState
     * @param w
     * @param c1
     * @param c2
     * @param maxIteration
     * @param numParticles
     * @param initialVelocity
     * @param heuristic
     * @param tol
     */
    public PSOSolver(short[][] initialState, short[][] targetState, float w, float c1, float c2, int maxIteration, int numParticles, float initialVelocity, int tol) {
        this.initialState = initialState;
        this.targetState = targetState;
        this.w = w;
        this.c1 = c1;
        this.c2 = c2;
        this.maxIteration = maxIteration;
        this.numParticles = numParticles;
        this.initialVelocity = initialVelocity;
        this.tol = tol;
        this.sequenceSize = 5;
        this.currentBestEvaluation = -1;
        this.maxSequenceSize = 500;
    }

    /**
     * This method initialize randomly the particles that we will use in our algorithm
     */
    public void initParticles()
    {
        particles = new LinkedList<>();
        Random rand = new Random(42);

        for(int i = 0; i < numParticles; ++i)
        {
            //each particle will contain a sequence of movements 
            float[] sequence = new float[maxSequenceSize];
            for(int j = 0; j < sequenceSize; ++j)
            {
                //each movement will be represented by a float 
                sequence[j] = rand.nextFloat();
            }
            //We create our particle using the random sequence of movement we just generated and the initial velocity
            Particle p = new Particle(sequence, initialVelocity);
            //We evaluate our new generated particle
            short evaluation = fitnessFunction(p);

            /*We see if the evaluation of our current particle is better than the current best evaluation
             which in this case we replace it so at the end we will know our global best particle of our 
             random generation*/
            if(evaluation > currentBestEvaluation)
            {
                gBest = p.getPosition();
                currentBestEvaluation = evaluation;
            }
            //we add our new created particle to the list
            particles.add(p);
        }
    }

    /**
     * method that calculate the mean of the sequence of one particle
     * @param f the sequence of the particle 
     * @return the mean of the sequence
     */
    public float mean(float[] f)
    {
        float sum = 0;
        for (int i = 0; i < f.length; ++i) {
            sum += f[i];
        }
        return sum / f.length;
    }

    /**
     * this method compute the new value of the the velocity of a particle
     * @param p the particle that we will compute its new velocity
     * @return the value of the new velocity
     */
    public float computeNewVelocity(Particle p)
    {
        Random rand = new Random(42);
        float r1 = rand.nextFloat();
        float r2 = rand.nextFloat();


        return (w * p.getVelocity()
                + c1 * r1 * (p.getBestEvaluation() - p.positionMean())
                + c2 * r2 * (mean(this.gBest) - p.positionMean())) % 1;
    }

    /**
     * method that return the opposite of the last movement that has been made
     * @param m the last movement that has been made
     * @return the opposite movement
     */
    public Movement lastMoveOpposite(Movement m)
    {
        switch(m)
        {
            case UP:
                return Movement.DOWN;
            case DOWN:
                return Movement.UP;
            case RIGHT:
                return Movement.LEFT;
            case LEFT:
                return Movement.RIGHT;
            default:
                return null;
        }
    }

    /**
     * method that applies the sequence of movement of a particle and return in the obtained state as a matrix
     * @param p the particle which we will apply its sequence
     * @return the obtained state as a matrix
     */
    public short[][] applySequence(Particle p)
    {
        short[][] matrixRepresentation = new short[3][3];

        // the position of the 0 in the matrix
        int xi = 0, xj = 0;

        // initialize the state to the initial state
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                matrixRepresentation[i][j] = this.initialState[i][j];
                // if this the position of the 0 we save it
                if (matrixRepresentation[i][j] == 0) {
                    xi = i;
                    xj = j;
                }
            }
        }

        // A variable used to make sure we don't go beyond the current size of the solution
        int limit = 0;
        for(float movement : p.getPosition())
        {
            if(limit < sequenceSize)
            {
                // We compute the number of authorised moves and set up our interval for the conversation of the float value to a move
                int numberOfMoves = (lastMove == null) ? numberOfMovesPerTile[xi][xj] : (numberOfMovesPerTile[xi][xj] - 1);
                float interval =  (float) 1 / numberOfMoves;
                // We convert the float value into in index
                int moveIndex = (int)(movement / interval) % numberOfMoves;

                // Create different structures to stock our authorised move
                LinkedList<Movement> authorizedMoves = new LinkedList<>();

                //We starts by adding our exitings valid moves 
                for(int i = 0; i < numberOfMovesPerTile[xi][xj]; ++i)
                {
                    if(authorizedMovesPerTile[xi][xj][i] != null)
                        authorizedMoves.add(authorizedMovesPerTile[xi][xj][i]);
                }

                /*Then we remove the opposite of the last move that has been used 
                for example if the last move was LEFT we ban the move RIGHT for this turn*/
                if(lastMove != null) authorizedMoves.remove(lastMoveOpposite(lastMove));

                /* We get the value of our movement by extracting one of the move of the authorizd moves
                at the index value that we computed earlier*/ 
                Movement move = authorizedMoves.get(moveIndex);
                
                // we apply the movement on our current state and we save it to ban its opposing movement in the next turn
                switch (move) {
                    case UP:
                        matrixRepresentation[xi][xj] = matrixRepresentation[xi - 1][xj];
                        matrixRepresentation[xi - 1][xj] = 0;
                        xi = xi - 1;
                        lastMove = Movement.UP;
                        break;
                    case RIGHT:
                        matrixRepresentation[xi][xj] = matrixRepresentation[xi][xj + 1];
                        matrixRepresentation[xi][xj + 1] = 0;
                        xj = xj + 1;
                        lastMove = Movement.RIGHT;
                        break;
                    case DOWN:
                        matrixRepresentation[xi][xj] = matrixRepresentation[xi + 1][xj];
                        matrixRepresentation[xi + 1][xj] = 0;
                        xi = xi + 1;
                        lastMove = Movement.DOWN;
                        break;
                    case LEFT:
                        matrixRepresentation[xi][xj] = matrixRepresentation[xi][xj - 1];
                        matrixRepresentation[xi][xj - 1] = 0;
                        xj = xj - 1;
                        lastMove = Movement.LEFT;
                        break;
                    default:
                        break;
                }
            }
        else
            break; //if we reached the lenght of the current solution sequence we exit the loop 
        limit++;
        }
        lastMove = null;
        return matrixRepresentation;
    }

    /**
     * method that apply the fitness function to a particle 
     * @param p the particle which the fitness function will apply to
     * @return an int between 0 and 9 the higher the value is the better the particle is close to the solution
     * (reaching 9 means that the target state has been found using the sequence of the particle)
     */
    public short fitnessFunction(Particle p)
    {
        //We get our matrix representation of the state after applying the sequence movement
        short[][] state = applySequence(p);
        short count = 0;
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                // For each case of the actual that match the same case of the target state we increment the count
                if(state[i][j] == targetState[i][j])
                    count ++;
            }
        }

        return count;
    }

    /**
     * Our PSO Solver method
     */
    public void solve()
    {
        // We initialize our particles
        initParticles();
        int iteration = 0;

        // While we didn't reach the maximum number of iteration or we didn't find the target value
        while((iteration < maxIteration) && (9 != currentBestEvaluation))
        {
            for (Particle p: particles)
            {
                // We compute the new velocity of our particle
                p.setVelocity(computeNewVelocity(p));
                if(p.getVelocity() < 0) p.setVelocity(- p.getVelocity());

                // We compute its new position by updated all the moves of the sequence
                float[] temp = new float[maxSequenceSize];
                int l = 0;
                for(float pos : p.getPosition())
                {
                    float newPosition  = (float) (pos + p.getVelocity());
                    temp[l] = newPosition;
                    l++;
                }
                p.setPosition(temp);

                // We evaluate our new positionned particle and see if its evalution is better than our current best one
                short evaluation = fitnessFunction(p);

                if(evaluation > currentBestEvaluation)
                {
                    gBest = temp;
                    currentBestEvaluation = evaluation;
                }

                // If we reached our tolerance limit without finding the target state we add a new move in the sequence of our particle 
                if((iteration % tol == 0) && (9 != currentBestEvaluation) && (sequenceSize < maxSequenceSize) && (iteration != 0)) 
                {
                    Random rand = new Random(42);
                    temp[sequenceSize] = rand.nextFloat();
                    p.setPosition(temp);
                }
            }
            iteration++;
            //if the tolerance limit was reached we update the new size of our sequence of moves
            if(iteration % tol == 0)
            {
                sequenceSize++;
            }
        }
        solutionIteration = iteration;
    }    


    public short getCurrentBestEvaluation() {
        return this.currentBestEvaluation;
    }

    public int getSequenceSize() {
        return this.sequenceSize;
    }

    public int getSolutionIteration() {
        return this.solutionIteration;
    }

}