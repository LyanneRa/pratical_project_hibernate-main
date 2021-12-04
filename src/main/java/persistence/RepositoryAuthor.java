package persistence;

import model.Author;
import util.DBUtil;

import javax.persistence.EntityManager;

public class RepositoryAuthor {
    private EntityManager em;

    public RepositoryAuthor(){
        em = DBUtil.getEntityManager();
    }
    public void addAuthor(Author author){
        try {
            em.getTransaction().begin();
            em.persist(author);
            em.getTransaction().commit();
            System.out.println("Author saved");
        } catch (Exception e){
            em.getTransaction().rollback();
        }
    }
    public Author findAuthorById(int authorId){
        String sql = "FROM Author WHERE authorId = :authorId";
        return em.createQuery(sql, Author.class)
                .setParameter("authorId", authorId)
                .getSingleResult();
    }
}
