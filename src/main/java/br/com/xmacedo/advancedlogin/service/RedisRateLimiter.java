package br.com.xmacedo.advancedlogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisRateLimiter {

    @Autowired
    private RedisOperations<String, String> operations;

    private final String script =
            "local tokens = redis.call('get', KEYS[1])\n" +
                    "if tokens == false then tokens = ARGV[1] end\n" +
                    "tokens = tonumber(tokens)\n" +
                    "if tokens <= 0 then return -1 end\n" +
                    "tokens = tokens - 1\n" +
                    "redis.call('setex', KEYS[1], ARGV[2], tokens)\n" +
                    "return tokens";
    public boolean allow(String fp) {
        Long result = operations.execute(
                (RedisCallback<Long>) connection -> connection.eval(
                        script.getBytes(),
                        ReturnType.INTEGER, 1,
                        ("login:bucket:" + fp).getBytes(),
                        "5".getBytes(),      // refill tokens
                        "60".getBytes()      // TTL seconds
                )
        );
        return result != -1;
    }
}
