/**
 * Created by Dave on 5/15/17.
 */

import java.util.Arrays;
import java.util.TreeMap;

public class KNN {
    private int k;
    private double[][] trainData;
    private double[][] testData;

    KNN(int k, double[][] trainData, double[][] testData) {
        this.k = k;
        this.trainData = trainData;
        this.testData = testData;
    }

    private double euclideanDistance(double[] a, double[] b) {
        double distance = 0;

        for (int x = 1; x < a.length; x++) {
            distance += Math.pow((a[x] - b[x]), 2);
        }
        return Math.sqrt(distance);
    }

    public int[] getNeighbors(double[] row) {
        // return an array with the k closest points
        // neighbors are represented by their row number ex: 51
        TreeMap<Double, Integer> distances = new TreeMap<>();
        for (int i = 0; i < trainData.length; i++) {
            distances.put(euclideanDistance(row, trainData[i]), i);
        }

        Object[] sortedKeys = distances.keySet().toArray();
        Arrays.sort(sortedKeys);

        int[] neighbors = new int[k];

        for (int i = 0; i < k; i++) {
            double currentKey = (double)sortedKeys[i];
            neighbors[i] = distances.get(currentKey);
        }

        return neighbors;
    }

    private int classify(double[] row) {
        int[] labels = new int[k];
        int[] neighbors = getNeighbors(row);

        for (int i = 0; i < neighbors.length; i++) {
            double [] currentRow = trainData[neighbors[i]];
            int label = (int)currentRow[0];

            if (label == 1)
                labels[0]++;
            else if (label == 2)
                labels[1]++;
            else
                labels[2]++;
        }

        int indexOfMax = 0;
        int maxValue = labels[0];
        for (int i = 1; i < labels.length; i++) {
            if (labels[i] > maxValue) {
                maxValue = labels[i];
                indexOfMax = i;
            }
        }
        return indexOfMax + 1;
    }

    private void printNeighborLabels(int[] neighbors) {
        double[] first = trainData[neighbors[0]];
        double[] second = trainData[neighbors[1]];
        double[] third = trainData[neighbors[2]];

        System.out.println((int)first[0]);
        System.out.println((int)second[0]);
        System.out.println((int)third[0]);
    }

    public void getAccuracy() {
        int numberCorrect = 0;
        for (int i = 0; i < testData.length; i++) {
            double[] currentRow = testData[i];
            int prediction = classify(currentRow);
            System.out.println("Prediction: " + prediction);
            int actualValue = (int)currentRow[0];
            System.out.println("Actual: " + actualValue + "\n");

            if (prediction == actualValue) {
                numberCorrect++;
            }
        }
        System.out.println("Number Correct: " + numberCorrect);
        System.out.println("Number of Samples: " + testData.length);
        double percentCorrect = (double)numberCorrect / testData.length * 100;
        System.out.print("\n" + percentCorrect + "%");
    }
}
