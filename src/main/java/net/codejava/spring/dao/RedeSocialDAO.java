package net.codejava.spring.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.codejava.spring.model.RedeSocial;

@Repository
public class RedeSocialDAO implements Dao<RedeSocial, Integer>{
    private final GenericDAO<RedeSocial,Integer> dao;
    
    @Autowired
    public RedeSocialDAO(final SessionFactory factory) {
        dao  = new GenericDAO<RedeSocial,Integer>(factory, RedeSocial.class);
    }

	public void adiciona(RedeSocial t) {
		dao.adiciona(t);
	}

	public void remove(RedeSocial t) {
		dao.remove(t);	
	}

	public RedeSocial busca(Integer id) {
		return dao.busca(id);
	}

	public List<RedeSocial> lista() {
		return dao.lista();
	}

	public void atualiza(RedeSocial t) {
		dao.atualiza(t);
	}
}
