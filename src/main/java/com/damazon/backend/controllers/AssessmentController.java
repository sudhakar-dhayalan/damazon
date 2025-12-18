package com.damazon.backend.controllers;

import com.damazon.backend.model.AssessmentDetail;
import com.damazon.backend.model.Question;
import com.damazon.backend.service.AssessmentService;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assessment")
@Validated
public class AssessmentController {
    @Autowired
    private AssessmentService assessmentService;

    @GetMapping("/questions")
    public List<Question> getAssessmentQuestions() {
        return this.assessmentService.getQuestions();
    }

    @PostMapping("/submit-assessment")
    public AssessmentDetail saveAssessmentDetail(@RequestBody AssessmentDetail assessmentDetail) {
        return this.assessmentService.saveAssessmentDetail(assessmentDetail);
    }

    @GetMapping("/leaderboard")
    public List<AssessmentDetail> getLeaderboard() {
        return this.assessmentService.getLeaderboard();
    }

    @GetMapping("/leaderboard/{email}")
    public List<AssessmentDetail> getLeaderboardByEmail(@PathVariable @Email String email) {
        return this.assessmentService.getLeaderboardByEmail(email);
    }
}
