package com.preparation.crypto.Service;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.preparation.crypto.Model.Crypto;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class CryptoService {

    private static final String TOP_LIST_URL = "https://min-api.cryptocompare.com/data/top/mktcapfull?limit=10&tsym=USD";
    private static String cryptoConversionURL = "https://min-api.cryptocompare.com/data/price"; 
                                                                    //?fsym=BTC&tsyms=USD,JPY,EUR" 
    private static final String API_KEY = System.getenv("API_KEY");
    
    @Autowired
    private RestTemplate restTemplate;


    public Map<String,BigDecimal> getCryptoConversionResult(String from, String to){
        String fullURL = UriComponentsBuilder.fromHttpUrl(cryptoConversionURL)
        .queryParam("fsym", from)
        .queryParam("tsyms", to)
        .toUriString();

        RequestEntity<Void> reqEntity = RequestEntity.get(fullURL).header("authorization", "Apikey "+ API_KEY).build();
        ResponseEntity<Map> respEntity = restTemplate.exchange(reqEntity, Map.class);
        Map<String,BigDecimal> result = respEntity.getBody();
        return result;
        }


    public String getTopListByMarketCap(){
        ResponseEntity<String> respEntity = 
                    restTemplate.getForEntity(TOP_LIST_URL, String.class);
        String respBody = respEntity.getBody();
        return respBody;
    }

    public void getAllCoinList(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://min-api.cryptocompare.com/data/all/coinlist?summary=true", String.class);
        String payload = responseEntity.getBody();
        StringReader stringReader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(stringReader);
        JsonObject jsonObject = jsonReader.readObject();
        Set<String> keys = jsonObject.getJsonObject("Data").keySet();
        for (String key : keys){
            String fullName = jsonObject.getJsonObject("Data").getJsonObject(key).getString("FullName");
            System.out.println(fullName);
        }
    }


        // JsonArray jsonArray = jsonObject.getJsonObject("Data").asJsonArray();
        // for (int i = 0; i < jsonArray.size(); i++){
        //     String fullName = jsonArray.getJsonObject(i).getString("FullName");
        //     System.out.println("FULL NAME >>>>>>>>>>>>>>>>>>>>>>>>>" + fullName);
        // }
    // }

    public void getCrpyto(String json){
        StringReader stringReader = new StringReader(json);
        JsonReader jsonReader = Json.createReader(stringReader);
        JsonObject jsonObject = jsonReader.readObject();
        JsonArray jsonArray = jsonObject.getJsonArray("Data");
        for (int i = 0; i < jsonArray.size(); i++){
            Crypto crypto = new Crypto();
            crypto.setName(jsonArray.get(i).asJsonObject().getJsonObject("CoinInfo").getString("Name"));
            crypto.setFullName(jsonArray.get(i).asJsonObject().getJsonObject("CoinInfo").getString("FullName"));
            crypto.setImageURL(jsonArray.get(i).asJsonObject().getJsonObject("CoinInfo").getString("ImageUrl"));
            crypto.setUrl(jsonArray.get(i).asJsonObject().getJsonObject("CoinInfo").getString("Url"));
            crypto.setMktCap(jsonArray.get(i).asJsonObject().getJsonObject("RAW").getJsonObject("USD").getJsonNumber("MKTCAP").bigDecimalValue());
            Crypto.cryptoList.add(crypto);
        }
        for (Crypto crypto : Crypto.cryptoList){
            Crypto.cryptoNameAndFullName.put(crypto.getName(), crypto.getFullName());
        }

    }

}
