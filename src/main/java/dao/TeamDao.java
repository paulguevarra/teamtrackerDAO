package dao;

import models.Teams;

import java.util.List;

public interface TeamDao {
    //create
    void addTeam (Teams teams);

    //read
    List<Teams> getAllTeams();
    Teams locateById(int id);
    //update
    void update(String teamName, String Description, int id);
    //delete
    void deleteTeam(int id);
    void deleteAllTeams();

}
