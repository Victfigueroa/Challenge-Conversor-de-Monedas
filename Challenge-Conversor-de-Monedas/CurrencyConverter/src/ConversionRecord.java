import com.google.gson.Gson;

public class ConversionRecord {
    private double conversion_result;

    public double result() {
        return conversion_result;
    }

    public static ConversionRecord parse(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, ConversionRecord.class);
    }
}
