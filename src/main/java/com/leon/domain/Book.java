package com.leon.domain;

/***
 *
 * @Author:Leon
 * @Description:itheima
 * @date: 2019/4/7 9:40
 *
 ****/
public class Book {

    private Integer id;
    private String name;
    private Float price;
    private String pic;
    private String desc;

    public Book() {
    }

    public Book(Integer id, String name, Float price, String pic, String desc) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pic = pic;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
