package gr.blxbrgld.mylist.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.solr.analysis.HTMLStripCharFilterFactory;
import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.PhoneticFilterFactory;
import org.apache.solr.analysis.StandardFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.CharFilterDef;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.validator.constraints.Length;

/**
 * Artist Java Bean
 * @author blxbrgld
 */
@NamedQueries({
	@NamedQuery(
			name = "findArtistByTitle",
			query = "FROM Artist WHERE title = :title"),
	@NamedQuery(
			name = "findLastArtist",
			query = "FROM Artist ORDER BY dateUpdated DESC"),
	@NamedQuery(
			name = "findArtistsLike",
			query = "FROM Artist WHERE title LIKE :term ORDER BY title ASC")
})
@Entity
@AnalyzerDef(
	name = "artistAnalyzer",
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
@Analyzer(definition = "artistAnalyzer")
@Table(name = "Artists")
public class Artist extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@Field
	@NotNull
	@Length(min = 1, max = 255)
	@Column(name = "Title")
	private String title;
	
	@Field
	@Lob
	@Column(name = "Description", columnDefinition = "text")
	private String description;
	
	@ContainedIn
	@OneToMany(mappedBy = "idArtist")
	private List<ArtistActivityItem> artistActivityItems;

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<ArtistActivityItem> getArtistActivityItems() {
		return artistActivityItems;
	}
	
	public void setArtistActivityItems(List<ArtistActivityItem> artistActivityItems) {
		this.artistActivityItems = artistActivityItems;
	}
	
	/**
	 * @return String Representation Of Artist Object
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .appendSuper(super.toString())
            .append("title", title)
            .append("description", description)
            .toString();
	}
}
