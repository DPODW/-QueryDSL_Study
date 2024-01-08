package Jpa.QueryDsl.study.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class QueryDslConfig {

   @Autowired
   private EntityManager em;
   /**
    * QueryDSL 은 JPA 의 EntityManager 를 사용하기 때문에
    * QueryDSL 설정 시에, 이렇게 생성해주어야 한다.
    * ++ 오류는 인텔리제이 문제로, 실제 컴파일 시에는 문제가 발생하지 않는다.
    * */


   @Bean
   public JPAQueryFactory jpaQueryFactory(){
       return new JPAQueryFactory(em);
   }
   /**
    * QueryDSL 에서 제공하는 기능인 JPAQueryFactory.
    * JPAQueryFactory 는 QueryDSL 의 핵심 기능인 쿼리를 작성하고 실행하는 역할을 한다.
    * */

}
