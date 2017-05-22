/**
 * Created by Dave on 5/15/17.
 */
public class Tester {
    public static void main(String[] args) {
        double[][] trainingData = DataSetup.loadData("wineTrain.csv");
        DataSetup.printData(trainingData);

        System.out.println("\n___________________________________________________");
        double[][] testingData = DataSetup.loadData("wineTest.csv");
        DataSetup.printData(testingData);

        System.out.println("\n___________________________________________________");
        KNN knn = new KNN(5, trainingData, testingData);
        knn.getAccuracy();

        // run on all data and check accuracy
        System.out.println("******************************************************\n\n\n");
        double[][] allData = DataSetup.loadData("wine.csv");
        KNearestNeighbors knn2 = new KNearestNeighbors(3, allData);
        knn2.getAccuracy();
    }
}