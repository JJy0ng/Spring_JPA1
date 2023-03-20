package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //DB에 읽기 전용 모드인 것을 알려줘서 리소스를 많이 쓰는 것을 막음, 쓰기에는 사용하면 안된다 -> 데이터 값이 저장이 안된다
@RequiredArgsConstructor //Lombok
public class MemberService {

    private final MemberRepository memberRepository;

    /*
    @Autowired //생성자에서 인젝션을 해준다 -> 좋은 방법, 하나 있을 때 @Autowird가 없어도 알아서 해준다
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */

    /**
     * 회원 가입
     */
    @Transactional //JPA의 모든 데이터 변경 같은 로직들은 Transactional 안에서 실행되어야 한다
    public Long join(Member member){
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { //실무에는 데이터베이스에 멤버에 있는 이름을 유니크 제약 조건을 잡아둠
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //단권 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
