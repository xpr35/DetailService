package com.detailservice.dao;

import com.detailservice.model.Cell;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.List;

public class CellDao {
    private static final EntityManager em = Persistence.createEntityManagerFactory("LEO_TEST").createEntityManager();

    public List<Cell> getUsers(Long cellId) throws PersistenceException {
        return em.createQuery("FROM Cell WHERE cell_id = :cellId", Cell.class).setParameter("cellId", cellId).getResultList();
    }
}
