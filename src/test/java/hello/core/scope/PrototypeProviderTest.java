package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class PrototypeProviderTest {

    @Test
    public void providerTest() throws Exception{
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,ProtoTypeBean.class);
        //when
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int logic1 = clientBean1.logic();
        int logic2 = clientBean2.logic();

        //then
        assertThat(logic1).isEqualTo(1);
        assertThat(logic2).isEqualTo(1);

    }

    @RequiredArgsConstructor
    static class ClientBean{
        /*
        @Autowired
        private ApplicationContext ac;

        public int logic(){
            ProtoTypeBean protoTypeBean = ac.getBean(ProtoTypeBean.class);
            protoTypeBean.addCount();
            return protoTypeBean.getCount();
        }
        */

//        private final ObjectProvider<ProtoTypeBean> objectProvider;
        private final Provider<ProtoTypeBean> provider;

        public int logic(){
//            ProtoTypeBean protoTypeBean = objectProvider.getObject();
            ProtoTypeBean protoTypeBean = provider.get();

            protoTypeBean.addCount();
            return protoTypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class ProtoTypeBean{
        int count;

        @PostConstruct
        public void init(){
            System.out.println("ProtoTypeBean.init");
        }

        @PreDestroy
        public void close(){
            System.out.println("ProtoTypeBean.close");
        }

        public void addCount(){
            count++;
        }

        public int getCount() {
            return count;
        }
    }
}