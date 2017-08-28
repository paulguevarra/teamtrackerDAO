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
        int originalMemberId = members.getMemberId();
        memberDao.addMember(members);
        assertNotEquals(originalMemberId,members.getMemberId());
    }

    @Test
    public void existingMembersCanBeFoundById() throws Exception {
        Members members = setUpNewMember();
        memberDao.addMember(members);
        Members foundMember = memberDao.locateMemberById(members.getMemberId());
        assertEquals(members, foundMember);
    }

    @Test
    public void addedMembersAreReturnedFromgetAll() {
        Members member = setUpNewMember();
        Members otherMember = setUpNewMember2();
        memberDao.addMember(member);
        memberDao.addMember(otherMember);
        assertEquals(2, memberDao.getAllMembers().size());
    }

    @Test
    public void noMembersReturnsEmptyList() throws Exception {
        assertEquals(0, memberDao.getAllMembers().size());
    }

    @Test
    public void updateChangesMemberContent() throws Exception {
        Members member = setUpNewMember();
        memberDao.addMember(member);
        memberDao.updateMember("Tanner", 2,member.getMemberId());
        Members updatedMember = memberDao.locateMemberById(member.getMemberId());
        assertNotEquals(member, updatedMember);
    }

    @Test
    public void deleteById_DeletesCorrectMember_0() throws Exception {
        Members member = setUpNewMember();
        memberDao.addMember(member);
        memberDao.deleteMember(member.getMemberId());
        assertEquals(0, memberDao.getAllMembers().size());
    }

    @Test
    public void deleteAllMembers_returns_0() {
        Members member = setUpNewMember();
        Members otherMember = setUpNewMember2();
        memberDao.addMember(member);
        memberDao.addMember(otherMember);
        memberDao.deleteAllMembers();
        assertEquals(0, memberDao.getAllMembers().size());
    }

    @Test
    public void categoryIdIsReturnedCorrectly() throws Exception {
        Members member = setUpNewMember();
        int originalTeamId = member.getTeamId();
        memberDao.addMember(member);
        assertEquals(originalTeamId, memberDao.locateMemberById(member.getMemberId()).getTeamId());
    }


}