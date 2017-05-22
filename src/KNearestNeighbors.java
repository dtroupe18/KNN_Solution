/**
 * Created by Dave on 5/22/17.
 */

import java.util.Arrays;
import java.util.TreeMap;

public class KNearestNeighbors {

    private int k;
    private double[][] data;


    KNearestNeighbors(int k, double[][] data) {
        this.k = k;
        this.data = data;
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
        for (int i = 0; i < data.length; i++) {
            if (row != data[i]) {
                distances.put(euclideanDistance(row, data[i]), i);
            }
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
       int[] currentNeighbors = getNeighbors(row);
       int[] labels = new int[k];

       if (k == 1) {
           double[] neighborData = data[currentNeighbors[0]];
           return (int)neighborData[0];
       }


       for (int i: currentNeighbors) {
           double[] neighborData = data[i];
           int neighborLabel = (int)neighborData[0];
           labels[neighborLabel - 1]++;
       }

       int max = labels[0];
       int labelToReturn = 1;
       for (int j = 0; j < labels.length; j++) {
           if (labels[j] > max) {
               max = labels[j];
               labelToReturn = j + 1;
           }
       }
       return labelToReturn;
    }

    public void getAccuracy() {
        int correctCount = 0;
        for (double[] dataPoint: data) {
            int predictedLabel = classify(dataPoint);
            int actualLabel = (int)dataPoint[0];
            if (predictedLabel == actualLabel) {
                correctCount++;
            }
            if (predictedLabel != actualLabel) {
                System.out.println("Pred: " + predictedLabel + " Actual: " + actualLabel);
            }
        }
        System.out.println((double)correctCount / data.length * 100 + "%\n");
    }
}
