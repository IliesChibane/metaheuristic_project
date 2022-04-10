package meta.projet.solver;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;

import meta.projet.solver.heuristic.Heuristic;

public class GASolver extends Solver {
    private int dimension;
    private short[][] initialState;
    private short[][] targetState;
    private int populationSize;
    private LinkedList<Individual> population;
    private Heuristic heuristic;
    private int initialSequenceLength;
    private int maxIter;
    private float selectionRatio;
    private int numberOfCrossovers;
    private int numberOfMutations;
    private float crossoverRatio;
    private float[] solution;
    private int solutionIteration;

    private static int[][] numberOfMovesPerTile;
    private static Movement[][][] authorizedMovesPerTile;

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


    public GASolver(short[][] initialState, short[][] targetState,
            int populationSize, Heuristic heuristic, int initialSequenceLength,
            int maxIter, float selectionRatio, int numberOfCrossovers,
            int numberOfMutations, float crossoverRatio) {

        this.dimension = initialState.length;
        this.initialState = new short[dimension][dimension];
        this.targetState = new short[dimension][dimension];

        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                this.initialState[i][j] = initialState[i][j];
                this.targetState[i][j] = targetState[i][j];
            }
        }

        this.populationSize = populationSize;

        this.heuristic = heuristic;
        this.heuristic.setTargetState(targetState);

        this.initialSequenceLength = initialSequenceLength;
        this.maxIter = maxIter;
        this.selectionRatio = selectionRatio;
        this.numberOfCrossovers = numberOfCrossovers;
        this.numberOfMutations = numberOfMutations;
        this.crossoverRatio = crossoverRatio;
    }

    public void solve() {
        // initial population
        population = new LinkedList<Individual>();

        Random rand = new Random(42);
        for (int i = 0; i < populationSize; ++i) {
            float[] sequence = new float[initialSequenceLength];
            for (int j = 0; j < initialSequenceLength; ++j) {
                sequence[j] = rand.nextFloat();
            }
            population.add(new Individual(sequence));
        }

        Collections.sort(population, new Comparator<Individual>() {
            @Override
            public int compare(Individual sequence1, Individual sequence2) {
                return GASolver.fitnessScore(initialState, sequence1.getSequence(), heuristic)
                    - GASolver.fitnessScore(initialState, sequence2.getSequence(), heuristic);
            }
        });

        System.out.println("=== initial population === | size : " + population.size());
        for (Individual individual : population) {
            individual.print();
            System.out.println(" | score = " + 
                fitnessScore(initialState, individual.getSequence(), heuristic));
        }
        System.out.println("==================================");
        
        /**
         * boucle de recherche
         * selection : an individual is selected with a probability 
         *             proportional to it's fitness score.
         * reproduction : crossover then mutation.
         *     crossover : concatenation of the first n genes of the first 
         *                 individual and the m last genes of the second 
         *                 individual.
         *     mutation : mutation operators are : permutation, inverse with 
         *                respect to 0.5 and adding new gene.
         * insertion : the new individuals are inserted in the population and 
         *             only the overall best N individuals are kept for the
         *             next iteration.
         */
        boolean found = false;
        int iter = 0;
        final int selectionSize = (int) (populationSize * selectionRatio);
        final int samplerMax = populationSize * (populationSize + 1) / 2;
        System.out.println("selection size : " + selectionSize + " | max sampler : " + samplerMax);
        while (iter < maxIter && !found) {
            System.out.println("================================== iteration n° " + iter + " ==========================");
            
            // selection

            LinkedList<Individual> selection = new LinkedList<Individual>();
            while (selection.size() < selectionSize) {
                int sampler = rand.nextInt(samplerMax);
                //System.out.println("sampler : " + sampler);

                int threshold = populationSize;
                Iterator<Individual> iterator = population.iterator();
                while (iterator.hasNext()) {
                    Individual sequence = iterator.next();
                    if (sampler < threshold) {
                        selection.add(sequence);
                        break;
                    } else {
                        sampler = sampler - threshold;
                        threshold = threshold - 1;
                    }
                }
            }

            System.out.println("=== initial selection ===");
            for (Individual individual : selection) {
                individual.print();
                System.out.println(" | score = " + fitnessScore(initialState, individual.getSequence(), heuristic));
            }
            System.out.println("==================================");

            // reproduction - crossover

            int i = 0;
            while (i < numberOfCrossovers) {
                int parent1Index = rand.nextInt(selectionSize);
                int parent2Index = rand.nextInt(selectionSize);
                while (parent1Index == parent2Index)
                    parent2Index = rand.nextInt(selectionSize);

                Individual parent1 = selection.get(parent1Index);
                Individual parent2 = selection.get(parent2Index);

                if (parent1.getSequence().length > parent2.getSequence().length) {
                    Individual tmp = parent1;
                    parent1 = parent2;
                    parent2 = tmp;
                }

                int childLength = parent1.getSequence().length;
                int numberOfParent1Genes = (int) (parent1.getSequence().length * crossoverRatio);
                
                float[] child = new float[childLength];
                
                for (int j = 0; j < childLength; ++j) {
                    if (j < numberOfParent1Genes)
                        child[j] = parent1.getSequence()[j];
                    else
                        child[j] = parent2.getSequence()[j];
                }

                Individual newIndividual = new Individual(child);
                selection.add(newIndividual);
                ++i;
            }
            
            System.out.println("=== selection after crossover ===");
            for (Individual individual : selection) {
                individual.print();
                System.out.println(" | score = " + fitnessScore(initialState, individual.getSequence(), heuristic));
            }
            System.out.println("==================================");

            // reproduction - mutation

            i = 0;
            while (i < numberOfMutations) {
                int mutantIndex = rand.nextInt(selection.size());
                Individual mutant = selection.remove(mutantIndex);
                int mutation = rand.nextInt(3);

                switch (mutation) {
                    case 0: // permutation
                        int firstPosition = rand.nextInt(mutant.getSequence().length);
                        int secondPosition = rand.nextInt(mutant.getSequence().length);

                        float tmp = mutant.getSequence()[firstPosition];
                        mutant.getSequence()[firstPosition] = mutant.getSequence()[secondPosition];
                        mutant.getSequence()[secondPosition] = tmp;
                        break;
                    case 1: // inverse
                        int position = rand.nextInt(mutant.getSequence().length);
                        mutant.getSequence()[position] = 1 - mutant.getSequence()[position];
                        break;
                    case 2: // augmentation
                        float[] augmented = new float[mutant.getSequence().length + 1];
                        for (int j = 0; j < mutant.getSequence().length; ++j)
                            augmented[j] = mutant.getSequence()[j];
                        augmented[augmented.length - 1] = rand.nextFloat();
                        mutant = new Individual(augmented);
                        break;
                }

                selection.add(mutant);
                ++i;
            }

            System.out.println("=== selection after mutation ===");
            for (Individual individual : selection) {
                individual.print();
                System.out.println(" | score = " + 
                    fitnessScore(initialState, individual.getSequence(), heuristic));
            }
            System.out.println("==================================");

            // insertion

            for (Individual individual : selection) {
                if (!population.contains(individual))
                    population.add(individual);
            }

            Collections.sort(population, new Comparator<Individual>() {
                @Override
                public int compare(Individual sequence1, Individual sequence2) {
                    return GASolver.fitnessScore(initialState, sequence1.getSequence(), heuristic)
                        - GASolver.fitnessScore(initialState, sequence2.getSequence(), heuristic);
                }
            });
            
            LinkedList<Individual> newPopulation = new LinkedList<Individual>();
            i = 0;
            for (Individual individual : population) {
                newPopulation.add(individual);
                ++i;
                if (i == populationSize)
                    break;
            }

            population = newPopulation;

            System.out.println("=== final population ===");
            for (Individual individual : population) {
                individual.print();
                System.out.println(" | score = " +
                    fitnessScore(initialState, individual.getSequence(), heuristic));
            }
            System.out.println("==================================");

            ++iter;

            // check for solution
            for (Individual individual : population) {
                if (fitnessScore(initialState, individual.getSequence(), heuristic) == 0) {
                    System.out.println("solution : ");
                    individual.print();
                    this.solution = individual.getSequence();
                    this.solutionIteration = iter;
                    found = true;
                }
            }
        }
    }

    public static short[][] applySequence(short[][] initialState,
        float[] movesSequence) {

        short[][] state = new short[3][3];
        int xi = 0, xj = 0;

        // initialize the state to the initial state
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                state[i][j] = initialState[i][j];
                if (state[i][j] == 0) {
                    xi = i;
                    xj = j;
                }
            }
        }
        
        // apply the movements sequentially
        // moves order is up, right, down, left
        for (float movement : movesSequence) {
            int numberOfMoves = numberOfMovesPerTile[xi][xj];
            float interval = (float) 1 / numberOfMoves;
            int moveIndex = (int)(movement / interval);
            Movement move = authorizedMovesPerTile[xi][xj][moveIndex];

            switch (move) {
                case UP:
                    state[xi][xj] = state[xi - 1][xj];
                    state[xi - 1][xj] = 0;
                    xi = xi - 1;
                    break;
                case RIGHT:
                    state[xi][xj] = state[xi][xj + 1];
                    state[xi][xj + 1] = 0;
                    xj = xj + 1;
                    break;
                case DOWN:
                    state[xi][xj] = state[xi + 1][xj];
                    state[xi + 1][xj] = 0;
                    xi = xi + 1;
                    break;
                case LEFT:
                    state[xi][xj] = state[xi][xj - 1];
                    state[xi][xj - 1] = 0;
                    xj = xj - 1;
                    break;
                default:
                    break;
            }
        }
        return state;
    }

    public static int fitnessScore(short[][] initialState,
        float[] movesSequence, Heuristic heuristic) {

        short[][] state = applySequence(initialState, movesSequence);
        return heuristic.score(state);
    }
}
