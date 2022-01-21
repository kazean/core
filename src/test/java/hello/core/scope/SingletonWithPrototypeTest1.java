package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {
    @Test
    public void prototypeFind() throws Exception{
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);
        //when
        ProtoTypeBean protoTypeBean1 = ac.getBean(ProtoTypeBean.class);
        ProtoTypeBean protoTypeBean2 = ac.getBean(ProtoTypeBean.class);
        protoTypeBean1.addCount();
        protoTypeBean2.addCount();
        int count1 = protoTypeBean1.getCount();
        int count2 = protoTypeBean2.getCount();

        //then
        assertThat(count1).isEqualTo(1);
        assertThat(count2).isEqualTo(1);
        ac.close();
    }
    
    @Test
    public void singletonClientUsePrototype() throws Exception{
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, ProtoTypeBean.class);
        //when
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int logic1 = clientBean1.logic();
        int logic2 = clientBean2.logic();

        //then
        assertThat(logic1).isEqualTo(1);
        assertThat(logic2).isEqualTo(2);
        ac.close();
    }

    static class ClientBean{
        private final ProtoTypeBean protoTypeBean;

        @Autowired
        public ClientBean(ProtoTypeBean prototypeBean) {
            protoTypeBean = prototypeBean;
        }


        public int logic(){
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
