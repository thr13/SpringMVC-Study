package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 10000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) { // VIP 인 경우에만 할인
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
