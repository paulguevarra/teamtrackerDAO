package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TeamsTest {

    @Before
    public void setUp() throws Exception {
    }
    @After
    public void tearDown() throws Exception {
        Teams.clearAllTeams();
    }
    @Test
    public void newTeamsObjectGetsCreatedCorrectly_true() throws Exception {
        Teams teams = setupNewTeam();
        assertEquals(true, teams instanceof Teams);
    }

    @Test
    public void newTeamsInstantiatesWithTeamName_true() throws Exception {
        Teams teams = setupNewTeam();
        assertEquals("TeamFusion", teams.getTeamName());
    }

    @Test
    public void allTeamsAreCorrectlyReturned_true(){
        Teams teams = setupNewTeam();
        Teams nextTeams = new Teams("Dragon Fever", "Fitness is Goal");
        assertEquals(2,Teams.getAll().size());
    }

    @Test
    public void allTeamsContainsAllTeams_true() {
        Teams teams = setupNewTeam();
        Teams nextTeams = new Teams("Dragon Fever", "Fitness is Goal");
        assertTrue(Teams.getAll().contains(teams));
        assertTrue(Teams.getAll().contains(nextTeams));
    }

    @Test
    public void getID_teamsInstantiateWithID_1() throws Exception {
        Teams.clearAllTeams();
        Teams teams = setupNewTeam();
        assertEquals(1,teams.getId());
    }
    @Test
    public void locateByIdReturnsCorrectTeam() throws Exception {
        Teams teams = setupNewTeam();
        assertEquals(1, Teams.locateById(teams.getId()).getId());
    }

    @Test
    public void findReturnsCorrectTeamWhenMoreThanOneTeamExists() throws Exception {
        Teams teams = setupNewTeam();
        Teams nextTeam = new Teams("Dragon Fever", "Fitness is Goal");
        assertEquals(2, Teams.locateById(nextTeam.getId()).getId());
    }


    @Test
    public void updateChangesTeamContent() throws Exception {
    Teams teams = setupNewTeam();
    String formerTeamName = teams.getTeamName();
    int formerId = teams.getId();
    teams.update("Bridge City Paddlers", "Locally grown fanatics");
    assertEquals(formerId, teams.getId());
    assertNotEquals(formerTeamName, teams.getTeamName());
    }

    @Test
    public void addNewMembersToTeamRoster() throws Exception {
        Teams teams = setupNewTeam();
        ArrayList<String> roster = new ArrayList<>();
        roster.add("ducky");
        assertEquals(roster, teams.addToRoster("ducky"));
    }

    @Test
    public void getAllMembersFromRoster() throws Exception {
        Teams teams = setupNewTeam();
        ArrayList<String>roster = new ArrayList<>();
        assertEquals(roster, teams.getRoster());
    }

    @Test
    public void getTeamDescription() throws Exception {
        Teams teams = setupNewTeam();
        assertEquals("All are welcome", teams.getDescription());
    }

    //helper
    public Teams setupNewTeam(){
        return new Teams ("TeamFusion", "All are welcome");
    }

}