package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SingleTonTest {
    @Test
    @DisplayName("스프링이 없는 순수한 DI 컨테이너")
    public void pureContainer() throws Exception{
        //given
        AppConfig appConfig = new AppConfig();
        //when
        MemberService memberService1 = appConfig.memberService();;
        MemberService memberService2 = appConfig.memberService();;

        //then
        assertThat(memberService1).isNotSameAs(memberService2);
    }
}
