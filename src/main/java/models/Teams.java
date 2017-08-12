package models;

import java.security.PrivateKey;
import java.util.ArrayList;

public class Teams {
    private String teamName;
    private static ArrayList<Teams> instances=new ArrayList<>();
    private int id;

    public Teams(String teamName){
        this.teamName=teamName;
        instances.add(this);
        this.id=instances.size();
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
    public int getId(){
        return id;
    }
    public static Teams locateById(int id){
        return instances.get(id-1);
    }
}
