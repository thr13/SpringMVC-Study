package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 회원 리포지토리
 * 해당 코드는 동시성 문제가 고려되어 있지 않았음, 실무에서는 ConcurrentHashMap, AtomicLong 등을 사용 권고
 */
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository(); // 싱글톤

    public static MemberRepository getInstance() { // 해당 메소드를 통해서 싱글톤 인스턴스에 접근가능
        return instance;
    }

    private MemberRepository() {} // 싱글톤 사용시 생성자를 private 로 선언해서 외부 접근을 막아야 한다

    // 리포지토리 기능들
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
