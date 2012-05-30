package fileoperations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import datatypes.Cluster;
import datatypes.KMeansObject;

/**
 * @author varadmeru
 * @task It reads from the file and makes a list of objects
 */

public class DataManipulationOnFile {
	File file = null;
	FileReader fileReader = null;
	KMeansObject objectTemp = null;
	FileWriter clusterFileWriter = null;
	List<KMeansObject> listOfObjects = null;

	/**
	 * @param fileLocation
	 * @param noOfDimensions
	 * @param noOfObjects
	 * @return List<KMeansObject> listOfObjects
	 * @task It returns the in-memory representation of the whole file as a
	 *       collection (ArrayList) of KMeansObjects
	 */
	public List<KMeansObject> readFromFile(String fileLocation,
			int noOfDimensions) {
		try {
			/*
			 * Preparing the in-memory copy of the KMeansObjects for further
			 * processing
			 */
			objectTemp = new KMeansObject(noOfDimensions);
			listOfObjects = new ArrayList<KMeansObject>();

			// String Temporaries
			String stringTemp;
			String[] stringSplits;

			// File References
			file = new File(fileLocation);
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// Fetch the string line and split it to get the entries
			while ((stringTemp = bufferedReader.readLine()) != null) {
				stringSplits = stringTemp.split("\t");
				for (int i = 0; i < stringSplits.length; i++) {
					objectTemp.valuesInObject[i] = Double
							.parseDouble(stringSplits[i]);
				}
				listOfObjects.add(objectTemp);
				objectTemp = new KMeansObject(noOfDimensions);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return listOfObjects;
	}

	/**
	 * @param clusterToBeWritten
	 * @param fileName
	 */
	public void writeToFileFile(Cluster clusterToBeWritten, String fileName) {
		String string = null;
		try {
			file = new File(fileName+".txt");
			clusterFileWriter = new FileWriter(file);
			// for each object present in the cluster
			List<KMeansObject> listOfObjectsInCluster = new ArrayList<KMeansObject>();
			listOfObjectsInCluster = clusterToBeWritten
					.getListOfObjectInCluster();
			for (KMeansObject oneObject : listOfObjectsInCluster) {
				// for each value present in the k-means cluster
				for (int i = 0; i < oneObject.valuesInObject.length; i++) {
					if (i == 0) {
						// The string contains the values of the dimensions for
						// the first time
						string = oneObject.valuesInObject[i] + "\t";
						continue;
					}
					// The string contains the values of the dimensions for the
					// all the times after first
					string += oneObject.valuesInObject[i] + "\t";
				}
				// adding a end line
				string += "\n";
				// write the object in the file
				clusterFileWriter.write(string);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				clusterFileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}