package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

class StatefulServiceTest {
  @Test
  void statefullServiceSingleton() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    StatefulService statefullService1 = ac.getBean("statefullService", StatefulService.class);
    StatefulService statefullService2 = ac.getBean("statefullService", StatefulService.class);

    // ThreadA: A사용자가 10000원 주문
    statefullService1.order("userA",10000);
    // ThreadA: B사용자가 20000원 주문
    statefullService1.order("userB",20000);

    //ThreadA : 사용자A 주문 금액 조회
    int price = statefullService1.getPrice();
    String name = statefullService1.getName();
    // 20000원이 나옴 error
    System.out.println("price = " + price);
    System.out.println("name = " + name);
    assertThat(statefullService1.getPrice()).isEqualTo(20000);
  }

  static class TestConfig {
    @Bean
    public StatefulService statefullService() {
      return new StatefulService();
    }
  }
}