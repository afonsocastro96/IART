package test.input_tests;

import Core.DataSet;
import algorithms.SimulatedAnnealing;
import algorithms.simulated_annealing.OneRandomTribunalStateTransition;
import algorithms.simulated_annealing.State;
import algorithms.simulated_annealing.StateTransitionFunction;
import cityparser.CityParser;
import cityparser.Data;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

/**
 * Created by Luís on 30/05/2016.
 */
public class tribunalVariationTest {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        CityParser parser = new CityParser();
        Data data = parser.getData("cities.ser");

        DataSet dataSet;

        StateTransitionFunction function;


        PrintWriter writer = new PrintWriter("tribunalVar.csv", "UTF-8");

        int iterations = 30;

        for(int numTribunals = 10; numTribunals <= 150; numTribunals++) {
            System.out.println("Tribunal: " + numTribunals);
            dataSet =  new DataSet(data, 100, 50, 75000, numTribunals);
            function = new OneRandomTribunalStateTransition(numTribunals, data.getCities().size());
            SimulatedAnnealing algorithm = new SimulatedAnnealing(function, 20, 0.25, dataSet);
            BigInteger accumulatedTime =  BigInteger.ZERO;
            BigInteger accumulatedFitness = BigInteger.ZERO;


            for (int i = 0; i < iterations; ++i) {
                long begin = System.nanoTime();
                State result = algorithm.run();
                long end = System.nanoTime();

                double fitnessValue = (long) result.getValue();
                long elapsedTime = (long) ((end - begin) / 1000000.0);

                accumulatedFitness = accumulatedFitness.add(BigInteger.valueOf((long)fitnessValue));
                accumulatedTime = accumulatedTime.add(BigInteger.valueOf(elapsedTime));
            }

            long fitnessValue = accumulatedFitness.divide(BigInteger.valueOf(iterations)).longValue();
            long elapsedTime = accumulatedTime.divide(BigInteger.valueOf(iterations)).longValue();

            writer.println("" + numTribunals + "," + (long)fitnessValue + "," + elapsedTime);
        }

        writer.close();
    }

}
