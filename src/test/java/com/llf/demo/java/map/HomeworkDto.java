package com.llf.demo.java.map;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/5/31 10:06
 */
public class HomeworkDto {

    private Long id;
    private Long topicId;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "HomeworkDto{" +
                "id=" + id +
                ", topicId=" + topicId +
                ", title=" + title +
                '}';
    }
}
