package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @Test
    public void order() throws Exception{
//        given
        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);
//        memberRepository.save(member);
        memberService.join(member);

//        when
        Order order = orderService.createOrder(memberId, "itemA", 10000);

//        then
        assertThat(order.calculatePrice()).isEqualTo(9000);
    }

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }
}
