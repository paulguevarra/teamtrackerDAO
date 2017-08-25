
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Members;
import models.Teams;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        //Teams:

        //get: show new team form
        get("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "newteam-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new team form
        post("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String teamName = request.queryParams("teamName");
            Teams newTeam = new Teams(teamName);
            model.put("teams", newTeam);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show all teams list
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Teams> teams = Teams.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual team
        get("/teams/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToFind = Integer.parseInt(request.params("id"));
            Teams foundTeam = Teams.locateById(idOfTeamToFind);
            model.put("teams", foundTeam);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update an individual team
        get("/teams/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToEdit = Integer.parseInt(request.params("id"));
            System.out.println(idOfTeamToEdit);
            Teams editTeam = Teams.locateById(idOfTeamToEdit);
            model.put("editTeam", editTeam);
            return new ModelAndView(model, "newteam-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update an individual team
        post("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newTeamName = req.queryParams("teamName");
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
            Teams editTeams = Teams.locateById(idOfTeamToEdit);
            editTeams.update(newTeamName);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //Members:

        //get: show new members form
        get("/teams/:id/members/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Teams> teams = Teams.getAll();
            List<Members> members = Members.getAll();
            model.put("teams", teams);
            model.put("members", members);
            return new ModelAndView(model, "newmember-form.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show all members list
        get("/teams/:id/members", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Members> members = Members.getAll();
            model.put("members", members);
            return new ModelAndView(model, "members-roster.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new members form
        post("/teams/:id/members/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String memberTeams = request.queryParams("teamName");
            String memberName = request.queryParams("memberName");
            Integer teamsId = Integer.parseInt(request.queryParams("id"));
            Members members = new Members(memberName, memberTeams, teamsId);
            model.put("members", members);
            model.put("id", teamsId);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

    }
}

