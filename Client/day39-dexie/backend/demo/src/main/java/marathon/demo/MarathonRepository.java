package marathon.demo;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonArray;

@Repository
public class MarathonRepository {
    
    public static final String COLLECTION_MARATHON = "marathon";

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Document> save(JsonArray jsonArray){
        List<Document> docsToInsert = new LinkedList<>();
        jsonArray.forEach(v -> {
            Document d = new Document();
            d.put("name", v.asJsonObject().getString("name"));
            d.put("email", v.asJsonObject().getString("email"));
            System.out.println("DOC > " + d);
            // docsToInsert.add(d);
        });
        return mongoTemplate.insert(docsToInsert, COLLECTION_MARATHON).stream().toList();
    }

    public Document save2(String payload){
        List<Document> docsToInsert = new LinkedList<>();
        for (String s : payload.split(",")){
            System.out.println(s);
        }
        Document d = Document.parse(payload);
        // List<Document> insertedDocs = 
        
        return mongoTemplate.insert(d, COLLECTION_MARATHON);
    }

    


}
