/**
 * 
 */
package com.webcontext.apps.blog.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author frederic
 *
 */
@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String title;

	@Column
	private String header;

	@Column
	private String content;

	@Column
	private Date createdAt;

	@Column
	private String createdBy;

	@Column
	private PublicationState publish;

	/**
	 * @param id
	 * @param title
	 * @param header
	 * @param content
	 * @param createdAt
	 * @param createdBy
	 * @param publish
	 */
	public Post(Long id, String title, String header, String content, Date createdAt, String createdBy,
			PublicationState publish) {
		super();
		this.id = id;
		this.title = title;
		this.header = header;
		this.content = content;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.publish = publish;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
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
	public void setTitle(String title) {
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
	public void setHeader(String header) {
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
	public void setContent(String content) {
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
	public void setCreatedAt(Date createdAt) {
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
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the publish
	 */
	public PublicationState getPublish() {
		return publish;
	}

	/**
	 * @param publish
	 *            the publish to set
	 */
	public void setPublish(PublicationState publish) {
		this.publish = publish;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Post [id=").append(id).append(", title=").append(title).append(", header=").append(header)
				.append(", content=").append(content).append(", createdAt=").append(createdAt).append(", createdBy=")
				.append(createdBy).append("]");
		return builder.toString();
	}

}
