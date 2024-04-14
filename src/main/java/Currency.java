import com.google.gson.annotations.SerializedName;

import java.util.Map;

public record Currency(
        @SerializedName("base_code")
        String currencyName,
        @SerializedName("conversion_rates")
        Map<String, Double> conversionRates

) {



}
