package models;

public class Members {
    private int memberTeamId;
    private String memberName;

    public Members(int teamId, String memberName){
        this.memberTeamId=teamId;
        this.memberName=memberName;
    }
    public String getMemberName(){
        return memberName;
    }
    public int getMemberTeamId(){
        return memberTeamId;
    }
}
