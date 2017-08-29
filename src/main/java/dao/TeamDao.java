package dao;

import models.Members;
import models.Teams;

import java.util.List;

public interface TeamDao {
    //create
    void addTeam (Teams teams);

    //read
    List<Teams> getAllTeams();
    List<Members>getAllMembersByTeamId(int teamid);
    Teams locateById(int teamid);
    //update
    void update(String teamName, String Description, int teamid);
    //delete
    void deleteTeam(int teamid);
    void deleteAllTeams();

}
