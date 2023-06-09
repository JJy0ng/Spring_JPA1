package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public void save(Item item){
        if(item.getId() == null){
            em.persist(item); // 처음에는 db에 값이 없다
        } else {
            em.merge(item); // 업데이트와 비슷한 개념
        }
    }

    public Item finOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return  em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
