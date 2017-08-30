package models;


import java.util.ArrayList;

public class Teams {
    private String teamname;
    private String description;
    private int teamid;



    public Teams(String teamname, String description){
        this.teamname=teamname;
        this.description=description;

    }
    public String getTeamname(){
        return teamname;
    }
    public String getDescription() {
        return description;
    }
    public int getTeamid() {
        return teamid;
    }

    public void setTeamid(int teamid) {
        this.teamid = teamid;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teams teams = (Teams) o;

        if (teamid != teams.teamid) return false;
        if (!teamname.equals(teams.teamname)) return false;
        return description.equals(teams.description);
    }

    @Override
    public int hashCode() {
        int result = teamname.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + teamid;
        return result;
    }
}
