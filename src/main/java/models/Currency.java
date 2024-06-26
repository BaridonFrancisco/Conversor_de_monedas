package models;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import java.util.Map;

public record Currency(

        @SerializedName("base_code")
        String currencyName,
        @SerializedName("conversion_rates")
        Map<String, BigDecimal> conversionRates,
        @SerializedName("target_code")
        String currencyTarget,
        @SerializedName("conversion_rate")
        BigDecimal rateConversion,
        @SerializedName("conversion_result")
        BigDecimal conversionResult



) {


}
