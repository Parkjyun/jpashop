package jpabook.jpashop.Service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //회원조회==읽기 -> 성능 최적화를 위해 -> 이클래스의 publicmethod에 작용
@RequiredArgsConstructor//롬복의 어노테이션으로 이 클래스의 final이 붙은 필드의 생성자를 만들어줌, autowired도 자동으로 되는 일
public class MemberService {


    private final MemberRepository memberRepository;



    //회원가입
    @Transactional//얘는 save 고로 읽기가 아니라 쓰기 -> transactional을 따로 적어줌 -> 이메서드만 그냥 trans따로 적용
    public Long join(Member member) {
        validateDuplicateMember(member);//중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //중복 회원이어서 문제가 있다면 여기서 예외를 터트릴꺼임
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {//findmembers가 비지 않았다면
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }
    //회원 전체 조회

    public  List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
