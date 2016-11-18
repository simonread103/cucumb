package com.cuc;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Optional;

public class TitleLookupService {

    private String albumsUri = "https://jsonplaceholder.typicode.com/albums?id=";
    private HttpClient client;
    private JsonParser parser;

    public TitleLookupService() {
        client = HttpClientBuilder.create().build();
        parser = new JsonParser();
    }


    public Optional<String> getTitleById(String aId) throws IOException {
        Optional<String> result = Optional.empty();
        if (Objects.nonNull(aId)) {
            HttpGet request = prepareRequest(aId);

            try {
                Optional<String> responseEntity = getJSONresponse(request);
                if (responseEntity.isPresent()) {

                    JsonArray ar = parser.parse(responseEntity.get()).getAsJsonArray();
                    if (ar.size() != 0 && ar.get(0) != null) {
                        JsonObject obj = ar.get(0).getAsJsonObject();
                        result = Optional.ofNullable(obj.get("title").getAsString());
                    }

               }
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return result;
    }

    private HttpGet prepareRequest(String aId) {
        String finalUrl = albumsUri + aId;
        return new HttpGet(finalUrl);

    }

    public Optional<String> getJSONresponse(HttpGet request) throws IOException {
        Optional<String> result = Optional.empty();
        HttpResponse response = client.execute(request);
        HttpEntity responseEntity = response.getEntity();
        if (responseEntity != null) {
            result = Optional.ofNullable(EntityUtils.toString(responseEntity));
        }
        return result;
    }

    public void setAlbumsUri( String albumsUri ) {
        this.albumsUri = albumsUri;
    }

    public void setClient(HttpClient client) {
        this.client = client;
    }
}