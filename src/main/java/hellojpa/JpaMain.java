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
            teamA.setName("teamA");
            em.persist(teamA);

//            for (int i = 0; i < 10; i++) {
//                Member member = new Member();
//                member.setName("member"+i);
//                member.setAge(i);
//                em.persist(member);
//                teamA.addMember(member);
//            }

            Member member = new Member();
            member.setName("teamA");
            em.persist(member);
            teamA.addMember(member);

            Member member1 = new Member();
            member1.setName("member1");
            em.persist(member1);

            em.flush();
            em.clear();

//            //페이징
//            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member = " + member);
//            }
//
//            //조인
//            List<Team> resultList = em.createQuery("select t from Member m join m.team t", Team.class)
//                    .getResultList();

            List<Member> joinResult = em.createQuery("select m from Member m left join m.team t on m.team.id = t.id", Member.class)
                    .getResultList();

            System.out.println("joinResult.size() = " + joinResult.size());


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally{
            em.close();
        }

        emf.close();
    }
}
