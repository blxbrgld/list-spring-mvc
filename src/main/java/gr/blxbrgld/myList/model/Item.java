package gr.blxbrgld.mylist.model;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.solr.analysis.HTMLStripCharFilterFactory;
import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.PhoneticFilterFactory;
import org.apache.solr.analysis.StandardFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OrderBy;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.CharFilterDef;
import org.hibernate.search.annotations.ClassBridge;
import org.hibernate.search.annotations.ClassBridges;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.FullTextFilterDef;
import org.hibernate.search.annotations.FullTextFilterDefs;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.NumericField;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import gr.blxbrgld.mylist.utilities.ArtistActivityItemBridge;
import gr.blxbrgld.mylist.utilities.CategoryBridge;
import gr.blxbrgld.mylist.utilities.CategoryFilterFactory;
import gr.blxbrgld.mylist.utilities.CommonsMultipartFileValid;
import gr.blxbrgld.mylist.utilities.ParentCategoryBridge;
import gr.blxbrgld.mylist.utilities.ParentCategoryFilterFactory;

/**
 * Item Java Bean
 * @author blxbrgld
 */
@NamedQueries({
	@NamedQuery(
			name = "findItemsByCategory",
			query = "FROM Item WHERE category = :category OR category.parent = :category"),
	@NamedQuery(
			name = "findItemsByArtist",
			query = "SELECT DISTINCT i FROM Item i, IN (i.artistActivityItems) c WHERE c.idArtist = :artist ORDER BY i.titleEng"),
	@NamedQuery(
			name = "findItemsBySubtitles",
			query = "FROM Item WHERE subtitles = :subtitles"),
	@NamedQuery(
			name = "countItemsHavingCategory",
			query = "SELECT COUNT(*) FROM Item WHERE category.title = :category OR category.parent.title = :category"),			
	@NamedQuery(
			name = "findLastItemHavingParent",
			query = "FROM Item WHERE category.parent.title = :parent ORDER BY dateUpdated DESC"),
	@NamedQuery(
			name = "findNextPlaceHavingParent",
			query = "SELECT max(place) + 1 FROM Item WHERE category.parent.title = :parent")
})
@Entity
@Indexed
@AnalyzerDef(
	name = "itemAnalyzer",
	charFilters = {
		@CharFilterDef(factory = HTMLStripCharFilterFactory.class)
	},
	tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
	filters = {
		@TokenFilterDef(factory = StandardFilterFactory.class),
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = PhoneticFilterFactory.class, params = {
			@Parameter(name = "encoder", value = "Metaphone")
		})
	}
)
@Analyzer(definition = "itemAnalyzer")
@FullTextFilterDefs({
	@FullTextFilterDef(
			name = "parentCategoryFilter",
			impl = ParentCategoryFilterFactory.class),
	@FullTextFilterDef(
			name = "categoryFilter",
			impl = CategoryFilterFactory.class),	
})
@ClassBridges({
	@ClassBridge(
			name = "itemParentCategory",
			impl = ParentCategoryBridge.class),
	@ClassBridge(
			name = "itemCategory",
			impl = CategoryBridge.class)
})
@Table(name = "Items")
public class Item extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@Fields({
		@Field,
		@Field(name = "sortTitle", analyze = Analyze.NO)
	})
	@NotNull
	@Length(min = 1, max = 255)
	@Column(name = "TitleEng")
	private String titleEng;
	
	@Field
	@Length(max = 255)
	@Column(name = "TitleEll")
	private String titleEll;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "Category", referencedColumnName = "Id", nullable = false)
	private Category category;
	
	@Length(max = 45)
	@Column(name = "photoPath")
	private String photoPath;
	
	@CommonsMultipartFileValid(message = "{gr.blxbrgld.mylist.utilities.constraints.CommonsMultipartFileValid.message}")
    @Transient
	private CommonsMultipartFile photo;
	
	@Field
	@Lob
	@Column(name = "Description", columnDefinition = "text")
	private String description;
	
	@Range(min = 1900, max = 2100)
	@Column(name = "Year")
	private Integer year;
	
	@Field
	@NumericField
	@Column(name = "Rating")
	private Integer rating;
	
	@ManyToOne
	@JoinColumn(name = "Subtitles", referencedColumnName = "Id", nullable = true)
	private Subtitles subtitles;
	
	@NotNull
	@Range(min = 1, max = 100)
	@Column(name = "Discs")
	private Integer discs;
	
	@Min(1)
	@Column(name = "Place")
	private Integer place;
	
	@Field(name = "sortArtist", analyze = Analyze.NO)
	@FieldBridge(impl = ArtistActivityItemBridge.class)
	@IndexedEmbedded
	@OneToMany(mappedBy = "idItem", fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE })
	@OrderBy(clause = "id ASC")
	@Fetch(FetchMode.SELECT)
	private List<ArtistActivityItem> artistActivityItems;
	
	@OneToMany(mappedBy = "idItem", fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE })
	@OrderBy(clause = "id ASC")
	@Fetch(FetchMode.SELECT)
	private Set<CommentItem> commentItems;

	public String getTitleEng() {
		return titleEng;
	}
	
	public void setTitleEng(String titleEng) {
		this.titleEng = titleEng;
	}
	
	public String getTitleEll() {
		return titleEll;
	}
	
	public void setTitleEll(String titleEll) {
		this.titleEll = titleEll;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String getPhotoPath() {
		return photoPath;
	}
	
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
	public CommonsMultipartFile getPhoto() {
		return photo;
	}
	
	public void setPhoto(CommonsMultipartFile photo) {
		this.photo = photo;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getYear() {
		return year;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}
	
	public Integer getRating() {
		return rating;
	}
	
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	public Subtitles getSubtitles() {
		return subtitles;
	}
	
	public void setSubtitles(Subtitles subtitles) {
		this.subtitles = subtitles;
	}
	
	public Integer getDiscs() {
		return discs;
	}
	
	public void setDiscs(Integer discs) {
		this.discs = discs;
	}
	
	public Integer getPlace() {
		return place;
	}
	
	public void setPlace(Integer place) {
		this.place = place;
	}
	
	public List<ArtistActivityItem> getArtistActivityItems() {
		return artistActivityItems;
	}
	
	public void setArtistActivityItems(List<ArtistActivityItem> artistActivityItems) {
		this.artistActivityItems = artistActivityItems;
	}
	
	public Set<CommentItem> getCommentItems() {
		return commentItems;
	}
	
	public void setCommentItems(Set<CommentItem> commentItems) {
		this.commentItems = commentItems;
	}
	
	/**
	 * Get Comment Titles From commentItems Separated With ' | ' Characters
	 * @return String Of Item's Comment Titles
	 */
	public String getCommentsString() {
		StringBuilder comments = new StringBuilder();
		Iterator<CommentItem> iterator = commentItems.iterator();
		while(iterator.hasNext()) {
			comments.append(iterator.next().getIdComment().getTitle()).append(" | ");
		}
        return comments.length()>0 ? comments.toString().substring(0,comments.toString().length()-3) : null;
	}

	/**
	 * Get Artist Titles From artistActivityItems Separated With ' | ' Characters. 'Actor' Activity Is Excluded
	 * @return String Of Item's Artist Titles
	 */
	public String getArtistsString() {
		StringBuilder artists = new StringBuilder();
		for(int i = 0; i < artistActivityItems.size(); i++) {
			if(!"Actor".equals(artistActivityItems.get(i).getIdActivity().getTitle())) {
				artists.append(artistActivityItems.get(i).getIdArtist().getTitle()).append(" | ");
			}
		}
        return artists.length()>0 ? artists.toString().substring(0,artists.toString().length()-3) : null;
	}
	
	/**
	 * @return String Representation Of Item Object
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .appendSuper(super.toString())
            .append("titleEng", titleEng)
            .append("titleEll", titleEll)
            .append("category", category)
            .append("photoPath", photoPath)
            .append("description", description)
            .append("year", year)
            .append("rating", rating)
            .append("subtitles", subtitles)
            .append("discs", discs)
            .append("place", place)
            .toString();
	}
}
