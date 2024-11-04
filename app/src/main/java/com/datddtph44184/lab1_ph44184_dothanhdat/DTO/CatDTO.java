package com.datddtph44184.lab1_ph44184_dothanhdat.DTO;

public class CatDTO {
    int id;
    String name;
    public String toString (){
        return "ID cat: "+ id + ", name: " + name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//tạo các hàm getter vad setter bằng generate
}
