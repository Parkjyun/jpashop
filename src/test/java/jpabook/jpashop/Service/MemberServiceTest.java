package jpabook.jpashop.Service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)//junit실행시 스프링과 엮어서 실행할래 !
@SpringBootTest//springboot를 띄운 상태로 테스트를 할래
@Transactional//testcase에 있으면 롤백을 해버린다
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;
    @Test

    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        // when
        Long saveId = memberService.join(member);

        // then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    /*@Test//(expected = IllegalStateException.class)//만약 illegalstateexception.class가 터지면 테스트 성공
    public void 중복회원예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);
        memberService.join(member2);//예외가 발생해야 한다

        // then
        //fail("예외가 발생해야 한다");//코드가 여기까지 오면 안된다. 만약 온다면 fail이뜸
    }
    */


}