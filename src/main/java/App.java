import java.util.HashMap;
import java.util.Map;

import models.Teams;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String,Object> model=new HashMap<>();
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/new", (request, response) -> {
            Map<String,Object> model=new HashMap<>();
            String teamName=request.queryParams("teamName");
            Teams newTeam=new Teams("teamName");
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
    }
}