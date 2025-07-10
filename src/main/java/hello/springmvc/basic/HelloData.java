package hello.springmvc.basic;

import lombok.Data;

/*
@Data: @Getter, @Setter, @EqualsAndHashCode, @RequiredArgsConstructor 를 자동으로 적용해줌
주의: @Data 에는 불필요한 메서드까지 오버라이드 하므로 사용에 필요없는 자원을 사용하게 된다 그러므로 사용을 자제하는게 좋다
 */
@Data
public class HelloData {

    private String username;
    private int age;
}
