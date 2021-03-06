package com.whu.onelist.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/**
 * @author: create by zhong
 * @description: com.whu.onelist.vo
 * @date:2019/4/23
 */
public class Matter {
    private Long matterID;
    private Long userID;
    private String caption;
    private String detail;
    private Timestamp remindTime;
    private Long remindInterval;
    private int priority;
    private int status;

    public Long getMatterID() {
        return matterID;
    }

    public void setMatterID(Long matterID) {
        this.matterID = matterID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Timestamp getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Timestamp remindTime) {
        this.remindTime = remindTime;
    }

    public Long getRemindInterval() {
        return remindInterval;
    }

    public void setRemindInterval(Long remindInterval) {
        this.remindInterval = remindInterval;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
