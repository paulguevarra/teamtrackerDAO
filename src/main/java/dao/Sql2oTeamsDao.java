package dao;

import models.Members;
import models.Teams;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;


import java.util.List;

public class Sql2oTeamsDao implements TeamDao {
    private final Sql2o sql2o;


    public Sql2oTeamsDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void addTeam(Teams teams) {
        String sql = "INSERT INTO teams (teamname,description) VALUES (:teamname, :description)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(teams)
                    .executeUpdate()
                    .getKey();
            teams.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }

    @Override
    public List<Teams> getAllTeams() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM teams")
                    .executeAndFetch(Teams.class); //fetch a list
        }
    }

    @Override
    public Teams locateById(int number) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM teams WHERE id = :id")
                    .addParameter("id", number)
                    .executeAndFetchFirst(Teams.class);
        }
    }
    @Override
    public void update(String newName, String newDescription, int id){
        String sql = "UPDATE teams SET (teamname,description) = (:teamname, :description) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("teamname", newName)
                    .addParameter("description", newDescription)
                    .addParameter("id",id )
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Members> getAllMembersByTeamId(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM members WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetch(Members.class);
        }
    }
    @Override
    public void deleteTeam(int id) {
        String sql = "DELETE FROM teams WHERE id=:id"; //raw sql
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
    @Override
    public void deleteAllTeams() {
        String sql = "DELETE FROM teams"; //raw sql
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
