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
        assessmentQuestions.add(
                new Question(3,
                        "Which controller is responsible for maintaining a set of identical Pods?",
                        new String[]{"StatefulSet", "DaemonSet", "Deployment", "ReplicaSet"})
        );
        assessmentQuestions.add(
                new Question(4,
                        "What component stores the cluster state and configuration data in Kubernetes?",
                        new String[]{"etcd", "Kubelet", "Kube-Proxy", "API Server"})
        );
        assessmentQuestions.add(
                new Question(5,
                        "Which update strategy allows zero downtime deployments in Kubernetes?",
                        new String[]{"Recreate", "Rolling Update", "Blue-Green", "Canary"})
        );
        assessmentQuestions.add(
                new Question(6,
                        "Which Kubernetes object is used to manage configuration data?",
                        new String[]{"Secret", "ConfigMap", "Volume", "Namespace"})
        );
        assessmentQuestions.add(
                new Question(7,
                        "Which component runs on each node and ensures containers are running in a Pod?",
                        new String[]{"Kube-Proxy", "Kubelet", "Scheduler", "Controller Manager"})
        );
        assessmentQuestions.add(
                new Question(8,
                        "Which Kubernetes resource manages external access to services in a cluster?",
                        new String[]{"Service", "Ingress", "Endpoint", "NetworkPolicy"})
        );
        assessmentQuestions.add(
                new Question(9,
                        "Which object is used to request storage resources in Kubernetes?",
                        new String[]{"PersistentVolume", "StorageClass", "PersistentVolumeClaim", "Volume"})
        );
        assessmentQuestions.add(
                new Question(10,
                        "What is used to organize and select Kubernetes objects based on key-value pairs?",
                        new String[]{"Annotation", "Label", "Selector", "Namespace"})
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
