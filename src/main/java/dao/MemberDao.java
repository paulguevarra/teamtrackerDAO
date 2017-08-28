package dao;

import models.Members;

import java.lang.reflect.Member;
import java.util.List;

public interface MemberDao {
    //create
    void addMember (Members members);

    //read
    List<Members> getAllMembers();
    Members locateMemberById(int id);
    //update
    void updateMember(String memberName, int id, int memberId);
    //delete
    void deleteMember(int id);
    void deleteAllMembers();

}
