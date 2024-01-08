package Jpa.QueryDsl.study.repo;

import Jpa.QueryDsl.study.response.SubjectScoreResponse;

import java.util.List;

public interface SubjectScoreCustom {
    List<SubjectScoreResponse> findScoreByStudent(Long studentId);
}
