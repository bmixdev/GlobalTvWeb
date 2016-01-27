package atua.anddev.globaltv.dao.impl;

import atua.anddev.globaltv.dao.UserDAO;
import atua.anddev.globaltv.entity.User;
import lombok.NoArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@NoArgsConstructor
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public User getUserById(int id) {
        return sessionFactory.getCurrentSession().load(User.class, id);
    }

    public User getUserByEmail(String email) {
        return sessionFactory.getCurrentSession().load(User.class, email);
    }

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }
}
