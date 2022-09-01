package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository//componentscan에 의해 스프링 빈에 자동으로 등록됨
@RequiredArgsConstructor//->repository의 entitymanager을 생성자로 인젝션한 것과 동일
public class MemberRepository {


    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);//아마 id를 이용해서 member 자료형을 찾는단 이야기겠지?
    }

    public List<Member> findAll() {
        //첫번째 매개변수는 jpql 두번째 매개변수는 반환타입
       return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
