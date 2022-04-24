package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    //@Autowired 어노테이션을 생략해도 생성자가 하나면 생성자 injection
    //이때 MemberRepository 생성시 final 적용
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원등록
    @Transactional(readOnly = false) //삽입시 Transactional 옵션 readonly = false
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증 로직
        memberRepository.save(member);
        return member.getId(); //반환값이 있어야 저장 대상이 무엇인지를 알 수 있음
    }

    private void validateDuplicateMember(Member member) {
        //Exception : 문제가 있을 경우
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 조회
    //조회시 Transactional 옵션 readonly = true
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findMember(Long id) {
        return memberRepository.findOne(id);
    }
}
