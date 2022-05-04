package com.example.maventest;

public class Book {
    private String Bno;
    private String Bname;
    private String Bauthor;
    private Double Bprice;
    private String Boress;

    @Override
    public String toString() {
        return "Book{" +
                "Bno='" + Bno + '\'' +
                ", Bname='" + Bname + '\'' +
                ", Bauthor='" + Bauthor + '\'' +
                ", Bprice=" + Bprice +
                ", Boress='" + Boress + '\'' +
                '}';
    }

    public Book() {
    }

    public Book(String bno, String bname, String bauthor, Double bprice, String boress) {
        Bno = bno;
        Bname = bname;
        Bauthor = bauthor;
        Bprice = bprice;
        Boress = boress;
    }

    public String getBno() {
        return Bno;
    }

    public void setBno(String bno) {
        Bno = bno;
    }

    public String getBname() {
        return Bname;
    }

    public void setBname(String bname) {
        Bname = bname;
    }

    public String getBauthor() {
        return Bauthor;
    }

    public void setBauthor(String bauthor) {
        Bauthor = bauthor;
    }

    public Double getBprice() {
        return Bprice;
    }

    public void setBprice(Double bprice) {
        Bprice = bprice;
    }

    public String getBoress() {
        return Boress;
    }

    public void setBoress(String boress) {
        Boress = boress;
    }
}
