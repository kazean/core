package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MemberServiceTest {
    MemberService memberService;

    @Test
    void join(){
//        given
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA",Grade.VIP);
        memberService.join(member);

//        when
        Member findMember = memberService.findMember(memberId);

//        then
        assertThat(member).isEqualTo(findMember);
    }

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }
}
