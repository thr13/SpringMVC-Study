package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 트랜잭션 - 파라미터 연동, 커넥션 풀을 고려한 종료
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {

    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        Connection con = dataSource.getConnection();

        try {
            con.setAutoCommit(false); // 수동 커밋 설정 - 트랜잭션 시작
            businessLogic(con, fromId, toId, money);
            con.commit(); // 수동 커밋 - 성공시 커밋 전송
        } catch (Exception e) {
            con.rollback(); // 실패시 롤백 (커밋을 전송하지 않음)
            throw new IllegalStateException(e);
        } finally {
            release(con);
        }

    }

    // 비즈니스 로직
    private void businessLogic(Connection con, String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(con, fromId);
        Member toMember = memberRepository.findById(con, toId);

        memberRepository.update(con, fromId, fromMember.getMoney() - money);
        validation(toMember);
        memberRepository.update(con, toId, toMember.getMoney() + money);
    }

    private void release(Connection con) {
        if (con != null) {
            try {
                con.setAutoCommit(true); // 커넥션 풀에 반납시 오토 커밋으로 변경
                con.close();
            } catch (Exception e) {
                log.info("error", e);
            }
        }
    }

    // 입금 처리과정에서 예외(ex)가 발생할 경우
    private static void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체중 예외 발생");
        }
    }
}
