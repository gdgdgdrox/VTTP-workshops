package day23.beer.repository;
public class Queries {
    public static final String SQL_QUERY_STYLE = "select distinct(style_name) from styles order by style_name asc;";
    public static final String SQL_GET_BREWERIES = "select distinct(breweries.name), address1, city, country, website from breweries join beers on breweries.id = beers.brewery_id join styles on beers.style_id = styles.id where styles.style_name = ? order by 1 asc;";

}




