package com.example.miniprojet.dataBase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Map;

@Entity(tableName = "questions")
public class Questions implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String type;
    private String question;
    private String reponseJuste;
    private String reponseFausse1;
    private String reponseFausse2;
    private String reponseFausse3;

    public Questions(String type, String question, String reponseJuste, String reponseFausse1, String reponseFausse2, String reponseFausse3) {
        this.type = type;
        this.question = question;
        this.reponseJuste = reponseJuste;
        this.reponseFausse1 = reponseFausse1;
        this.reponseFausse2 = reponseFausse2;
        this.reponseFausse3 = reponseFausse3;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getQuestion() {
        return question;
    }

    public String getReponseJuste() {
        return reponseJuste;
    }

    public String getReponseFausse1() {
        return reponseFausse1;
    }

    public String getReponseFausse2() {
        return reponseFausse2;
    }

    public String getReponseFausse3() {
        return reponseFausse3;
    }
}