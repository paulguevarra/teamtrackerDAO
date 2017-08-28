package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Members {
    private String memberTeam;
    private int memberTeamId;
    private String memberName;
    private int memberId;
    private static ArrayList<Members> roster = new ArrayList<>();

    public Members(){
        roster.add(this);
    }

    public void setAnotherMember(String name) {
//        this.memberTeam = teamName;
        memberName = name;
//        roster.add(this);
//        this.memberId = roster.size();
//        this.memberTeamId = teamId;
    }

    public String getMemberName() {
        return memberName;
    }

    public static ArrayList<Members> getAllMembers() {
        return roster;
    }

//    public int getMemberId() {
//        return memberId;
//    }
//    public static void clearAllMembers(){
//        roster.clear();
//    }
//    public String getMemberTeam() {
//        return memberTeam;
//    }

//    public int getMemberTeamId() {
//        return memberTeamId;
//    }
}

