package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 회원 리포지토리 테스트
 */
class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance(); // 싱글톤 객체를 불러옴

    @AfterEach
    void afterEach() {
        memberRepository.clearStore(); // 테스트 실행 후 리포지토리를 비워줌
    }

    @Test
    void save() {
        //given
        Member member = new Member("hello", 20);

        //when
        Member savemember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(savemember.getId());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }
}
