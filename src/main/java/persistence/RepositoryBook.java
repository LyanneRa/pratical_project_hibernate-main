package persistence;

import model.Book;
import model.BookLoan;
import model.Category;
import util.DBUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class RepositoryBook {
    private EntityManager em;

    public RepositoryBook(){
        em = DBUtil.getEntityManager();
    }
    public Book findBookById(int bookId){
        return em.find(Book.class, bookId);
    }
    public List<Book> listAllBooks(){
        String sql = "Select b from Book as b order by b.name asc";
        return em.createQuery(sql, Book.class)
                .getResultList();
    }
    public List<Book> listBooksByAuthorId(int authorId) {
        return em.createQuery("FROM Book WHERE authorId = :authorId", Book.class)
                .setParameter("authorId", authorId)
                .getResultList();
    }
    public List<Book> listBooksByCategory(String category){
        return em
                .createQuery("FROM Book WHERE categoryId.name = :category", Book.class)
                .setParameter("category", category)
                .getResultList();
    }
    public List<BookLoan> listBookLoans (){
        String sql = "SELECT b FROM BookLoan as b order by b.bookLoanId asc";
        return em.createQuery(sql, BookLoan.class)
                .getResultList();
    }
    public void addABook(Book book){
        try {
           em.getTransaction().begin();
           em.persist(book);
           em.getTransaction().commit();
            System.out.println("Book saved");
        } catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
    public void addACategory(Category category){
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
            System.out.println("Category saved");
        } catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
    public Category findCategoryByName(String name){
        return em.find(Category.class, name);
    }
    public List<BookLoan> listOfBookLoans(){
        return null;
    }
}
