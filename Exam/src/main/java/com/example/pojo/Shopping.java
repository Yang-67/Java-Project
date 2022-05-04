package com.example.pojo;

public class Shopping {
    private int wareid;
    private String warename;
    private int wareprice;
    private String waretype;
    private String wareintro;
    private String wareimage;
    private int wareif;
    private String dattime;

    public Shopping() {
    }

    public Shopping(int wareid, String warename, int wareprice, String waretype, String wareintro, String wareimage, int wareif, String dattime) {
        this.wareid = wareid;
        this.warename = warename;
        this.wareprice = wareprice;
        this.waretype = waretype;
        this.wareintro = wareintro;
        this.wareimage = wareimage;
        this.wareif = wareif;
        this.dattime = dattime;
    }

    public int getWareid() {
        return wareid;
    }

    public void setWareid(int wareid) {
        this.wareid = wareid;
    }

    public String getWarename() {
        return warename;
    }

    public void setWarename(String warename) {
        this.warename = warename;
    }

    public int getWareprice() {
        return wareprice;
    }

    public void setWareprice(int wareprice) {
        this.wareprice = wareprice;
    }

    public String getWaretype() {
        return waretype;
    }

    public void setWaretype(String waretype) {
        this.waretype = waretype;
    }

    public String getWareintro() {
        return wareintro;
    }

    public void setWareintro(String wareintro) {
        this.wareintro = wareintro;
    }

    public String getWareimage() {
        return wareimage;
    }

    public void setWareimage(String wareimage) {
        this.wareimage = wareimage;
    }

    public int getWareif() {
        return wareif;
    }

    public void setWareif(int wareif) {
        this.wareif = wareif;
    }

    public String getDattime() {
        return dattime;
    }

    public void setDattime(String dattime) {
        this.dattime = dattime;
    }

    @Override
    public String toString() {
        return "Shopping{" +
                "wareid=" + wareid +
                ", warename='" + warename + '\'' +
                ", wareprice=" + wareprice +
                ", waretype='" + waretype + '\'' +
                ", wareintro=" + wareintro +
                ", wareimage='" + wareimage + '\'' +
                ", wareif=" + wareif +
                ", dattime='" + dattime + '\'' +
                '}';
    }
}
