package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext //엔티티 매니저 주입
    EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId(); //커맨드와 쿼리를 분리하라
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
