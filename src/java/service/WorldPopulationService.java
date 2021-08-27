/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.CountryEntity;
import entity.WorldPopulation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 *
 * @author Dao Van Do
 */
public class WorldPopulationService {

    public WorldPopulation getWorldPopulation() throws IOException {
        WorldPopulation population = new WorldPopulation();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://world-population.p.rapidapi.com/worldpopulation")
                .get()
                .addHeader("x-rapidapi-host", "world-population.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "eebe4323e0msh6008b560d44e924p15c646jsna92edfda6817")
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        JsonObject jsonObject = new JsonParser().parse(responseBody).getAsJsonObject();

        String worldPopulation = jsonObject.get("body").getAsJsonObject().get("world_population").toString();
        String totalCountries = jsonObject.get("body").getAsJsonObject().get("total_countries").toString();
        population.setTotalCountries(totalCountries);
        population.setWorldPopulation(worldPopulation);

        return population;
    }

    public CountryEntity getPopulationByCountryName(String countryName) throws IOException {
        CountryEntity countryEntity = new CountryEntity();
        OkHttpClient client = new OkHttpClient();
        countryName = countryName.replaceAll("\"", "");
        Request request = new Request.Builder()
                .url("https://world-population.p.rapidapi.com/population?country_name=" + countryName)
                .get()
                .addHeader("x-rapidapi-host", "world-population.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "eebe4323e0msh6008b560d44e924p15c646jsna92edfda6817")
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        JsonObject jsonObject = new JsonParser().parse(responseBody).getAsJsonObject();
        String name = jsonObject.get("body").getAsJsonObject().get("country_name").toString();
        name = name.replaceAll("\"", "");
        countryEntity.setName(name);
        countryEntity.setPopulation(jsonObject.get("body").getAsJsonObject().get("population").toString());
        countryEntity.setRanking(jsonObject.get("body").getAsJsonObject().get("ranking").toString());

        return countryEntity;
    }

    public List<CountryEntity> getAllPopulationByCountry(int startIndex, int endindex) throws IOException {
        List<CountryEntity> ces = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://world-population.p.rapidapi.com/allcountriesname")
                .get()
                .addHeader("x-rapidapi-host", "world-population.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "eebe4323e0msh6008b560d44e924p15c646jsna92edfda6817")
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        JsonObject jsonObject = new JsonParser().parse(responseBody).getAsJsonObject();
        JsonArray jsonElements = (JsonArray) jsonObject.getAsJsonObject("body").get("countries");
        jsonObject.getAsJsonObject("body").entrySet();
        for (int i = startIndex; i < endindex; i++) {
            CountryEntity entity = getPopulationByCountryName(jsonElements.get(i).toString());
            ces.add(entity);
        }
        return ces;
    }

}
