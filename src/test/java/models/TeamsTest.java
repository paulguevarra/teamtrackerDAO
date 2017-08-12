package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamsTest {
    @Before
    public void setUp() throws Exception {
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
    @After
    public void tearDown() throws Exception {
        Teams.clearAllTeams();
    }
    @Test
    public void allTeamsAreCorrectlyReturned_true(){
        Teams teams = setupNewTeam();
        Teams nextTeams = new Teams("Dragon Fever");
        assertEquals(2,Teams.getAll().size());
    }

    @Test
    public void allTeamsContainsAllTeams_true() {
        Teams teams = setupNewTeam();
        Teams nextTeams = new Teams("Dragon Fever");
        assertTrue(Teams.getAll().contains(teams));
        assertTrue(Teams.getAll().contains(nextTeams));
    }

    @Test
    public void getID_postsInstantiateWithID_1() throws Exception {
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
    public void findReturnsCorrectPostWhenMoreThanOnePostExists() throws Exception {
        Teams teams = setupNewTeam();
        Teams nextTeam = new Teams("Dragon Fever");
        assertEquals(2, Teams.locateById(nextTeam.getId()).getId());
    }
    public Teams setupNewTeam(){
        return new Teams ("TeamFusion");
    }

}