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
}