package dao;

import models.Members;

import java.lang.reflect.Member;
import java.util.List;

public interface MemberDao {
    //create
    void addMember (Members members);

    //read
    List<Members> getAllmembers();
    Members locatememberbyid(int id);
    //update
    void updatemember(String membername, int teamid, int memberid);
    //delete
    void deletemember(int id);
    void deleteallmembers();

}
