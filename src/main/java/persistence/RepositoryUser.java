package persistence;

import java.util.List;
import javax.persistence.EntityManager;
import model.Customer;
import model.User;
import util.DBUtil;

public class RepositoryUser {

    private EntityManager em;

    public RepositoryUser() {
        em = DBUtil.getEntityManager();
    }

    @SuppressWarnings("unchecked")
    public List<Customer> listAllCustomers() {
        return em.createQuery("Select f from User as f order by f.firstName asc")
                .getResultList();
    }
    public void saveUser(User user){
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            System.out.println("User saved");
        } catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
    public void updatePhoneNumber(int userId, String phoneNumber){
        try {
            String sql = "UPDATE User u SET u.mobile = :phoneNumber WHERE u.userId = :userId";
            em.getTransaction().begin();
            em. createQuery(sql, User.class)
                    .setParameter("phoneNumber", phoneNumber)
                    .setParameter("userId", userId)
                    .executeUpdate();
            em.getTransaction().commit();
            System.out.println("Phone number successfully updated.");
        } catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
    public void updateEmail(int userId, String email){
        try{
            String sql = "UPDATE user u SET u.email = :email WHERE u.userId = :userId";
            em.getTransaction().begin();
            em.createQuery(sql, User.class)
                    .setParameter("userId", userId)
                    .setParameter("email", email)
                    .executeUpdate();
            em.getTransaction().commit();
            System.out.println("Email successfully updated.");
        } catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
    public User findUserById(int userId){
        String sql = "FROM User WHERE userId = :userId";
        return em.createQuery(sql, User.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }
    public long listTotalUsers(){
        String sql = "SELECT count(u) FROM User u";
        return (long) em.createQuery(sql)
                .getSingleResult();
    }
    public long listActiveCustomers(){
        String sql = "SELECT c.accountStatus, count(accountStatus) FROM Customer c group by c.accountStatus";
        return (long) em.createQuery(sql)
                .getSingleResult();
    }
}
