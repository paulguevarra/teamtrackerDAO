package models;

import java.security.PrivateKey;

public class Teams {
    private String teamName;

    public Teams(String teamName){
        this.teamName=teamName;
    }
    public String getTeamName(){
        return teamName;
    }
}
