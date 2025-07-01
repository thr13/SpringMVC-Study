package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
    할인 정책의 클라이언트 OrderServiceImpl

    추상체뿐만 아니라 구현체에 의존하고 있기 때문에 '할인정책' 변경시 여기 코드도 수정해야한다 -> OCP, DIP 위반
    추상(인터페이스) 의존: DiscountPolicy
    구체(구현 클래스): FixDiscountPolicy, RateDiscountPolicy
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    해결방법:
    제3자가 클라이언트인 OrderServiceImpl 에 추상체인 DiscountPolicy 의 구현 객체를 대신 생성하고 주입해야함
*/

//@RequiredArgsConstructor //final 이 붙은 필드를 생성자를 만들어서 초기화해줌
@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 생성자가 하나일 경우, 생성자에 @Autowired 생략 가능, 주입할 대상이 없어도 동작하게 하려면 required = false 로 지정하면 된다
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인은 오직 discountPolicy 만 담당하므로 SRP 을 만족함

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트용 - 스프링 컨테이너가 싱글톤으로 관리하는지 확인하는 코드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
