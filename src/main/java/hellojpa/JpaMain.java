package hellojpa;

import hellojpa.domain.Member;
import hellojpa.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("memberA");
            member.setTeam(team);
            em.persist(member);

            Member member2 = new Member();
            member2.setName("memberB");
            member2.setTeam(team);
            em.persist(member2);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());

            Team findTeam = findMember.getTeam();
            System.out.println("findTeam.name = " + findTeam.getName());

            Team newTeam = new Team();
            newTeam.setName("TeamB");
            em.persist(newTeam);

            List<Member> members = findMember.getTeam().getMembers();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally{
            em.close();
        }

        emf.close();
    }
}
