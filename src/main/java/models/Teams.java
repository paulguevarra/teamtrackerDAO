package models;


import java.util.ArrayList;

public class Teams {
    private String teamName;
    private String description;
    private static ArrayList<Teams> instances=new ArrayList<>();
    private int id;
    private Members members;
    private ArrayList<Members> roster;

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

    public ArrayList<Members>addToRoster(Members members){
        this.roster.add(members);
        return roster;
    }

    public Members getMembers() {
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

    public ArrayList<Members> getRoster() {
        return roster;
    }


}
