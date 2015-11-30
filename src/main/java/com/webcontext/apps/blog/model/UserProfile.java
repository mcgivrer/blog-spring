/**
 * file: UserProfile.java
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

/**
 * User profiles
 * 
 * @author Frédéric Delorme
 *
 */
public enum UserProfile {
	/**
	 * Anybody can read posts
	 */
	PUBLIC,
	/**
	 * A user can write posts
	 */
	WRITER,
	/**
	 * A user can manage all blog entities.
	 */
	ADMIN
}
