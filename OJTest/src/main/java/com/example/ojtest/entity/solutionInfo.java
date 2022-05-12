package com.example.ojtest.entity;

public class solutionInfo {
    private int id;
    private String solution;
    private int titleId;
    private int userId;

    public solutionInfo() {
    }

    public solutionInfo(String solution, int titleId, int userId) {
        this.solution = solution;
        this.titleId = titleId;
        this.userId = userId;
    }

}
