import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyConverter {
    public static double fetchConversionRate(String baseCode, String targetCode) throws IOException, InterruptedException {
        String apiKey = "71c28498becd4c4c2b1035d6"; // Reemplaza con tu clave de API
        String url = String.format("https://api.exchangerate-api.com/v4/latest/%s", baseCode);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Imprime la respuesta para verificar su contenido
        System.out.println("API Response: " + response.body());

        // Parsear la respuesta JSON
        JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonObject rates = jsonObject.getAsJsonObject("rates");

        if (rates != null && rates.has(targetCode)) {
            return rates.get(targetCode).getAsDouble();
        } else {
            throw new IllegalStateException("Invalid response from API");
        }
    }

    public static void performConversion(int choice, double amount) {
        String baseCode = "";
        String targetCode = "";
        switch (choice) {
            case 1:
                baseCode = "USD";
                targetCode = "EUR";
                break;
            case 2:
                baseCode = "EUR";
                targetCode = "USD";
                break;
            case 3:
                baseCode = "USD";
                targetCode = "GBP";
                break;
            case 4:
                baseCode = "GBP";
                targetCode = "USD";
                break;
            case 5:
                baseCode = "USD";
                targetCode = "JPY";
                break;
            case 6:
                baseCode = "JPY";
                targetCode = "USD";
                break;
            default:
                System.out.println("Opción no válida");
                return;
        }

        try {
            double rate = fetchConversionRate(baseCode, targetCode);
            double convertedAmount = amount * rate;
            System.out.printf("Monto convertido: %.2f %s%n", convertedAmount, targetCode);
        } catch (Exception e) {
            System.out.println("Ocurrió un error durante la conversión: " + e);
        }
    }
}