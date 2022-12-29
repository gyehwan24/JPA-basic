package hellojpa;

import hellojpa.domain.Item.Album;
import hellojpa.domain.Item.Movie;
import hellojpa.domain.Member;
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

            Movie movie = new Movie();
            movie.setName("아바타");
            movie.setActor("계환");
            movie.setDirector("놀란");
            movie.setPrice(14000);

            em.persist(movie);

            Album album = new Album();
            album.setName("23");
            album.setPrice(1200);
            album.setArtist("콜드");

            em.persist(album);

            Member member = new Member();
            member.setName("다은");
            member.setCity("광주");
            member.setLastModifiedBy("계환");
            member.setCreatedDate(LocalDateTime.now());
            em.persist(member);

            em.flush();
            em.clear();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally{
            em.close();
        }

        emf.close();
    }
}
