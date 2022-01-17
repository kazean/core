package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class OrderServiceTest {
    MemberRepository memberRepository = new MemoryMemberRepository();
    OrderService orderService = new OrderServiceImpl();

    @Test
    public void order() throws Exception{
//        given
        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberRepository.save(member);

//        when
        Order order = orderService.createOrder(memberId, "itemA", 10000);

//        then
        assertThat(order.calculatePrice()).isEqualTo(9000);
    }
}
