package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        Teams teams = new Teams("TeamFusion");
        assertEquals(true, teams instanceof Teams);
    }

    @Test
    public void newTeamsInstantiatesWithTeamName_true() throws Exception {
        Teams teams = new Teams("TeamFusion");
        assertEquals("TeamFusion", teams.getTeamName());
    }

    @Test
    public void allTeamsAreCorrectlyReturned_true(){
        Teams teams = new Teams("TeamFusion");
        Teams nextTeams = new Teams("Dragon Fever");
        assertEquals(2,Teams.getAll().size());
    }

    @Test
    public void allTeamsContainsAllTeams_true() {
        Teams teams = new Teams("TeamFusion");
        Teams nextTeams = new Teams("Dragon Fever");
        assertTrue(Teams.getAll().contains(teams));
        assertTrue(Teams.getAll().contains(nextTeams));
    }
}