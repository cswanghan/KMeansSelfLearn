package datatypes;

import java.util.List;

public class InitialisationStep {
	Cluster[] clustersTemp;

	public Cluster[] Initialise(List<KMeansObject> listOfObjects,
			int numOfClusters, int noOfDimensions) {
		clustersTemp = new Cluster[numOfClusters];
		for (int i = 0; i < clustersTemp.length; i++) {
			clustersTemp[i] = new Cluster(noOfDimensions);
		}
		
		// I need to make sure that every cluster should have at least one
		// object inserted into it .. thats very important through a random
		// distribution perspective
		
		for (KMeansObject kMeansObject : listOfObjects) {
			int tempVar = (int) Math.floor(Math.random() * numOfClusters);
			clustersTemp[tempVar].listOfObjectInCluster.add(kMeansObject);
		}
		for (int i = 0; i < clustersTemp.length; i++) {
			clustersTemp[i].setMeanOfCluster();
		}
		return clustersTemp;
	}
}