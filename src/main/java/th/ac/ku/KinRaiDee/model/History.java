package th.ac.ku.KinRaiDee.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class History {
    private String historyId;
    private String userId;
    private String foodId;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",timezone="Asia/Bangkok")
    private Date timestamp = new Date();


    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public Date getTimestamp() {
        return timestamp;}


    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
