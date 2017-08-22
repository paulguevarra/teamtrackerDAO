package models;

import java.util.ArrayList;

public class Members {
    private int memberTeamId;
    private String memberName;
    private static ArrayList<Members> roster=new ArrayList<>();

    public Members(int teamId, String memberName){
        this.memberTeamId=teamId;
        this.memberName=memberName;
        roster.add(this);
    }
    public String getMemberName(){
        return memberName;
    }
    public int getMemberTeamId(){
        return memberTeamId;
    }
    public static ArrayList<Members>getAll(){
        return roster;
    }
}
