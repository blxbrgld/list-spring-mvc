package gr.blxbrgld.mylist.utilities;

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
 * Filter Hibernate Search Results By 'Music' or 'Film' Argument Given By User
 * @author blxbrgld
 */
public class ParentCategoryFilterFactory {

	private String parentCategory;
	
	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}
	
	@Factory
	public Filter getFilter() {
		Term term = new Term("itemParentCategory", parentCategory);
		Query query = new TermQuery(term);
		return new QueryWrapperFilter(query);
	}
	
	@Key
	public FilterKey getKey() {
		StandardFilterKey key = new StandardFilterKey();
		key.addParameter(parentCategory);
		return key;
	}
}
