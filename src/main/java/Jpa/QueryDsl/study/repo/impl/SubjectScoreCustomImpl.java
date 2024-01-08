package Jpa.QueryDsl.study.repo.impl;

import Jpa.QueryDsl.study.entity.QScore;
import Jpa.QueryDsl.study.entity.QStudent;
import Jpa.QueryDsl.study.repo.SubjectScoreCustom;
import Jpa.QueryDsl.study.response.SubjectScoreResponse;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

public class SubjectScoreCustomImpl implements SubjectScoreCustom {

    private final JPAQueryFactory queryFactory;


    public SubjectScoreCustomImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<SubjectScoreResponse> findScoreByStudent(Long studentId) {
        QStudent qStudent = QStudent.student;
        QScore qScore = QScore.score1;
        /**
         * Querydsl 을 사용하게 되면, 엔티티 클래스(JPA 테이블 정의 클래스) 와 동일한 객체를 가진 Q 클래스가 생성된다.
         * Q 클래스를 사용하는 이유는
         * 1. 엔티티 클래스로 접근해서 값을 가져오는것이 안되기 때문 <=> static 한 클래스는 객체를 생성할 필요없이 바로 접근해서 사용할수 있기 때문
         * 2. Q 클래스는 엔티티 속성과 타입을 정확히 표시하므로, 타입에 맞지 않는 연산이나 비교를 사전에 잡을수 있으며 자동 완성 기능을 사용할 수 있다.
         * 근본적인 이유는 1 번이다.
         * */


        JPAQuery<Tuple>query =  queryFactory.select(
            qStudent.studentId,
            qStudent.name,
            qStudent.age,
            qScore.subject,
            qScore.score
        ).from(qStudent).
                join(qScore).
                on(qStudent.studentId.eq(qScore.studentId)).
                where(qStudent.studentId.eq(studentId)).
                orderBy(qStudent.studentId.asc());
        /**
         * Querydsl 로 표현한 JOIN 쿼리문이다.
         * JPAQuery<Tuple> : JPA 에서 제공하는 Tuple 은, Map 과 비슷한 저장 공간이다. 주로 여러값을 묶어서 반환할때 사용한다.
         * eq : Querydsl 에서 사용하는 문법으로, '동일한지' 라는 조건을 의미한다.
         *
         * 반환 타입은 Tuple 이 아닌, DTO 도 가능하다.
         * */



        return query.fetchJoin().fetch().stream().map(tupple -> SubjectScoreResponse.builder()
                .studentId(tupple.get(qStudent.studentId))
                .name(tupple.get(qStudent.name))
                .age(tupple.get(qStudent.age))
                .subject(tupple.get(qScore.subject))
                .score(tupple.get(qScore.score))
                .build()).toList();
    }
    /**
     * 최종적으로 실행한 쿼리를 반환하는 로직.
     * fetchJoin: 기본적으로 JPA 는 지연로딩이다. 그런데 지연로딩이 늘 최선의 선택이 아니고 즉시로딩 또한 필요한 시점이 많다.
     * fetchJoin 을 사용하면, 특정 부분만 전체 조회(즉시 로딩) 을 사용하게 해준다. 현재의 경우는 모든 정보를 조회하는 기능이니까, 즉시 로딩이 효율적이다.
     * 그러므로 fetchJoin 을 사용하였다.
     *
     * fetch: queryDSL 의 결과를 반환하는 메소드로, fetch 는 결과값을 List 로 반환한다.
     * 그 외에도 fetchOne(단건 조회) , fetchFirst(처음의 한건 조회) , fetchCount(Count 기능) 등 반환 방법이 여러개 존재한다.
     *
     * 우리는 SubjectScoreResponse 반환 클래스를 구현하였기 때문에, 해당 클래스에 데이터를 바인딩 해서 최종 결과를 반환받는다.
     * */
}
