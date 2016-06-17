package controllers;

import controllers.action.Secured;
import models.Membre;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.*;

/**
 * Created by brick on 16/06/2016.
 */
@Security.Authenticated(Secured.class)
public class ApplicationController extends Controller {

    public Result accueil() {
        String email = session("membre");
        Membre membre= Membre.byEmail(email);
        return ok(accueil.render(membre));
    }
}
