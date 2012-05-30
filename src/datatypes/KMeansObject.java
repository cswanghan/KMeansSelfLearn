package datatypes;

/**
 * @author varadmeru
 * @task It is the basic object of the KMeans with one object referring to one
 *       line of the input file
 */

public class KMeansObject {
	public double[] valuesInObject;

	public double[] getValuesInObject() {
		return valuesInObject;
	}

	public KMeansObject(int noOfDimensions) {
		valuesInObject = new double[noOfDimensions];
	}
}
