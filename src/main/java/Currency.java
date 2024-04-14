import com.google.gson.annotations.SerializedName;

import java.util.Map;

public record Currency(
        @SerializedName("base_code")
        double currencyName,
        Map<String,String> convertion_rate

) {
    private class rateMoney{

        Map<String,String>mapRate;
    }

    private void showMap(){
        if(!this.convertion_rate.isEmpty()){
            this.convertion_rate.forEach((k,v)-> System.out.println(k+":"+v));
        }
    }
}
