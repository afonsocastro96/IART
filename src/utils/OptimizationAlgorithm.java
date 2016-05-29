package utils;

public abstract class OptimizationAlgorithm {
    private int iterations = 0;

    protected void resetIterations(){
        iterations = 0;
    }

    protected void incrementIterations(){
        iterations += 1;
    }

    public int getIterations() {
        return iterations;
    }

    /**
     * The evaluate function used to assign a value to a chromosome. Abstract function.
     * @param chromosome Chromosome to assign a value.
     * @return The value of the chromosome.
     */
    protected abstract double evaluate(Chromosome chromosome);

    /**
     * The stop condition for the algorithm. Abstract method.
     * @return True if the stop condition has already been met, false otherwise.
     */
    protected abstract boolean stopCondition();
}
