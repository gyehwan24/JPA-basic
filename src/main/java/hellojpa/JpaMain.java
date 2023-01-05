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

            Team teamA = new Team();
            teamA.setName("TeamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("TeamB");
            em.persist(teamB);

            Address addressA = new Address("city", "street", "zipcode");

            Member memberA = new Member();
            memberA.setName("memberA");
            memberA.setHomeAddress(addressA);
            memberA.setWorkPeriod(new Period());

            em.persist(memberA);
            teamA.addMember(memberA);

            Address addressB = new Address(addressA.getCity(), "streetB", "zipcodeB");

            Member memberB = new Member();
            memberB.setName("memberA");
            memberB.setHomeAddress(addressB);
            em.persist(memberB);
            teamB.addMember(memberB);

            em.flush();
            em.clear();

            Member findMemberA = em.find(Member.class, memberA.getId());
            System.out.println("findMemberA = " + findMemberA.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally{
            em.close();
        }

        emf.close();
    }
}
