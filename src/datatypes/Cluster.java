package datatypes;

import java.util.ArrayList;
import java.util.List;

import algorithmingredients.KMeansIngredients;

public class Cluster {
	public List<KMeansObject> listOfObjectInCluster;
	public double[] meanOfCluster;
	private int dimensionOfDataset;

	// A multi dimensional cluster mean.

	public double[] getMeanOfCluster() {
		return meanOfCluster;
	}

	public void setMeanOfCluster() {
		this.meanOfCluster = calculateMean();
	}

	public List<KMeansObject> getListOfObjectInCluster() {
		return listOfObjectInCluster;
	}

	public void setListOfObjectInCluster(
			List<KMeansObject> listOfObjectInCluster) {
		this.listOfObjectInCluster = listOfObjectInCluster;
	}

	/*
	 * Get the mean of the cluster passed
	 */
	private double[] calculateMean() {
		double[] valuesInObject;
		double[] totalValue = new double[dimensionOfDataset];
		// The no. of dimensions of the data set
		double[] meanOfCluster = new double[dimensionOfDataset];
		// The no. of dimensions of the data set
		/*
		 * I am getting the no. of dimensions from the data stored and we are
		 * working with, I could have done with passing the numOfDimensions but
		 * still , first try
		 */

		/**
		 * The data is fetched out of an object and put into valuesInObject
		 * array which is later used for processing
		 */
		for (KMeansObject oneKMeansObject : this.getListOfObjectInCluster()) {
			valuesInObject = oneKMeansObject.getValuesInObject();
			for (int i = 0; i < valuesInObject.length; i++) {
				totalValue[i] += valuesInObject[i];
				// Summing all the dimensional values
			}
		}

		for (int i = 0; i < totalValue.length; i++) {
			meanOfCluster[i] = (totalValue[i] / this.getListOfObjectInCluster()
					.size());
			// Total divided by the size of the list (no. of objects)
		}
		return meanOfCluster;
	}

	public Cluster(int dimension) {
		listOfObjectInCluster = new ArrayList<KMeansObject>();
		dimensionOfDataset = dimension;
	}

	public double calculateSquaredErrors() {
		// double[] varMeanOfCluster = getMeanOfCluster();
		double[] valuesInObject;
		double squaredError = 0;
		for (KMeansObject kMeansObj : listOfObjectInCluster) {
			valuesInObject = kMeansObj.valuesInObject;
			squaredError += KMeansIngredients.findSquaredError(valuesInObject, meanOfCluster);
		}
		return squaredError;
	}
}