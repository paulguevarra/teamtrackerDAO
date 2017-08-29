
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Sql2oMembersDao;
import dao.Sql2oTeamsDao;
import models.Members;
import models.Teams;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oMembersDao memberDao = new Sql2oMembersDao(sql2o);
        Sql2oTeamsDao teamDao = new Sql2oTeamsDao(sql2o);

        //get: show new team form
        get("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Teams> teams = teamDao.getAllTeams();
            model.put("teams", teams);
            return new ModelAndView(model, "newteam-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new team form
        post("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
//            ArrayList<String>roster=new ArrayList<>();
            String teamName = request.queryParams("teamname");
            String teamDescription = request.queryParams("description");
            Teams teams = new Teams(teamName, teamDescription);
            teamDao.addTeam(teams);
            List<Teams>teamsList=teamDao.getAllTeams();
            model.put("teams",teams);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
//
//        //get: show all teams
//        get("/teams",(request, response) -> {
//            Map<String,Object> model = new HashMap<>();
//            ArrayList<Teams> teams = Teams.getAll();
//            model.put("teams", teams);
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());


        //get: show all teams list
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Teams> teamsList = teamDao.getAllTeams();
            model.put("teams", teamsList);
            List<Members> membersList = memberDao.getAllMembers();
            model.put("members", membersList);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual team
        get("/teams/:teamid", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToFind = Integer.parseInt(request.params("teamid"));
            List<Teams> teamsList = teamDao.getAllTeams();
            Teams foundTeam = teamDao.locateById(idOfTeamToFind);
            model.put("teams", foundTeam);
            List<Members> membersList = teamDao.getAllMembersByTeamId(idOfTeamToFind);
            model.put("members",membersList);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update an individual team
        get("/teams/:teamid/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToEdit = Integer.parseInt(request.params("teamid"));
            System.out.println(idOfTeamToEdit);
            Teams editTeam = teamDao.locateById(idOfTeamToEdit);
            model.put("editTeam", editTeam);
            return new ModelAndView(model, "newteam-form.hbs");
        }, new HandlebarsTemplateEngine());
        //get: delete an Attendee of a specific event
        get("/teams/:teamid/members/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfMember = Integer.parseInt(req.params("memberid"));
            memberDao.deleteMember(idOfMember);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

//        //get: show a form to update team roster
//        get("/teams/:teamid/addMember", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfTeamToEdit = Integer.parseInt(request.params("teamid"));
//            System.out.println(idOfTeamToEdit);
//            Teams modifyTeamRoster = teamDao.locateById(idOfTeamToEdit);
//            model.put("modifyTeamRoster", modifyTeamRoster);
//            return new ModelAndView(model, "newmember-form.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //post: add new member to team roster
//        post("/teams/:teamid/addMember", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            String memberName = request.queryParams("name");
//            int teamid=Integer.parseInt(request.params("teamid"));
//            Members anotherMember = new Members(memberName,teamid);
//            anotherMember.updateMember(memberName,teamid);
//            Teams modifyTeamRoster = teamDao.locateById(teamid);
//            modifyTeamRoster.addToRoster(anotherMember);
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());

        //post: process a form to update an individual team
        post("/teams/:teamid/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newTeamName = request.queryParams("teamName");
            String newDescription = request.queryParams("description");
            String newMemberName = request.queryParams("membername");
            int idOfTeamToEdit = Integer.parseInt(request.params("teamid"));
            Teams editTeams = teamDao.locateById(idOfTeamToEdit);
            teamDao.update(newTeamName,newDescription, teamDao.locateById(idOfTeamToEdit).getId());
            Members newMember = new Members(newMemberName, idOfTeamToEdit);
            memberDao.addMember(newMember);
            model.put("member", newMember);
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

