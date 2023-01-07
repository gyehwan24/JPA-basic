package hellojpa;

import hellojpa.domain.Address;
import hellojpa.domain.Item.Album;
import hellojpa.domain.Item.Movie;
import hellojpa.domain.Member;
import hellojpa.domain.Period;
import hellojpa.domain.Team;
import org.h2.command.ddl.AlterUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setName("member1");
            member.setHomeAddress(new Address("homeCity", "street", "zipcode"));
            member.setWorkPeriod(new Period());

            em.persist(member);

            Team team = new Team();
            team.setName("team1");
            em.persist(team);
            team.addMember(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember.name = " + findMember.getName());
            System.out.println("findMember.getTeam().getName() = " + findMember.getTeam().getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally{
            em.close();
        }

        emf.close();
    }
}
