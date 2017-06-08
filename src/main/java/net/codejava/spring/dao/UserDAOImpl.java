package net.codejava.spring.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.spring.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

    private final GenericDAO<User,Integer> dao;
    
    @Autowired
    public UserDAOImpl(final SessionFactory factory) {
        dao  = new GenericDAO<User,Integer>(factory, User.class);
    }
    
    @Transactional
	public void adiciona(User t) {
		dao.adiciona(t);
	}

    @Transactional
	public void remove(User t) {
		dao.remove(t);	
	}

    @Transactional
	public User busca(Integer id) {
		return dao.busca(id);
	}

    @Transactional
	public List<User> lista() {
		return dao.lista();
	}

    @Transactional
	public void atualiza(User t) {
		dao.atualiza(t);
	}

    @Transactional
	public void remove(Integer id) {
		dao.remove(id);
	}

}