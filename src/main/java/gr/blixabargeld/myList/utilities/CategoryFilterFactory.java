package gr.blixabargeld.myList.utilities;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.TermQuery;
import org.hibernate.search.annotations.Factory;
import org.hibernate.search.annotations.Key;
import org.hibernate.search.filter.FilterKey;
import org.hibernate.search.filter.StandardFilterKey;

/**
 * Filter Hibernate Search Results By First Token Of Item's Category ('popular', 'classical', 'dvd' etc.)
 */
public class CategoryFilterFactory {

	private String category;
	
	public void setCategory(String category) {
		
		this.category = category;
	}
	
	@Factory
	public Filter getFilter() {
		
		Term term = new Term("itemCategory", category);
		Query query = new TermQuery(term);
		Filter filter = new QueryWrapperFilter(query);
		
		return filter;
	}
	
	@Key
	public FilterKey getKey() {
		
		StandardFilterKey key = new StandardFilterKey();
		key.addParameter(category);
		return key;
	}
}
