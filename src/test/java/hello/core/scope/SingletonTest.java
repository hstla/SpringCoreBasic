package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {
  @Test
  void singletonBeanFing() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
    SingletonBean singletonBea1 = ac.getBean(SingletonBean.class);
    SingletonBean singletonBea2  = ac.getBean(SingletonBean.class);
    System.out.println("singletonBea1 = " + singletonBea1);
    System.out.println("singletonBea2 = " + singletonBea2);
    assertThat(singletonBea1).isSameAs(singletonBea2);
    ac.close();

  }

  @Scope("singleton")
  static class SingletonBean {
    @PostConstruct
    public void init() {
      System.out.println("SingletonBean.init");
    }
    @PreDestroy
    public void destroy() {
      System.out.println("SingletonBean.destroy");
    }
  }
}
