import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Map;

public record Currency(
        @SerializedName("base_code")
        String currencyName,
        @SerializedName("conversion_rates")
        Map<String, Double> conversionRates,
        @SerializedName("target_code")
        String currencyTarget,
        @SerializedName("conversion_rate")
        BigDecimal rateConversion





) {
    public void showConversionRate() {
        if (this.conversionRates != null) {
            for (Map.Entry<String, Double> currencyRate : conversionRates.entrySet()) {
                System.out.println("CurrencyType:" + currencyRate.getKey() + " " +
                        "CurrencyValue" + currencyRate.getValue());
            }
        }
    }


}
