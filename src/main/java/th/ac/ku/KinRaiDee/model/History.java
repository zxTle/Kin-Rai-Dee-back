package th.ac.ku.KinRaiDee.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.cloud.Timestamp;

public class History {
    private String historyId;
    private String userId;
    private String foodId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Timestamp timestamp;

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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
