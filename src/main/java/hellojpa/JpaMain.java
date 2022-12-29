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

            em.flush();
            em.clear();

            Movie findMovie = em.find(Movie.class, movie.getId());
            Album findAlbum = em.find(Album.class, album.getId());
            System.out.println("==================");
            System.out.println("findMovie = " + findMovie.getName());
            System.out.println("findAlbum.getName() = " + findAlbum.getName());
            System.out.println("==================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally{
            em.close();
        }

        emf.close();
    }
}
