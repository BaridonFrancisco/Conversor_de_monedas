package models;

import com.google.gson.annotations.SerializedName;

public record Quota(
        @SerializedName("plan_quota")
        int requestMax,
        @SerializedName("requests_remaining")
        int requestRemaining,
        @SerializedName("refresh_day_of_month")
        int dayResetMonth


) {
}
