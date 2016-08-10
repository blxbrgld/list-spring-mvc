package gr.blxbrgld.mylist.dao.hibernate;

import java.util.List;

import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.Query;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import gr.blxbrgld.mylist.dao.ItemDao;
import gr.blxbrgld.mylist.model.Artist;
import gr.blxbrgld.mylist.model.Category;
import gr.blxbrgld.mylist.model.Item;
import gr.blxbrgld.mylist.model.Subtitles;
import gr.blxbrgld.mylist.utilities.ReturningValues;

/**
 * Item's DAO Implementation
 * @author blxbrgld
 */
@Repository
public class HibernateItemDao extends AbstractHibernateDao<Item> implements ItemDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateItemDao.class);

    /**
     * {@inheritDoc}
     */
	@SuppressWarnings("unchecked")
    @Override
	public List<Item> findByCategory(Category category) {
		Query query = getSession().getNamedQuery("findItemsByCategory");
		query.setParameter("category", category);
		return (List<Item>) query.list();
	}

    /**
     * {@inheritDoc}
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findByArtist(Artist artist, int first, int size) {
		Query query = getSession().getNamedQuery("findItemsByArtist");
		query.setParameter("artist", artist);
		query.setFirstResult(first);
		query.setMaxResults(size);
		return (List<Item>) query.list();
	}

    /**
     * {@inheritDoc}
     */
	@SuppressWarnings("unchecked")
	@Override
	public ReturningValues search(String searchFor, String searchIn, String property, String order, int first, int size) {
		FullTextSession fullTextSession = Search.getFullTextSession(getSession());
		QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Item.class).get();
		org.apache.lucene.search.Query luceneQuery;
		String searchString = searchFor != null ? searchFor.trim() : "";
		
		if(searchIn != null && "*".equals(searchString)) { //All Category Items
			luceneQuery = queryBuilder
                .keyword()
                .wildcard()
                .onField("titleEng")
                .matching(searchString)
					.createQuery();
		}
		else if(searchIn != null && "Artist".equals(searchIn)) { //Search Only Artists
			luceneQuery = queryBuilder
				.phrase()
                .onField("artistActivityItems.idArtist.title").boostedTo(2)
                .andField("artistActivityItems.idArtist.description")
                .sentence(searchString)
					.createQuery();
		}
		else if(searchIn != null && "Favorites".equals(searchIn)) { //5- Star Reviews
			luceneQuery = queryBuilder
				.range()
				.onField("rating")
				.above(4)
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
		if(searchIn != null && !"Lists".equals(searchIn)) {
			if("*".equals(searchString)) {
				String[] tokens = StringUtils.tokenizeToStringArray(searchIn, " ");
				if("music".equalsIgnoreCase(tokens[0]) || "films".equalsIgnoreCase(tokens[0])) { //Parent Category Filter
					hibernateQuery.enableFullTextFilter("parentCategoryFilter").setParameter("parentCategory", tokens[0].toLowerCase());
				}
				else { //Category Filter
					hibernateQuery.enableFullTextFilter("categoryFilter").setParameter("category", tokens[0].toLowerCase());
				}
			}
			else if(!"Artist".equals(searchIn)) { //Parent Category Filter
				String parentCategory = "Music".equals(searchIn) ? "music" : "films";
				hibernateQuery.enableFullTextFilter("parentCategoryFilter").setParameter("parentCategory", parentCategory);
			}
		}
		
		/*
		 * Order By Clause
		 */
		boolean ordering = (order != null && "DESC".equals(order)) ? true : false;
		
		if(property != null && "titleEng".equals(property)) {
			Sort sort = new Sort(new SortField("sortTitle", SortField.STRING, ordering));
			hibernateQuery.setSort(sort);
		}
		else if(property != null && "artist".equals(property)) {
			Sort sort = new Sort(new SortField("sortArtist", SortField.STRING, ordering));
			hibernateQuery.setSort(sort);
		}
		
		int noOfResults = hibernateQuery.getResultSize();
		hibernateQuery.setFirstResult(first);
		hibernateQuery.setMaxResults(size);
		
		return new ReturningValues(noOfResults, (List<Item>) hibernateQuery.list());
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public void lucene(boolean synchronously) {
		FullTextSession fullTextSession = Search.getFullTextSession(getSession());
		if(synchronously) {
            try {
                fullTextSession.createIndexer().startAndWait();
            }
            catch(Exception exception) {
                LOGGER.error("Exception", exception);
            }
        }
		else {
			fullTextSession.createIndexer().start();
		}
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public boolean havingCategoryExists(Category category) {
		Query query = getSession().getNamedQuery("findItemsByCategory");
		query.setParameter("category", category);
		query.setMaxResults(1);
        return query.list().isEmpty() ? false : true;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public boolean havingSubtitlesExists(Subtitles subtitles) {
		Query query = getSession().getNamedQuery("findItemsBySubtitles");
		query.setParameter("subtitles", subtitles);
		query.setMaxResults(1);
        return query.list().isEmpty() ? false : true;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public Long countItems(String category) {
		Query query = getSession().getNamedQuery("countItemsHavingCategory");
		query.setParameter("category", category);
		return (Long) query.uniqueResult();
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public Item findLastDate(String parent) {
		Query query = getSession().getNamedQuery("findLastItemHavingParent");
		query.setParameter("parent", parent);
		query.setMaxResults(1);
		return (Item) query.uniqueResult();
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public Integer findNextPlace(String parent) {
		Query query = getSession().getNamedQuery("findNextPlaceHavingParent");
		query.setParameter("parent", parent);
		return (Integer) query.uniqueResult();
	}
}
