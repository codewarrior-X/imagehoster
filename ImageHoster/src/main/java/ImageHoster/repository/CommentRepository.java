package ImageHoster.repository;

import ImageHoster.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class CommentRepository {


    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    //The method receives the image id to get the comments from comments table
    //Creates an instance of EntityManager
    //Starts a transaction
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction

    public List<Comment> getAllComments(Integer image_id) {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Comment> query = em.createQuery("SELECT c from Comment c where c.image.id=:image_id", Comment.class).setParameter("image_id", image_id);
            List<Comment> resultList = query.getResultList();
            return resultList;
        } catch (NoResultException nre) {
            return null;
        }
    }

    //The method receives the Image object to be persisted in the database
    //Creates an instance of EntityManager
    //Starts a transaction
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction
    public Comment updateComment(Comment newComment){

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(newComment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return newComment;
    }



}
