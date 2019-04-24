package com.practice.shine.customlistview.model;

public class SchoolSATData {
    private String name;
    private String dbn;
    private String numSatTestTakers;
    private String readingScore;
    private String writingScore;
    private String mathScore;

    public SchoolSATData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbn() {
        return dbn;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }

    public String getNumSatTestTakers() {
        return numSatTestTakers;
    }

    public void setNumSatTestTakers(String numSatTestTakers) {
        this.numSatTestTakers = numSatTestTakers;
    }

    public String getReadingScore() {
        return readingScore;
    }

    public void setReadingScore(String readingScore) {
        this.readingScore = readingScore;
    }

    public String getWritingScore() {
        return writingScore;
    }

    public void setWritingScore(String writingScore) {
        this.writingScore = writingScore;
    }

    public String getMathScore() {
        return mathScore;
    }

    public void setMathScore(String mathScore) {
        this.mathScore = mathScore;
    }
}
