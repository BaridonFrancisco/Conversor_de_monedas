package models;

import com.google.gson.annotations.SerializedName;

public record CodeCurrency(
        @SerializedName("supported_codes")
        String [][] arr
){
}
