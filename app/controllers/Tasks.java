/*******************************************************************************
 *        File: Issues.java
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Feb 16, 2014
 *     Project: onto.nudge
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/
package controllers;

import models.Project;
import models.Task;
import models.TaskState;
import play.data.Form;
import play.mvc.*;

@Security.Authenticated(Secured.class)
public class Tasks extends Controller {

	
    public static Result index() {
        return TODO;
    }
    
    public static Result save(Long projectId) {
        
        Form<Task> TaskForm = Form.form(Task.class).bindFromRequest();
        if(TaskForm.hasErrors()) {
            return badRequest();
        }
                
        Project project = Project.find.byId(projectId);
        
        Task Task = TaskForm.get();
        Task.project = project;
        Task.save();
        
        project.save();
        
        //FIXME redirect to index()
        return redirect("/"+projectId);
    }
    
    
    /**
     * List your issues (or a project's issues)
     * @param project
     * @return
     */
    public static Result list() {
    	return TODO;
    }

    /**
     * Show an issue's details.
     * @param issue
     * @return
     */
    public static Result show(Long id) {
    	return TODO;
    }

    public static Result move(Long TaskId) {
        Task task = Task.find.byId(TaskId);
        
        int newValueIndex = (task.state.ordinal()+1)%TaskState.values().length;
        
        /**
         * Don't move back straight from done to proposal!
         */
        if (newValueIndex == 0)
            newValueIndex = TaskState.values().length - 1;

        task.state = TaskState.values()[newValueIndex];
        task.save();
        
    	return redirect("/"+task.project.id);
    }

    public static Result back(Long TaskId) {
        Task task = Task.find.byId(TaskId);
        
        int newValueIndex = (task.state.ordinal()-1)%TaskState.values().length;
        
        /**
         * Don't move to done from proposal state!
         */
        if (newValueIndex<0)
            newValueIndex = 0;
        
        task.state = TaskState.values()[newValueIndex];
        task.save();
        
        return redirect("/"+task.project.id);
    }

    public static Result delete(Long id) {
        Task task = Task.find.byId(id);
        Long projectId = task.project.id;
        
        task.delete();
        
        //FIXME redirect to index()
        return redirect("/" + projectId);
    }

    /**
     * Modify an existing issue.
     * @param issue
     * @return
     */
    public static Result edit(Long id) {
    	return TODO;
    }

    /**
     * Leave a comment on an issue.
     * @param issue
     * @return
     */
    public static Result comment(Long id) {
    	return TODO;
    }

    /**
     * Assign a label to an issue.
     * @param issue
     * @return
     */
    public static Result label(Long id) {
    	return TODO;
    }

    /**
     * Assign an issue to specific user
     * @param issue
     * @param account
     * @return
     */
    public static Result assign(Long issueId, Long accountId) {
    	return TODO;
    }

    /**
     * Unassigns an issue
     * TODO write "listAssigned", "listUnassigned", and "" actions. Or add them to the list somehow...
     * @param issue
     * @return
     */
    public static Result unassign(Long id) {
    	return TODO;
    }
    
    public static Result help() {
    	return TODO;
    }
}
