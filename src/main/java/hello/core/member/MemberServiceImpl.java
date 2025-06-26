package hello.core.member;

public class MemberServiceImpl implements MemberService {

    /*
    할당되는 부분이 구현체를 의존함 -> MemberServiceImpl 이 MemberRepository(추상화) 와 MemoryMemberRepository(구현체) 를 둘 다 의존하는 문제 발생 -> DIP 위반
     */
    private final MemberRepository memberRepository = new MemoryMemberRepository();


    @Override
    public void join(Member member) {
        memberRepository.save(member); // 다형성에 의해서 MemoryMemberRepository 에 있는 메소드 save 가 실행됨
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
