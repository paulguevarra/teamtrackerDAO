package models;

import java.security.PrivateKey;
import java.util.ArrayList;

public class Teams {
    private String teamName;
    private static ArrayList<Teams> instances=new ArrayList<>();

    public Teams(String teamName){
        this.teamName=teamName;
        instances.add(this);
    }
    public String getTeamName(){
        return teamName;
    }
    public static ArrayList<Teams> getAll(){
        return instances;
    }
    public static void clearAllTeams(){
        instances.clear();
    }
}
