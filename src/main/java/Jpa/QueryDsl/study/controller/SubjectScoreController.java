package Jpa.QueryDsl.study.controller;

import Jpa.QueryDsl.study.response.SubjectScoreResponse;
import Jpa.QueryDsl.study.service.SubjectScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectScoreController {

    private final SubjectScoreService service;

    @GetMapping("/score/{studentId}")
    public ResponseEntity<List<SubjectScoreResponse>>getScoreBStudent(@PathVariable Long studentId){
        List<SubjectScoreResponse> subjectScoreList = service.findScoreByStudent(studentId);
        return ResponseEntity.ok(subjectScoreList);
    }
    //반환 값이 1개 이상일 수 도 있기 때문에 List 타입
}
