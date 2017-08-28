package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Members {
    private String memberTeam;
    private int teamId;
    private String memberName;
    private int memberId;
    private static ArrayList<Members> allMembers = new ArrayList<>();
    private ArrayList<String> teamRoster;

    public Members(String name, int teamId){
        memberName = name;
        this.teamId = teamId;
        this.teamRoster=new ArrayList<>();
        teamRoster.add(name);
        allMembers.add(this);
        this.memberId=allMembers.size();


    }

    public void setMemberName(String name){ this.memberName=name;}
    public void setTeamId(int teamId){ this.teamId=teamId;}
    public void updateMember(String name, int teamId){
        this.memberName=name;
        this.teamId=teamId;
    }
    public String getMemberName() {
        return memberName;
    }
    public int getTeamId() {
        return teamId;
    }

    public ArrayList<String> getTeamRoster(){
        return teamRoster;
    }
}
//
//    public static ArrayList<Members> getAllMembers() {
//        return roster;
//    }

//    public int getMemberId() {
//        return memberId;
//    }
//    public static void clearAllMembers(){
//        roster.clear();
//    }
//    public String getMemberTeam() {
//        return memberTeam;
//    }

