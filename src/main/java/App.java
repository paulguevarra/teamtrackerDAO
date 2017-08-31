
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
            List<Teams> teams = teamDao.getAllteams();
            model.put("teams", teams);
            return new ModelAndView(model, "newteam-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new team form
        post("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String teamname = request.queryParams("teamname");
            String teamdescription = request.queryParams("description");
            Teams teams = new Teams(teamname, teamdescription);
            teamDao.addteam(teams);
            List<Teams> teamsList = teamDao.getAllteams();
            model.put("teams", teamsList);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update an individual team
        get("/teams/:teamid/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idofteamtoedit = Integer.parseInt(request.params("teamid"));
            System.out.println(idofteamtoedit);
            Teams editteam = teamDao.locatebyid(idofteamtoedit);
            model.put("editteam", editteam);
            List<Members> members = memberDao.getAllmembers();
            model.put("members", members);
            return new ModelAndView(model, "newteam-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update an individual team
        post("/teams/:teamid/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newteamname = request.queryParams("teamname");
            String newdescription = request.queryParams("description");
            String newmembername = request.queryParams("membername");
            int idofteamtoedit = Integer.parseInt(request.params("teamid"));
            Teams editteams = teamDao.locatebyid(idofteamtoedit);
            teamDao.update(newteamname, newdescription, teamDao.locatebyid(idofteamtoedit).getTeamid());
            Members newmember = new Members(newmembername, idofteamtoedit);
            memberDao.addMember(newmember);
            model.put("member", newmember);
            model.put("editteam", editteams);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show all teams list
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Members> membersList = memberDao.getAllmembers();
            model.put("members", membersList);
            List<Teams> teamsList = teamDao.getAllteams();
            model.put("teams", teamsList);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual team
        get("/teams/:teamid", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idofteamtofind = Integer.parseInt(request.params("teamid"));
            Teams foundteam = teamDao.locatebyid(idofteamtofind);
            model.put("teams", foundteam);
            List<Members> membersList = teamDao.getAllmembersbyteamid(idofteamtofind);
            model.put("members", membersList);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update an individual member
        get("/teams/:teamid/members/:memberid/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idofteamtoedit = Integer.parseInt(request.params("teamid"));
            int idofmembertoedit = Integer.parseInt(request.params("memberid"));
            System.out.println(idofteamtoedit);
            Members editmember = memberDao.locatememberbyid(idofmembertoedit);
            model.put("editmember", editmember);
            return new ModelAndView(model, "newmember-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update an individual member
        post("/teams/:teamid/members/:memberid/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String membername = request.queryParams("membername");
            int idofteamtoedit = Integer.parseInt(request.params("teamid"));
            int idofmembertoedit = Integer.parseInt(request.params("memberid"));
            memberDao.updatemember(membername, idofteamtoedit,idofmembertoedit);
            List<Members> members= memberDao.getAllmembers();
            model.put("members", members);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete a member of a specific team
        get("/teams/:teamid/members/:memberid/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idofmember = Integer.parseInt(req.params("memberid"));
            memberDao.deletemember(idofmember);
            List<Teams> teams = teamDao.getAllteams();
            model.put("teams", teams);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete a team and its members
        get("/teams/:teamid/delete", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            int teamid = Integer.parseInt(request.params("teamid"));
            List<Members> teamMembers =teamDao.getAllmembersbyteamid(teamid);
            teamDao.deleteteam(teamid);
            teamMembers.clear();
            List<Teams> teams = teamDao.getAllteams();
            List<Members> members = memberDao.getAllmembers();
            model.put("teams", teams);
            model.put("members", members);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
//        get("/teams/:teamid/members/new", (request, response)->{
//            Map<String, Object> model = new HashMap<>();
//            int idofteamtoedit = Integer.parseInt(request.params("teamid"));
//            String membername=request.queryParams("membername");
//            List<Teams> teams = teamDao.getAllteams();
//            List<Members> members = memberDao.getAllmembers();
//            model.put("teams",teams);
//            model.put("members",members);
//            return new ModelAndView(model, "newmember-form.hbs");
//        }, new HandlebarsTemplateEngine());


//        //remove a team
//

//        //Members:
        //get: display member form

//
//        //post: process member team form
//        post("/members/new", (req,res)->{
//            Map<String, Object> model = new HashMap<>();
//            String teamChoice= req.queryParams("team");
//            int teamId = Integer.parseInt(teamChoice);
//            Teams foundTeam = teamDao.locateById(teamId);
//            String memberName = req.queryParams("member-name");
//            Members newMember = new Members(memberName, foundTeam.getId());
//            memberDao.addMember(newMember);
//            List<Members> members = memberDao.getAllMembers();
//            List<Teams> teams = teamDao.getAllTeams();
//            model.put("members", members);
//            model.put("teams", teams);
//            return new ModelAndView(model,"success.hbs");
//        }, new HandlebarsTemplateEngine());





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
