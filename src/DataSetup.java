/**
 * Created by Dave on 5/15/17.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataSetup {


    private static int[] getNumberOfRowsAndColumns(String filePath) {

        int[] returnValues = new int[2];
        int numberOfRows = 0;
        int numberOfColumns = 0;
        String line;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            while ((line = bufferedReader.readLine()) != null) {
                numberOfRows++;
                String[] rowOfData = line.split(",");
                numberOfColumns = rowOfData.length;
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getLocalizedMessage());
        }

        returnValues[0] = numberOfRows;
        returnValues[1] = numberOfColumns;

        return returnValues;
    }

    private static double[] convertStringArrayToDoubleArray(String[] a) {
        double[] d = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            double currentValue = Double.parseDouble(a[i]);
            d[i] = currentValue;
        }
        return d;
    }

    private static double[][] addRowToData(double[] d, int rowNumber, double[][] data) {
        for (int i = 0; i < d.length; i++) {
            data[rowNumber][i] = d[i];
        }
        return data;
    }

    public static double[][] loadData(String filePath) {
        int[] rowsAndColumns = getNumberOfRowsAndColumns(filePath);
        int numberOfRows = rowsAndColumns[0];
        int numberOfColumns = rowsAndColumns[1];
        String line;
        int currentRow = 0;

        double data[][] = new double[numberOfRows][numberOfColumns];

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            while ((line = bufferedReader.readLine()) != null) {
                String[] rowOfData = line.split(",");

                double[] rowAsDoubles = convertStringArrayToDoubleArray(rowOfData);
                addRowToData(rowAsDoubles, currentRow, data);
                currentRow++;
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getLocalizedMessage());
        }

        return data;
    }

    public static void printData(double[][] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.println();
            System.out.print("[ ");
            for (int j = 0; j < data[0].length; j++) {
                if (j < data[0].length - 1){
                    System.out.print(data[i][j] + ", ");
                }
                else if (j == data[0].length - 1){
                    System.out.print(data[i][j] + " ]");
                }
                else {
                    System.out.println(data[i][j]);
                }
            }
        }
    }
}





