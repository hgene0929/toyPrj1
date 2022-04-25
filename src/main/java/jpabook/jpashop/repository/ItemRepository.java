package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Item item) {
        if (item.getId() == null) {
            em.persist(item); //처음으로 객체가 생성되는 경우 CREATE
        } else {
            em.merge(item); //기존에 해당 객체가 존재하는 경우 UPDATE
        }
        return item.getId();
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item", Item.class)
                .getResultList();
    }
}
