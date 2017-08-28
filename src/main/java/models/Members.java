package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Members {

    private int teamId;
    private String memberName;
    private int memberId;

    public Members(String name, int id){
        this.memberName = name;
        this.teamId = id;
    }
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
    public void setMemberName(String name){ this.memberName=name;}
    public void setTeamId(int teamId){ this.teamId=teamId;}

    public String getMemberName() {
        return memberName;
    }
    public int getTeamId() {
        return teamId;
    }

    public int getMemberId() {
        return memberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Members members = (Members) o;

        if (teamId != members.teamId) return false;
        return memberName.equals(members.memberName);
    }

    @Override
    public int hashCode() {
        int result = teamId;
        result = 31 * result + memberName.hashCode();
        return result;
    }
}
