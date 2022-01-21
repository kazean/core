package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
    public void findBeanByParentDuplicate() throws Exception{
        //given
        //when
//        ac.getBean(DiscountPolicy.class);

        //then
        assertThrows(NoUniqueBeanDefinitionException.class, ()->ac.getBean(DiscountPolicy.class));
    }
    
    @Test
    @DisplayName("부모 타입 조회시, 자식 둘 이상일 경우, 빈 이름을 지정하면된다.")
    public void findBeanByParentTypeBeanNames() throws Exception{
        //given
        //when
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);

        //then
        assertThat(rateDiscountPolicy).isInstanceOf(DiscountPolicy.class);
    }
    
    @Test
    @DisplayName("특정 하위 타입으로 조회")
    public void findBeanBySubtype() throws Exception{
        //given
        //when
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);

        //then
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
//        assertThat(bean).isInstanceOf(DiscountPolicy.class);
    }
    
    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    public void findAllBeanByParentType() throws Exception{
        //given
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        //when
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + ", value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);

        //then
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    public void findAllBeanByObject() throws Exception{
        //given
        //when
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);

        //then
        for (String key : beansOfType.keySet()) {
            /*
//            [X] getBeansOfType > getDefinitionNames와 가져오는 것이 다르다

            BeanDefinition beanDefinition = ac.getBeanDefinition(key);
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("key = " + key + ", value = " + beansOfType.get(key));
            }
            */
            System.out.println("key = " + key + ", value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
    }

    @Configuration
    static class TestConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }
        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}
