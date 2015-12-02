/**
 * file: User.java
 * date: 30 nov. 2015
 *
 * GEHC DoseWatch
 *
 * Copyright (c) 2015 by General Electric Company. All rights reserved.
 * 
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 *
 */

package com.webcontext.apps.blog.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * A User for the Blog, who is able to publish posts and comments.
 * 
 * @author Frédéric Delorme
 *
 */
@Entity
@Table(name = "blogusers")
public class User {

	@Id
	@Size(min = 4, max = 30)
	@Column(name = "USERNAME")
	private String username;

	@NotEmpty
	@Size(min = 6, max = 255)
	@Column(name = "PASSWORD")
	private String password;

	@NotEmpty
	@Email
	@Column(name = "EMAIL")
	private String email;

	@Size(min = 0, max = 60)
	@Column(name = "FIRSTNAME")
	private String firstname;

	@Size(min = 0, max = 60)
	@Column(name = "LASTNAME")
	private String lastname;

	@UpdateTimestamp
	@Column(name = "LAST_CONNECTION")
	private Date lastConnection;

	@CreationTimestamp
	@Column(name = "CREATED_AT")
	private Date createdAt;

	@NotEmpty
	@Column(name = "CREATED_BY")
	private String createdBy;

	@Enumerated(EnumType.STRING)
	@Column(name = "USER_PROFILE")
	UserProfile profile;

	/**
	 * Default constructor
	 */
	public User() {
		super();
	}

	/**
	 * /** Parameterized User constructor.
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @param firstname
	 * @param lastname
	 * @param lastConnection
	 * @param createdAt
	 * @param createdBy
	 */
	public User(String username, String password, String email, String firstname, String lastname, Date lastConnection,
			Date createdAt, String createdBy, UserProfile profile) {
		this();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.lastConnection = lastConnection;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.profile = profile;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the lastConnection
	 */
	public Date getLastConnection() {
		return lastConnection;
	}

	/**
	 * @param lastConnection
	 *            the lastConnection to set
	 */
	public void setLastConnection(Date lastConnection) {
		this.lastConnection = lastConnection;
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
	 * @return the profile
	 */
	public UserProfile getProfile() {
		return profile;
	}

	/**
	 * @param profile
	 *            the profile to set
	 */
	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [username=").append(username).append(", password=").append(password).append(", email=")
				.append(email).append(", firstname=").append(firstname).append(", lastname=").append(lastname)
				.append(", lastConnection=").append(lastConnection).append(", createdAt=").append(createdAt)
				.append(", createdBy=").append(createdBy).append(", profile=").append(profile).append("]");
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
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastConnection == null) ? 0 : lastConnection.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
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
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (firstname == null) {
			if (other.firstname != null) {
				return false;
			}
		} else if (!firstname.equals(other.firstname)) {
			return false;
		}
		if (lastConnection == null) {
			if (other.lastConnection != null) {
				return false;
			}
		} else if (!lastConnection.equals(other.lastConnection)) {
			return false;
		}
		if (lastname == null) {
			if (other.lastname != null) {
				return false;
			}
		} else if (!lastname.equals(other.lastname)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}

}
