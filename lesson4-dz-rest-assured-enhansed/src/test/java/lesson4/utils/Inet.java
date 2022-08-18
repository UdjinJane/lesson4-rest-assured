package lesson4.utils;

        import com.fasterxml.jackson.databind.JsonNode;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import okhttp3.*;
        import okhttp3.Response;

        import java.io.FileWriter;
        import java.io.IOException;

public class Inet {

    private final OkHttpClient httpClient = new OkHttpClient();

    public static void main(String[] args) throws IOException {
        Inet obj = new Inet();
        obj.sendPOST();
    }

    public void sendPOST() throws IOException {

        // json formatted data
        String json = new StringBuilder()
                .append("{")
                .append("\"username\":\"udjinjane\",")
                .append("\"firstName\":\"udjin\",")
                .append("\"lastName\":\"jane\",")
                .append("\"email\":\"udjinjane@gmail.com\"")
                .append("}").toString();

        // json request body

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                json
                );

        HttpUrl.Builder urlBuilder
                = HttpUrl.parse("https://api.spoonacular.com/users/connect").newBuilder();
        urlBuilder.addQueryParameter("apiKey", "a4b015b32e76460d84c93057186f1905");

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body

            // System.out.println(response.body().string());

            // Выдергиваем из респонса результат хэша.
            String bodyTXT= response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode hash = objectMapper
                    .readTree(bodyTXT)
                    .at("/hash");

             JsonNode username = objectMapper
                     .readTree(bodyTXT)
                    .at ("/username");

            String hashtxt = hash.asText();
            String usernameTXT = username.asText();
            // System.out.println(hash.asText());


            FileWriter writer = new FileWriter(
                    "src/main/resources/hash.txt");
            writer.write("hash = " + hashtxt + "\n");
            writer.write("username = " + usernameTXT);
            writer.close();

            // FileReader reader = new FileReader(
            //        "hash.txt");

            return;

        }

    }

}