package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberRepository memberRepository = new MemoryMemberRepository();
        OrderService orderService = new OrderServiceImpl();

//        given
        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberRepository.save(member);

//        when
        Order order = orderService.createOrder(memberId, "itemA", 10000);

//        then
        System.out.println("order = " + order);
    }
}
