package it.improvity.beans;

public class Vetrina {
	private Long id;
	private Long isdebug;
	private String description;
	private String site_meta_description;
	private String site_meta_author;
	private String site_meta_copyright;
	private String site_meta_keywords;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getSite_meta_description() {
		return site_meta_description;
	}

	public void setSite_meta_description(String site_meta_description) {
		this.site_meta_description = site_meta_description;
	}

	public String getSite_meta_author() {
		return site_meta_author;
	}

	public void setSite_meta_author(String site_meta_author) {
		this.site_meta_author = site_meta_author;
	}

	public String getSite_meta_copyright() {
		return site_meta_copyright;
	}

	public void setSite_meta_copyright(String site_meta_copyright) {
		this.site_meta_copyright = site_meta_copyright;
	}

	public String getSite_meta_keywords() {
		return site_meta_keywords;
	}

	public void setSite_meta_keywords(String site_meta_keywords) {
		this.site_meta_keywords = site_meta_keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getIsdebug() {
		return isdebug;
	}

	public void setIsdebug(Long isdebug) {
		this.isdebug = isdebug;
	}
}
