 /*******************************************************************************
 *        File: Secured.java
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Mar 6, 2015
 *     Project: onto.nudge
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;

import models.*;

public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("email");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.Accounts.login());
    }
}