package models;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Member;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MembersTest {
    @Test
    public void addAnotherMemberToRoster_IdentifyByName(){
        Members newMember = setupNewMember();
        assertEquals("Oreo", newMember.getMemberName());
    }

    @Test
    public void addAnotherMemberToRoster_IdentifyByCount() throws Exception {
        Members newMember = setupNewMember();
        Members newMemberTwo = setupNewMemberTwo();
        assertEquals(2,Members.getAllMembers().size());
    }

    //Helper
    public Members setupNewMember(){
        Members newMember = new Members();
        newMember.setAnotherMember("Oreo");
        return newMember;
    }
    public Members setupNewMemberTwo(){
        Members newMember = new Members();
        newMember.setAnotherMember("Tanner");
        return newMember;
    }


//    @Test
//    public void newMember_instantiatesCorrectly() throws Exception {
//        Members testMembers = setupNewMember();
//        assertEquals(true, testMembers instanceof Members);
//    }

//    @Test
//    public void newMember_getNewMemberName() throws Exception {
//        Members testMembers = setupNewMember();
//        assertEquals("Ducky", testMembers.getMemberName());
//    }

//    @Test
//    public void getIdOfTeamMemberBelongs() throws Exception {
//        Members testMembers = setupNewMember();
//        assertEquals(1,testMembers.getMemberTeamId());
//    }
//   @Test
//    public void allMembersAreCorrectlyReturned_true(){
//        Members testMembers = setupNewMember();
//        Members nextMembers = new Members(1,"Oreo");
//        assertEquals(2,Members.getAll().size());
//    }
//
//    @Test
//    public void getNewMemberRosterIdNumber() throws Exception {
//        Members testMembers = setupNewMember();
//        assertEquals(1, testMembers.getMemberId());
//
//    }

//    @Test
//    public void getNameOfTeamMemberBelongs() throws Exception {
//        Members testMembers = setupNewMember();
//        assertEquals("Team Fusion", testMembers.getMemberTeam());
//    }
    //
//    @Test
//    public void findAllMembersWithSameTeamId() throws Exception {
//        Members testMembers = setupNewMember();
//        Members nextMembers = new Members(1,"Oreo");
//        Members otherMembers = new Members(2,"Tanner");
//        Integer expected = 2;
//        assertEquals(true,Members.getTeamRoster().size());
//    }
}