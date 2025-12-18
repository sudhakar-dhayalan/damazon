package com.damazon.backend.service;

import com.damazon.backend.model.AssessmentDetail;
import com.damazon.backend.model.Question;
import com.damazon.backend.repo.AssessmentDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssessmentService {

    @Autowired
    private AssessmentDetailRepo assessmentDetailRepo;

    public List<Question> getQuestions() {
        List<Question> assessmentQuestions = new ArrayList<>();
        assessmentQuestions.add(
                new Question(1,
                        "What is the smallest deployable unit in Kubernetes?",
                        new String[]{"Pod", "Node", "Cluster", "Container"})
        );
        assessmentQuestions.add(
                new Question(2,
                        "Which Kubernetes object is used to expose an application running on a set of Pods?",
                        new String[]{"Ingress", "Service", "Deployment", "ConfigMap"})
        );
        return assessmentQuestions;
    }

    public AssessmentDetail saveAssessmentDetail(AssessmentDetail assessmentDetail) {
        return this.assessmentDetailRepo.save(assessmentDetail);
    }

    public List<AssessmentDetail> getLeaderboardByEmail(String email) {
        return this.assessmentDetailRepo.findByEmail(email);
    }

    public List<AssessmentDetail> getLeaderboard() {
        return this.assessmentDetailRepo.findAll();
    }
}
