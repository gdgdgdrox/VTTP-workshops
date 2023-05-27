package com.preparation.crypto.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Crypto {
    private String name;
    private String fullName;
    private BigDecimal mktCap;
    private String imageURL;
    private String url;
    private Map<String, BigDecimal> conversionResult = new HashMap();
    public static Map<String, String> cryptoNameAndFullName = new HashMap<>();
    public static List<Crypto> cryptoList = new ArrayList<>();
    public static List<String> fiatCurrency = Arrays.asList("USD", "SGD", "JPY", "EUR");


}
