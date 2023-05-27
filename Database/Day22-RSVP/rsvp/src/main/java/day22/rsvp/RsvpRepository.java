package day22.rsvp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

@Repository
public class RsvpRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Rsvp> getAllRsvp(){
        List<Rsvp> rsvps = new ArrayList<>();
        List<Map<String,Object>> result = jdbcTemplate.queryForList(Queries.SQL_GET_ALL_RSVP);
        for (Map<String,Object> r : result){
            rsvps.add(Rsvp.createRsvp(r));
        }
        return rsvps;
    }

    public Optional<List<Rsvp>> getRsvpsByName(String name){
        List<Map<String,Object>> result = jdbcTemplate.queryForList(Queries.SQL_GET_RSVP_BY_NAME,  "%%%s%%".formatted(name));

        if (result.isEmpty()){
            return Optional.empty();
        }
        else{
            List<Rsvp> rsvps = new ArrayList<>();
            for (Map<String,Object> r : result){
                rsvps.add(Rsvp.createRsvp(r));
            }
            return Optional.of(rsvps);
        }
    }

    public int upsert(MultiValueMap<String,String> params){
        int result = jdbcTemplate.update(Queries.SQL_UPSERT, params.getFirst("name"), params.getFirst("email"), params.getFirst("comments"), params.getFirst("phone"), params.getFirst("confirmation_date")
                                                            ,params.getFirst("name"), params.getFirst("email"), params.getFirst("comments"), params.getFirst("phone"), params.getFirst("confirmation_date"));
        return result;
    }

    public Integer countRsvp(){
        SqlRowSet result = jdbcTemplate.queryForRowSet(Queries.SQL_GET_COUNT);
        result.next();
        return result.getInt("total");
    }
}
