package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
    할당되는 부분이 구현체를 의존함 -> MemberServiceImpl 이 MemberRepository(추상화) 와 MemoryMemberRepository(구현체) 를 둘 다 의존하는 문제 발생 -> DIP 위반
    이를 해결하기 위해, 생성자를 이용해 외부에서 리파지토리 구현객체를 주입하도록 변경 -> DIP 만족
     */
@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; // 생성자를 통해서 구현체 주입
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member); // 다형성에 의해서 MemoryMemberRepository 에 있는 메소드 save 가 실행됨
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트용 - 스프링 컨테이너가 싱글톤으로 관리하는지 확인하는 코드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
