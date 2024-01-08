package Jpa.QueryDsl.study.repo;

import Jpa.QueryDsl.study.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectScoreRepository extends JpaRepository<Score,Long>,SubjectScoreCustom {

    /**
     * JpaRepository 만 상속하게 되면, JpaRepository 에서 제공하는 기본적인 메소드 밖에 사용하지 못한다.
     * 그렇기 때문에, QueryDSL 메소드가 정의되어있는 SubjectScoreCustom 인터페이스도 상속 받게 하여서
     * JpaRepository 의 기본적인 메소드와, QueryDSL 로 구현한 SubjectScoreCustom 메소드를 모두 한곳에서 사용할 수 있게끔 한다.
     * */
}
