package models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public record CodeCurrency2(
        @SerializedName("currencies")
        Map<String,String>mapCode
) {
}
