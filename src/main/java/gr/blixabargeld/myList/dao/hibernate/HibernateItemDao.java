package gr.blixabargeld.myList.dao.hibernate;

import java.util.List;

import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.Query;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import gr.blixabargeld.myList.dao.ItemDao;
import gr.blixabargeld.myList.model.Artist;
import gr.blixabargeld.myList.model.Category;
import gr.blixabargeld.myList.model.Item;
import gr.blixabargeld.myList.model.Subtitles;
import gr.blixabargeld.myList.utilities.ReturningValues;

/**
 * Item's DAO Implementation
 */
@Repository
public class HibernateItemDao extends AbstractHibernateDao<Item> implements ItemDao {

	@SuppressWarnings("unchecked")
	public List<Item> findByCategory(Category category) {
	
		Query query = getSession().getNamedQuery("findItemsByCategory");
		query.setParameter("category", category);
		return (List<Item>) query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findByArtist(Artist artist, int first, int size) {
		
		Query query = getSession().getNamedQuery("findItemsByArtist");
		query.setParameter("artist", artist);
		query.setFirstResult(first);
		query.setMaxResults(size);
		return (List<Item>) query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ReturningValues search(String searchFor, String searchIn, String property, String order, int first, int size) {

		FullTextSession fullTextSession = Search.getFullTextSession(getSession());
		QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Item.class).get();
		org.apache.lucene.search.Query luceneQuery = null;
		
		String searchString = searchFor != null ? searchFor.trim() : "";
		
		if(searchIn != null && searchString.equals("*")) { //All Category Items
			
			luceneQuery = queryBuilder
					.keyword()
					.wildcard()
					.onField("titleEng")
					.matching(searchString)
					.createQuery();
		}
		else if(searchIn != null && searchIn.equals("Artist")) { //Search Only Artists
			
			luceneQuery = queryBuilder
					.phrase()
					.onField("artistActivityItems.idArtist.title").boostedTo(2)
						.andField("artistActivityItems.idArtist.description")
					.sentence(searchString)
					.createQuery();
		}
		else if(searchIn != null && searchIn.equals("Lists")) { //4- and 5- Star Reviews
		
			//TODO Lists Functionality
			luceneQuery = queryBuilder
					.range()
					.onField("rating")
					.above(3)
					.excludeLimit()
					.createQuery();
		}
		else { //Search All

			luceneQuery = queryBuilder
					.phrase()
					.onField("titleEng").boostedTo(2)
						.andField("titleEll")
						.andField("description")
						.andField("artistActivityItems.idArtist.title").boostedTo(2)
						.andField("artistActivityItems.idArtist.description")
					.sentence(searchString)
					.createQuery();
		}
		
		FullTextQuery hibernateQuery = fullTextSession.createFullTextQuery(luceneQuery, Item.class);

		/*
		 * Filters
		 */
		if(searchIn != null && !searchIn.equals("Lists")) {
			
			if(searchString.equals("*")) {
				
				String[] tokens = StringUtils.tokenizeToStringArray(searchIn, " ");
				
				if(tokens[0].toLowerCase().equals("music") || tokens[0].toLowerCase().equals("films")) { //Parent Category Filter
					
					hibernateQuery.enableFullTextFilter("parentCategoryFilter").setParameter("parentCategory", tokens[0].toLowerCase());
				}
				else { //Category Filter
				
					hibernateQuery.enableFullTextFilter("categoryFilter").setParameter("category", tokens[0].toLowerCase());
				}
			}
			else if(!searchIn.equals("Artist")) { //Parent Category Filter
			
				String parentCategory = (searchIn.equals("Music")) ? "music" : "films";
				hibernateQuery.enableFullTextFilter("parentCategoryFilter").setParameter("parentCategory", parentCategory);
			}
		}
		
		/*
		 * Order By Clause
		 */
		boolean ordering = (order != null && order.equals("DESC")) ? true : false;
		
		if(property != null && property.equals("titleEng")) {
			
			Sort sort = new Sort(new SortField("sortTitle", SortField.STRING, ordering));
			hibernateQuery.setSort(sort);
		}
		else if(property != null && property.equals("artist")) {
			
			Sort sort = new Sort(new SortField("sortArtist", SortField.STRING, ordering));
			hibernateQuery.setSort(sort);
		}
		
		int noOfResults = hibernateQuery.getResultSize();
		hibernateQuery.setFirstResult(first);
		hibernateQuery.setMaxResults(size);
		
		return new ReturningValues(noOfResults, (List<Item>) hibernateQuery.list());
	}
	
	@Override
	public void lucene(boolean synchronously) {
		
		FullTextSession fullTextSession = Search.getFullTextSession(getSession());
		
		if(synchronously) {
			
			try {
				
				fullTextSession.createIndexer().startAndWait();
			} 
			catch(InterruptedException exception) {
			
				exception.printStackTrace();
			}
		}
		else {
			
			fullTextSession.createIndexer().start();
		}
	}
	
	@Override
	public boolean havingCategoryExists(Category category) {

		Query query = getSession().getNamedQuery("findItemsByCategory");
		query.setParameter("category", category);
		query.setMaxResults(1);
		if(query.list().isEmpty()) return false; else return true;
	}
	
	@Override
	public boolean havingSubtitlesExists(Subtitles subtitles) {
		
		Query query = getSession().getNamedQuery("findItemsBySubtitles");
		query.setParameter("subtitles", subtitles);
		query.setMaxResults(1);
		if(query.list().isEmpty()) return false; else return true;
	}

	@Override
	public Long countItems(String category) {
		
		Query query = getSession().getNamedQuery("countItemsHavingCategory");
		query.setParameter("category", category);
		return (Long) query.uniqueResult();
	}
	
	@Override
	public Item findLastDate(String parent) {
		
		Query query = getSession().getNamedQuery("findLastItemHavingParent");
		query.setParameter("parent", parent);
		query.setMaxResults(1);
		return (Item) query.uniqueResult();
	}
	
	@Override
	public Integer findNextPlace(String parent) {
		
		Query query = getSession().getNamedQuery("findNextPlaceHavingParent");
		query.setParameter("parent", parent);
		return (Integer) query.uniqueResult();
	}
}
