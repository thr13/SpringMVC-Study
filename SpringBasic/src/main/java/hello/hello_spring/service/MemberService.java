package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     *
     * @param member 회원 객체
     * @return 회원의 아이디
     */
    public Long join(Member member) {
        // 비즈니스 로직 요구사항: 같은 이름이 있는 중복 회원X + 메서드 호출 시간명시
        long start = System.currentTimeMillis();

        try {
            validateDuplicateMember(member); // 중복 회원 검증

            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }

    }

    private void validateDuplicateMember(Member member) { // 로직을 바로 구현가능한 경우에는 메서드로 뽑는게 좋다
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers() { // 서비스 계층의 메서드 이름은 비즈니스에 가까운 용어를 사용해야 한다
        long start = System.currentTimeMillis();

        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs + "ms");
        }

    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
