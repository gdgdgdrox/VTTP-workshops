package day23.beer.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import day23.beer.model.Brewery;

@Repository
public class StyleRepo {
    @Autowired
    private JdbcTemplate template;
    
    public List<String> getStyleList(){
        List<String> styleList = new ArrayList<>();
        SqlRowSet result = template.queryForRowSet(Queries.SQL_QUERY_STYLE);
        while (result.next()){
            styleList.add(result.getString("style_name"));
        }
        // System.out.println(">>>SIZE" + styleList.size());
        return styleList;
    }

    public List<Brewery> getBreweries(String styleName){
        List<Brewery> breweries = new ArrayList<>();
        SqlRowSet result = template.queryForRowSet(Queries.SQL_GET_BREWERIES,styleName);
        while (result.next()){
            Brewery brewery = new Brewery();
            brewery.setName(result.getString("name"));
            brewery.setAddress1(result.getString("address1"));
            brewery.setCity(result.getString("city"));
            brewery.setCountry(result.getString("country"));
            brewery.setWebsite(result.getString("website"));
            breweries.add(brewery);
        }
        // System.out.println("EMPTY?" + breweries.isEmpty());
        // System.out.println(">>>RESULT SIZE" + breweries.size());
        return breweries;
    }

}
