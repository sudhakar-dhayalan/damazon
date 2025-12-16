package com.damazon.backend.controllers;

import com.damazon.backend.model.Question;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/assessment")
public class AssessmentController {
    @GetMapping("/questions")
    public List<Question> getAssessmentQuestions() {
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
}
