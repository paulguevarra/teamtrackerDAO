//package models;
//
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.lang.reflect.Member;
//import java.util.ArrayList;
//
//import static org.junit.Assert.*;
//
//public class MembersTest {
//    @Test
//    public void addAnotherMemberToRoster_IdentifyByName() {
//        Members newMember = setupNewMember();
//        assertEquals("Oreo", newMember.getMemberName());
//    }
//
////    @Test
////    public void addAnotherMemberToRoster_IdentifyByCount() throws Exception {
////        Members newMember = setupNewMember();
////        Members newMemberTwo = setupNewMemberTwo();
////        assertEquals(2, Members.getAllMembers().size());
////    }
//
//    @Test
//    public void getIdOfTeamMemberBelongsTo() throws Exception {
//        Members testMembers = setupNewMember();
//        Members anotherMember = setupNewMemberTwo();
//        assertEquals(1,testMembers.getTeamId());
//    }
//
//
//    //Helper
//    public Members setupNewMember() {
//        return new Members("Oreo", 1);
//    }
//
//    public Members setupNewMemberTwo() {
//        return new Members("Tanner", 2);
//    }
//
//}
////    @Test
////    public void newMember_instantiatesCorrectly() throws Exception {
////        Members testMembers = setupNewMember();
////        assertEquals(true, testMembers instanceof Members);
////    }
//
////   @Test
////    public void allMembersAreCorrectlyReturned_true(){
////        Members testMembers = setupNewMember();
////        Members nextMembers = new Members(1,"Oreo");
////        assertEquals(2,Members.getAll().size());
////    }
////
////    @Test
////    public void getNewMemberRosterIdNumber() throws Exception {
////        Members testMembers = setupNewMember();
////        assertEquals(1, testMembers.getMemberId());
////
////    }
//
////    @Test
////    public void getNameOfTeamMemberBelongs() throws Exception {
////        Members testMembers = setupNewMember();
////        assertEquals("Team Fusion", testMembers.getMemberTeam());
////    }
//    //
//
