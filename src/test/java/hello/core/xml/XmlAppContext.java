package hello.core.xml;

import hello.core.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class XmlAppContext {

    @Test
    public void xmlAppConfig() throws Exception{
        //given
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

        //when
        MemberRepository memberRepository = ac.getBean(MemberRepository.class);

        //then
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }
}
