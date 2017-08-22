package models;

public class Members {
    private int teamId;
    private String memberName;

    public Members(int teamId, String memberName){
        this.teamId=teamId;
        this.memberName=memberName;
    }
    public String getMemberName(){
        return memberName;
    }
}
