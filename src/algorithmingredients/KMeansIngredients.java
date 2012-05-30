package algorithmingredients;

import datatypes.Cluster;
import datatypes.InitialisationStep;
import datatypes.KMeansObject;
import fileoperations.DataManipulationOnFile;

public class KMeansIngredients {

	public static DataManipulationOnFile manipulationOnFile = new DataManipulationOnFile();
	public static InitialisationStep initialisationObject = new InitialisationStep();
	public static int dimensionOfDataset;

	// ///////////////////////////
	// K-MEANS ALGORITHM METHOD //
	// ///////////////////////////
	/**
	 * 
	 * @param kMeansClusters
	 * @param numOfIterations
	 * @return kMeansClusters
	 * @performs It applies the algorithm on to parameter kMeansClusters which
	 *           is already initialized in the Initialize step
	 */
	public static Cluster[] algorithmKMeans(Cluster[] kMeansClusters,
			int numOfIterations) {
		int closestCluster = 0;
		Cluster[] finalKMeansCluster = new Cluster[kMeansClusters.length];
		for (int i = 0; i < finalKMeansCluster.length; i++) {
			finalKMeansCluster[i] = new Cluster(dimensionOfDataset);
		}
		for (int numOfTimes = 0; numOfTimes < numOfIterations; numOfTimes++) {
			// for numOfIterations times, run the following code
			for (int numOfClusters = 0; numOfClusters < kMeansClusters.length; numOfClusters++) {
				// foreach cluster run the code
				for (KMeansObject aSingleObject : kMeansClusters[numOfClusters].listOfObjectInCluster) {
					closestCluster = findClosestCluster(aSingleObject,
							kMeansClusters);
					finalKMeansCluster[closestCluster].listOfObjectInCluster
							.add(aSingleObject);
				}
			}
			kMeansClusters = finalKMeansCluster;
			for (int i = 0; i < kMeansClusters.length; i++) {
				kMeansClusters[i].setMeanOfCluster();
			}
			finalKMeansCluster = new Cluster[kMeansClusters.length];
			for (int i = 0; i < finalKMeansCluster.length; i++) {
				finalKMeansCluster[i] = new Cluster(dimensionOfDataset);
			}
		}
		return kMeansClusters;
	}

	// //////////////////////////////
	// FIND CLOSEST CLUSTER METHOD //
	// //////////////////////////////
	/**
	 * @param kMeansObj
	 * @param kMeansClusters
	 * @return indexOfClosest
	 * @performs It finds the index of closest cluster from the list of
	 *           cluster[] to the kMeansObj passed to the method as parameter
	 */
	public static int findClosestCluster(KMeansObject kMeansObj,
			Cluster[] kMeansClusters) {
		// Get the values embedded in the kMeansObj put into an in-memory copy
		double[] valuesInObject = kMeansObj.getValuesInObject();
		// Variables to be used
		double distance, tempDistance;
		// Initial Seeding of values with [0] cluster regarded as closest
		int indexOfClosest = 0;
		distance = findDistance(valuesInObject,
				kMeansClusters[0].getMeanOfCluster());
		// finding for all other cluster
		for (int i = 1; i < kMeansClusters.length; i++) {
			tempDistance = findDistance(valuesInObject,
					kMeansClusters[i].getMeanOfCluster());
			if (distance > tempDistance) {
				distance = tempDistance; // used for l8r processing
				indexOfClosest = i; // index of the closest cluster
			}
		}
		return indexOfClosest; // returning the closest cluster's index
	}

	// ///////////////////////
	// FIND DISTANCE METHOD //
	// ///////////////////////
	/**
	 * @param valuesInObject
	 * @param meanOfCluster
	 * @return Euclidean distance between two d-dimensional points in the
	 *         d-dimensional space
	 * @performs It finds the distance between the parameters passed to it
	 */
	public static double findDistance(double[] valuesInObject,
			double[] meanOfCluster) {
		double temp = 0;
		for (int i = 0; i < meanOfCluster.length; i++) {
			temp = temp
					+ ((valuesInObject[i] - meanOfCluster[i]) * (valuesInObject[i] - meanOfCluster[i]));
		}
		return Math.sqrt(temp); // Sqrt of the sum of the squares of the
								// differences of the d-dimensional values
	}

	public static double findSquaredError(double[] valuesOfObject, double[] meanOfCluster) {
		double squaredError = 0;
		for (int i = 0; i < meanOfCluster.length; i++) {
			squaredError = squaredError
					+ ((valuesOfObject[i] - meanOfCluster[i]) * (valuesOfObject[i] - meanOfCluster[i]));
		}
		return squaredError;
	}

}
