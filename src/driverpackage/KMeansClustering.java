package driverpackage;

import java.util.ArrayList;
import java.util.List;

import algorithmingredients.KMeansIngredients;

import datatypes.Cluster;
import datatypes.KMeansObject;

/**
 * @author varadmeru
 * @version 0.1
 * @performs It performs the basic K-Means clustering in the data file provided
 *           to it
 * @sampleoutput Starting Clustering 
 * 0.128649151504929 
 * 0.7123027436888993 
 * 0.0 // Due to only one member present
 * 0.06147613749667695 
 * Clustering Ended
 */
public class KMeansClustering {
	// //////////////
	// MAIN METHOD //
	// //////////////
	/**
	 * @param args
	 * @performs The Main Method
	 */
	public static void main(String[] args) {
		if (args.length != 5) {
			System.err.println("Please enter the correct arguments");
			System.err.println("Usage: 0. NumOfDimensions\n"
					+ "1. NumOfClusers\n" + "2. FileLocation\n"
					+ "3. ClusterFileNamePrefixes\n" + "4. NumOfIterations");
			System.exit(2);
		}
		
		System.out.println("Starting Clustering");
		
		KMeansIngredients.dimensionOfDataset = Integer.parseInt(args[0]);
		List<KMeansObject> listOfObjects = new ArrayList<KMeansObject>();
		// Read data-points from the file
		listOfObjects = KMeansIngredients.manipulationOnFile.readFromFile(
				args[2], Integer.parseInt(args[0]));
		// Random distribution of points
		Cluster[] initialClusters = KMeansIngredients.initialisationObject
				.Initialise(listOfObjects, Integer.parseInt(args[1]),
						Integer.parseInt(args[0]));
		// Apply the KMeans algorithms on to the initial clusters and get th
		// final cluster
		Cluster[] finalClusters = KMeansIngredients.algorithmKMeans(
				initialClusters, Integer.parseInt(args[4]));
		double totalSqError = 0;
		for (int i = 0; i < finalClusters.length; i++) {
			KMeansIngredients.manipulationOnFile.writeToFileFile(
					finalClusters[i], args[3] + "" + i);
			totalSqError += finalClusters[i].calculateSquaredErrors();
			System.out.println(finalClusters[i].calculateSquaredErrors());
		}
		System.out.println("The Total Squared Error of all the Clusters together is :" + totalSqError);
		System.out.println("Clustering Ended");
	}
}