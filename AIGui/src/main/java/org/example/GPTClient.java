package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;

import javax.swing.*;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class GPTClient {
    private static final String API_URL = "https://api.openai.com/v1/completions";
    private static final Gson GSON = new Gson();
    private static final Client CLIENT = ClientBuilder.newClient(new ClientConfig());

    public void sendMessage(String message, Consumer<String> callback) {
        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() {
                JsonObject payload = new JsonObject();
                payload.addProperty("prompt", message);
                payload.addProperty("max_tokens", 2000);
                payload.addProperty("n", 1);
                payload.addProperty("stop", "");
                payload.addProperty("temperature", 0.5);
                payload.addProperty("model", "text-davinci-003");

                Response response = CLIENT.target(API_URL)
                        .request(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer Token_URL")
                        .header("Content-Type", "application/json")
                        .post(Entity.entity(payload.toString(), MediaType.APPLICATION_JSON));

                if (response.getStatus() == 200) {
                    JsonObject responseJson = GSON.fromJson(response.readEntity(String.class), JsonElement.class).getAsJsonObject();
                    return responseJson.getAsJsonArray("choices").get(0).getAsJsonObject().get("text").getAsString();
                } else {
                    // Handle API error
                    return null;
                }
            }

            protected void done() {
                try {
                    String responseMessage = get();
                    if (responseMessage != null) {
                        callback.accept(responseMessage);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };

        worker.execute();
    }
    }


