package dao;

import models.Members;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;



import static org.junit.Assert.*;

public class Sql2oMembersDaoTest {
    private Sql2oMembersDao memberDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        memberDao = new Sql2oMembersDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    //helper
    public Members setUpNewMember(){
        return new Members("Ducky",1);
    }
    public Members setUpNewMember2(){
        return new Members("Oreo",2);
    }


    @Test
    public void addingMemberSetsId() throws Exception {
        Members members = setUpNewMember();
        int originalMemberId = members.getMemberid();
        memberDao.addMember(members);
        assertNotEquals(originalMemberId,members.getMemberid());
    }

    @Test
    public void existingMembersCanBeFoundById() throws Exception {
        Members members = setUpNewMember();
        memberDao.addMember(members);
        Members foundMember = memberDao.locatememberbyid(members.getMemberid());
        assertEquals(members, foundMember);
    }

    @Test
    public void addMembersAreReturnedFromGetAll() {
        Members member = setUpNewMember();
        memberDao.addMember(member);
        assertEquals(1, memberDao.getAllmembers().size());
    }

    @Test
    public void noMembersReturnsEmptyList() throws Exception {
        assertEquals(0, memberDao.getAllmembers().size());
    }

    @Test
    public void updateChangesMemberContent() throws Exception {
        Members member = setUpNewMember();
        memberDao.addMember(member);
        memberDao.updatemember("Tanner", 1,member.getMemberid());
        Members updatedMember = memberDao.locatememberbyid(member.getMemberid());
        assertNotEquals(member, updatedMember.getMembername());
    }

    @Test
    public void deleteById_DeletesCorrectMember_0() throws Exception {
        Members member = setUpNewMember();
        memberDao.addMember(member);
        memberDao.deletemember(member.getMemberid());
        assertEquals(0, memberDao.getAllmembers().size());
    }

    @Test
    public void deleteAllMembers_returns_0() {
        Members member = setUpNewMember();
        Members otherMember = setUpNewMember2();
        memberDao.addMember(member);
        memberDao.addMember(otherMember);
        int rosterSize = memberDao.getAllmembers().size();
        memberDao.deleteallmembers();
        assertTrue(rosterSize>0 && rosterSize> memberDao.getAllmembers().size());
    }

    @Test
    public void TeamIdIsReturnedCorrectly() throws Exception {
        Members member = setUpNewMember();
        int originalTeamId = member.getTeamid();
        memberDao.addMember(member);
        assertEquals(originalTeamId, memberDao.locatememberbyid(member.getMemberid()).getTeamid());
    }


}