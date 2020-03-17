package com.llf.demo.java.pojo;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/9/10 15:57
 */
public class Child extends Parent {

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return super.toString() + ",Child{" +
                "desc='" + desc + '\'' +
                "}}";
    }


    public static void main(String[] args) {
        Child child = new Child();
        child.setId(1L);
        child.setName("AA");
        child.setDesc("aa");

        System.out.println(child);
    }
}

