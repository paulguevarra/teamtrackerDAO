
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

        //get: show new team form
        get("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "newteam-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new team form
        post("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<String>roster=new ArrayList<>();
            String teamName = request.queryParams("teamName");
            String teamDescription = request.queryParams("description");
            Teams teams = new Teams(teamName, teamDescription);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show all teams
        get("/teams",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            ArrayList<Teams> teams = Teams.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "index.hbs");
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
            List<Members> members = Teams.getRoster(idOfTeamToFind);
            Teams foundTeam = Teams.locateById(idOfTeamToFind);
            model.put("teams", foundTeam);
            model.put("members",members);
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

        //get: show a form to update team roster
        get("/teams/:id/addMember", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToEdit = Integer.parseInt(request.params("id"));
            System.out.println(idOfTeamToEdit);
            Teams modifyTeamRoster = Teams.locateById(idOfTeamToEdit);
            model.put("modifyTeamRoster", modifyTeamRoster);
            return new ModelAndView(model, "newmember-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: add new member to team roster
        post("/teams/:id/addMember", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String memberName = request.queryParams("name");
            int teamId=Integer.parseInt(request.params("id"));
            Members anotherMember = new Members(memberName,teamId);
            anotherMember.updateMember(memberName,teamId);
            Teams modifyTeamRoster = Teams.locateById(teamId);
            modifyTeamRoster.addToRoster(anotherMember);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update an individual team
        post("/teams/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newTeamName = request.queryParams("teamName");
            String newDescription = request.queryParams("description");
//            String memberThree = request.queryParams("memberThree");
            int idOfTeamToEdit = Integer.parseInt(request.params("id"));
            Teams editTeams = Teams.locateById(idOfTeamToEdit);
            editTeams.update(newTeamName, newDescription);
//            editTeams.addToRoster(memberThree);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //Members:

        //get: show new members form
// 1       get("/teams/:id/modifyRoster", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            return new ModelAndView(model, "newmember-form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//
//
//
// 1       get("/teams/:id/members/new", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            List<Teams> teams = Teams.getAll();
//            List<Members> members = Members.getAll();
//            model.put("teams", teams);
//            model.put("members", members);
//            return new ModelAndView(model, "newmember-form.hbs");
//        }, new HandlebarsTemplateEngine());

        //get: show all members list
//        get("/teams/:id/addMember", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
////            ArrayList<Members> members = Members.getAll();
////            model.put("members", members);
//            return new ModelAndView(model, "newmember-form.hbs");
//        }, new HandlebarsTemplateEngine());

        //post: process new members form
//        post("/teams/:id/addMember", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            String teamName = request.queryParams("teamName");
//            String memberThree = request.queryParams("memberThree");
//            Teams modifiedTeam = Teams.locateTeam(teamName);
//
//            modifiedTeam.addToRoster(memberThree);
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());

    }
}

