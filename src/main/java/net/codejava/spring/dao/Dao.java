package net.codejava.spring.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao <T, PK extends Serializable>{
    public void adiciona(final T t);
    public void remove(final T t);
    public T busca(final PK id);
    public List<T> lista();
    public void atualiza(final T t);
}
