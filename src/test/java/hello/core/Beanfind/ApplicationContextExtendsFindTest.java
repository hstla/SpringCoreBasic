package hello.core.Beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPlicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ApplicationContextExtendsFindTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

  @Test
  @DisplayName("부모 타입으로 조회, 자식이 둘 이상 있으면, 중복 오류 발생")
  void findBeanByPParentTypeOuplicate() {
    assertThrows(NoUniqueBeanDefinitionException.class,
        () -> ac.getBean(DiscountPolicy.class));
  }

  @Test
  @DisplayName("부모 타입으로 조회, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다")
  void findBeanByPParentTypeBeanName() {
    DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
    assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPlicy.class);
  }

  @Test
  @DisplayName("특정 하위 타입으로 조회")
  void findBeanBySubType () {
    RateDiscountPlicy bean = ac.getBean(RateDiscountPlicy.class);
    assertThat(bean).isInstanceOf(RateDiscountPlicy.class);

    org.junit.jupiter.api.Assertions.assertThrows(AssertionError.class,
        () -> ac.getBean(String.valueOf(assertThat(bean).isInstanceOf(FixDiscountPolicy.class))));
  }


  @Test
  @DisplayName("부모타입으로 전부 조회")
  void findAllBeanByParentType() {
    Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
     assertThat(beansOfType.size()).isEqualTo(2);
     for (String key : beansOfType.keySet()) {
       System.out.println("beansOfType.keySet() = " + beansOfType.get(key));
     }

  }

  @Test
  @DisplayName("부모 타입으로 모두 조회하기 - Object")
  // 모든 객체가 다 나온다.
  void findBeanByObjectType () {
    Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
    for (String key : beansOfType.keySet()) {
      System.out.println("beansOfType = " + beansOfType.get(key));
    }
  }

  @Configuration
  static class TestConfig {
    @Bean
    public DiscountPolicy rateDiscountPolicy() {
      return new RateDiscountPlicy();
    }

    @Bean
    public DiscountPolicy fixDiscountPolicy() {
      return new FixDiscountPolicy();
    }
  }
}
