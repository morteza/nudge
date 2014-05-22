/*******************************************************************************
 *        File: Task.java
      Revision: 2
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: May 22, 2014
 *     Project: onto.nudge
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/
package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.data.validation.*;

@Entity
public class Task extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	@Constraints.Required
	public String title;	
	public String description;

	//@Constraints.Required
//	public Account creator;
	
	public Account assignee;
	
	@Constraints.Required
	public TaskState state = TaskState.PROPOSED;
	
	//TODO temporary. Use status instead!
	//@Constraints.Required
	public Boolean closed = false;
	
	//@Constraints.Required
	public Date createdAt;
	public Date closedAt;
	public Date updatedAt;
	
	//FIXME @Constraints.Required
	//@ManyToOne
	//public Project project;
	
	/**
	 * Generic query helper for entity Computer with id Long
	 */
	public static Finder<Long, Task> find = new Finder<Long, Task>(Long.class, Task.class); 

	    
	public void close() {
		this.closedAt = new Date();
		this.closed = true;
		this.updatedAt = new Date();
		this.save();
	}
	
}
