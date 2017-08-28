package dao;

import models.Members;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oMembersDao implements MemberDao{
    private final Sql2o sql2o;
    public Sql2oMembersDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void addMember(Members members) {
        String sql = "INSERT INTO members (name, teamid) VALUES (:name, :teamid)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .addParameter("name", members.getMemberName())
                    .addParameter("teamid", members.getTeamId())
                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("TEAMID", "teamid")
                    .executeUpdate()
                    .getKey();
            members.setMemberId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }

    @Override
    public List<Members> getAllMembers() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM members")
                    .executeAndFetch(Members.class);
        }
    }

    @Override
    public Members locateMemberById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM members WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Members.class);
        }
    }

    @Override
    public void updateMember(String newName, int newId, int memberId) {
        String sql = "UPDATE members SET (name, newId) = (:name, :id) WHERE memberId=:memberId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", newName)
                    .addParameter("id", newId)
                    .addParameter("memberId", memberId)
                    .executeUpdate();
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteMember(int number){
        String sql = "DELETE from members WHERE memberId=:memberId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("memberId", number)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteAllMembers() {
        String sql = "DELETE from members";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

}
