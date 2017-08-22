package models;


import org.junit.Test;

import java.lang.reflect.Member;

import static org.junit.Assert.*;

public class MembersTest {

    public Members setupNewMember(){
        return new Members (1, "Ducky");
    }
    @Test
    public void newMember_instantiatesCorrectly() throws Exception {
        Members testMembers = setupNewMember();
        assertEquals(true, testMembers instanceof Members);
    }

    @Test
    public void newMember_getNewMemberName() throws Exception {
        Members testMembers = setupNewMember();
        assertEquals("Ducky", testMembers.getMemberName());
    }

    @Test
    public void newMember_getMemberTeamId() throws Exception {
        Members testMembers = setupNewMember();
        assertEquals(1, testMembers.getMemberTeamId());
    }
    @Test
    public void allMembersAreCorrectlyReturned_true(){
        Members testMembers = setupNewMember();
        Members nextMembers = new Members(1,"Oreo");
        assertEquals(2,Members.getAll().size());
    }

    @Test
    public void getNewMemberRosterIdNumber() throws Exception {
        Members testMembers = setupNewMember();
        assertEquals(1, testMembers.getMemberId());

    }
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