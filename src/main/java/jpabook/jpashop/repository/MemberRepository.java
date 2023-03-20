package jpabook.jpashop.repository;


import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //자동으로 스프링 빈에서 관리
public class MemberRepository {
    @PersistenceContext //jpa의 엔티티매니저를 스프링이 만든 엔티티매니저에 주입을 알아서 해줌
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){ //from 대상이 테이블이 아니고 엔티티이다
        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
        return result;
    }

    public List<Member> findByName(String name){
        List<Member> find = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return find;
    }
}
