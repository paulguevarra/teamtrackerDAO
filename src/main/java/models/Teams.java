package models;


import java.util.ArrayList;

public class Teams {
    private String teamName;
    private String description;
    private static ArrayList<Teams> instances=new ArrayList<>();
    private int id;
    private String members;
    private ArrayList<String> roster;

    public Teams(String teamName,String description){
        this.teamName=teamName;
        this.description=description;
        this.roster=new ArrayList<>();
        instances.add(this);
        this.id=instances.size();
    }

    public String getTeamName(){
        return teamName;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String>addToRoster(String memberName){
        this.roster.add(memberName);
        return roster;
    }

    public String getMembers() {
        return members;
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

    public void update(String teamName, String description){
        this.teamName=teamName;
        this.description = description;
    }

    public ArrayList<String> getRoster() {
        return roster;
    }

}
