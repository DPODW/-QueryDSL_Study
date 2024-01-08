package Jpa.QueryDsl.study.service;

import Jpa.QueryDsl.study.repo.SubjectScoreRepository;
import Jpa.QueryDsl.study.response.SubjectScoreResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectScoreService {

    private final SubjectScoreRepository subjectScoreRepository;


    public SubjectScoreService(SubjectScoreRepository subjectScoreRepository) {
        this.subjectScoreRepository = subjectScoreRepository;
    }

    public List<SubjectScoreResponse> findScoreByStudent(Long studentId){
        return subjectScoreRepository.findScoreByStudent(studentId);
        //JpaRepository 기본 제공 메소드가 아닌, SubjectScoreCustom 의 QueryDSL 로 구현한 메소드로 접근한 것을 볼수있다.
    }
}
