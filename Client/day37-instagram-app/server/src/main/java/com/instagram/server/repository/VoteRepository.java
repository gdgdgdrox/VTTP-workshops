package com.instagram.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VoteRepository {
    
    @Autowired
    private RedisTemplate<String,String> redisTemp;

    public long updateVote(String postId, String update){
        long count = 0;
        if (update.equals("up")){
            count = redisTemp.opsForHash().increment(postId, "up", 1);
            redisTemp.opsForHash().put("something", "name", "GD");
            System.out.println("Latest count %d".formatted(count));
        }
        else{
            //even though the method says increment(), so long as we pass in a negative number it will decrement
            count = redisTemp.opsForHash().increment(postId, "down", -1);
        }
        return count;
        // else if (update.equals("downVote")){
        //     redisTemp.opsForHash().
        // }
    }

    public void getUpVoteCount(String postId){
        System.out.println(redisTemp.opsForHash().get(postId, "up"));
    }

}
