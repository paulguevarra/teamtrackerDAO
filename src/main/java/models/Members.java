package models;

import java.util.ArrayList;

public class Members {
    private int memberTeamId;
    private String memberName;
    private int memberId;
    private static ArrayList<Members> roster=new ArrayList<>();
//    private static ArrayList<Members> teamRoster=new ArrayList<>();

    public Members(int teamId, String memberName){
        this.memberTeamId=teamId;
        this.memberName=memberName;
        roster.add(this);
        this.memberId=roster.size();
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
    public int getMemberId(){
        return memberId;
    }
//    public static ArrayList<Members>getTeamRoster(Integer memberTeamId){
//
//        for (int i=0;i<roster.size();i++)
//            if(memberTeamId.equals(roster.get(i)))
//                teamRoster.add(i);
//        return teamRoster;
//    }
}
