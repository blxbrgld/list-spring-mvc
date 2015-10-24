package gr.blxbrgld.myList.utilities;

import java.util.List;

import gr.blxbrgld.myList.model.Item;

/**
 * Dull Class To Allow Hibernate-Search Functions Return Results Along With No Of Results Needed For Paging Purposes 
 */
public class ReturningValues {

	private int noOfResults;
	private List<Item> results;
	
	public ReturningValues(int noOfResults, List<Item> results) {
		this.noOfResults = noOfResults;
		this.results = results;
	}
	
	public int getNoOfResults() {
		return noOfResults;
	}
	
	public List<Item> getResults() {
		return results;
	}
}
