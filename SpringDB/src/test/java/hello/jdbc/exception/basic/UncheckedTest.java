package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Slf4j
class UncheckedTest {

    @Test
    void unchecked_catch() {
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void unchecked_throw() {
        Service service = new Service();
        assertThatThrownBy(() -> service.callThrow())
                .isInstanceOf(MyUncheckedException.class);

    }

    /**
     * RuntimeException 을 상속받은 예외는 언체크 예외가 된다.
     */
    static class MyUncheckedException extends RuntimeException {
        public MyUncheckedException(String message) {
            super(message);
        }
    }

    /**
     * UnChecked 예외는 예외를 잡거나 던지지 않아도 된다!!
     * UnChecked 예외는 예외를 잡지 않은 경우, 자동으로 밖으로 던진다
     */
    static class Service {
        Repository repository = new Repository();

        /**
         * 필요한 경우 UnChecked 예외를 잡아서 처리하면 된다
         */
        public void callCatch() {
            try {
                repository.call();
            } catch (MyUncheckedException e) {
                // 예외 처리 로직
                log.info("예외 처리, message={}", e.getMessage(), e);
            }
        }

        /**
         * UnChecked 예외를 잡지 않아도 된다. 자연스럽게 상위로 넘어가기 때문이다
         * Checked 예외와 다르게 throws 예외를 명시적으로 선언하지 않아도 된다
         */
        public void callThrow() {
            repository.call();
        }
    }

    static class Repository {
        public void call() {
            throw new MyUncheckedException("ex");
        }
    }
}
