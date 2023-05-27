package com.preparation.crypto.Controller;

import java.math.BigDecimal;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.preparation.crypto.Model.Crypto;
import com.preparation.crypto.Service.CryptoService;

@Controller
public class CryptoController {

    @Autowired
    private CryptoService service;

    @PostConstruct
    public void getTopListByMarketCapData(){
        service.getCrpyto(service.getTopListByMarketCap());
    }

    @GetMapping
    public String convertCryptoPage(Model model){
        model.addAttribute("cryptoNameAndFullName", Crypto.cryptoNameAndFullName);
        model.addAttribute("fiatCurrency", Crypto.fiatCurrency);
        return "cryptoconversion";
    }

    @GetMapping("/convert")
    public String returnResultPage(@RequestParam String to, @RequestParam String from, Model model){
        Map<String,BigDecimal> result = service.getCryptoConversionResult(from, to);
        model.addAttribute("result", result);
        model.addAttribute("from", from);
        return "conversionresult";
    }

    @GetMapping("/top10crypto")
    public String returnTop10Crypto(Model model){
        model.addAttribute("cryptolist", Crypto.cryptoList);
        return "cryptolist";
    }


    

}
