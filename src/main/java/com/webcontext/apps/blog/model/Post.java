/**
 * 
 */
package com.webcontext.apps.blog.model;

import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * An entity to store some fantastic piece of text art.
 * 
 * @author Frédéric Delorme
 *
 */
@Entity
@Table(name = "blogposts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "TITLE")
	@Size(min = 4, max = 150)
	private String title;

	@Column(name = "COVER")
	@Size(min = 0, max = 255)
	@URL
	private String cover;

	@Column(name = "HEADER")
	@Size(min = 0, max = 300, message = "header must not be empty")
	private String header;

	@Column(name = "CONTENT")
	@NotEmpty
	@Size(min = 1, max = 4000, message = "content must not be empty")
	private String content;

	@Column(name = "CREATED_AT")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@CreationTimestamp
	private Date createdAt;
	@Column(name = "CREATED_BY")
	@Size(min = 2, max = 30, message = "author can not be empty")
	private String createdBy;

	@Column(name = "LOCALE")
	private Locale locale;

	@Column(name = "RATED")
	private String rated;

	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private PublicationState status;

	/**
	 * Default constructor.
	 */
	public Post() {
		super();
		locale = new Locale("fr", "FR");
	}

	/**
	 * @param title
	 * @param cover
	 * @param header
	 * @param content
	 * @param createdAt
	 * @param createdBy
	 * @param locale
	 * @param rated
	 * @param status
	 */
	public Post(String title, String cover, String header, String content, Date createdAt, String createdBy,
			Locale locale, String rated, PublicationState status) {
		this();
		this.title = title;
		this.cover = cover;
		this.header = header;
		this.content = content;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.locale = locale;
		this.rated = rated;
		this.status = status;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId( Integer id ) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle( String title ) {
		this.title = title;
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header
	 *            the header to set
	 */
	public void setHeader( String header ) {
		this.header = header;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent( String content ) {
		this.content = content;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt( Date createdAt ) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy( String createdBy ) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the publish
	 */
	public PublicationState getStatus() {
		return status;
	}

	/**
	 * @param publish
	 *            the publish to set
	 */
	public void setStatus( PublicationState status ) {
		this.status = status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Post [id=").append(id).append(", title=\"").append(title).append("\", cover=\"").append(cover)
				.append("\", header=\"").append(header).append("\", content=\"").append(content)
				.append("\", createdAt=\"").append(createdAt).append("\", createdBy=\"").append(createdBy)
				.append("\", locale=\"").append(locale).append("\", rated=\"").append(rated).append("\", status=\"")
				.append(status).append("\"]");
		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((cover == null) ? 0 : cover.hashCode());
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((header == null) ? 0 : header.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		result = prime * result + ((rated == null) ? 0 : rated.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Post)) {
			return false;
		}
		Post other = (Post) obj;
		if (content == null) {
			if (other.content != null) {
				return false;
			}
		} else if (!content.equals(other.content)) {
			return false;
		}
		if (cover == null) {
			if (other.cover != null) {
				return false;
			}
		} else if (!cover.equals(other.cover)) {
			return false;
		}
		if (createdAt == null) {
			if (other.createdAt != null) {
				return false;
			}
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (createdBy == null) {
			if (other.createdBy != null) {
				return false;
			}
		} else if (!createdBy.equals(other.createdBy)) {
			return false;
		}
		if (header == null) {
			if (other.header != null) {
				return false;
			}
		} else if (!header.equals(other.header)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (locale == null) {
			if (other.locale != null) {
				return false;
			}
		} else if (!locale.equals(other.locale)) {
			return false;
		}
		if (rated == null) {
			if (other.rated != null) {
				return false;
			}
		} else if (!rated.equals(other.rated)) {
			return false;
		}
		if (status != other.status) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}

}
