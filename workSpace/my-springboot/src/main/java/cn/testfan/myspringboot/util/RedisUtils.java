package cn.testfan.myspringboot.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class RedisUtils {
    // Redis连接池
    private static JedisPool jedisPool;

    // 静态代码块，仅执行一次，主要是对整个类做初始化
    static {
        Map<String, String> props = PropertiesUtils.getAll(RedisUtils.class.getResourceAsStream("/application.properties"));
        String ip = props.get("redis.ip");
        int port = Integer.parseInt(props.get("redis.port"));
        int timeout = Integer.parseInt(props.get("redis.timeout"));;
        String password = props.get("redis.password");

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxActive(10);
        config.setMaxIdle(5);
        config.setMaxWait(5000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        config.setTestWhileIdle(true);
        config.setMinEvictableIdleTimeMillis(60000L);
        config.setTimeBetweenEvictionRunsMillis(3000L);
        config.setNumTestsPerEvictionRun(-1);
        jedisPool = new JedisPool(config, ip, port, timeout, password);
    }


    public static void close(Jedis jedis) {
        try {
            jedisPool.returnResource(jedis);
        } catch (Exception e) {
            if (jedis.isConnected()) {
                jedis.quit();
                jedis.disconnect();
            }
        }
    }


    public static String get(String key) {

        String value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        } finally {
            //返还到连接池
            close(jedis);
        }

        return value;
    }

    public static void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } finally {
            //返还到连接池
            close(jedis);
        }
    }

    public static Set<String> keys(String keyFormat) {

        Jedis jedis = null;
        Set<String> set = new HashSet<>();
        try {
            jedis = jedisPool.getResource();
            set = jedis.keys(keyFormat);
        } finally {
            //返还到连接池
            close(jedis);
        }
        return set;
    }


    public static void hset(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hset(key, field, value);
        } finally {
            //返还到连接池
            close(jedis);
        }
    }

    /**
     * 获取数据
     *
     * @param key
     * @return
     */
    public static String hget(String key, String field) {

        String value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.hget(key, field);
        } finally {
            //返还到连接池
            close(jedis);
        }

        return value;
    }


    /**
     * 发布消息
     *
     * @param channel
     * @param message
     */
    public static void publish(String channel, String message) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.publish(channel, message);
        } finally {
            //返还到连接池
            close(jedis);
        }
    }


    /**
     * 向名为key的list尾部添加一个值为value的元素
     *
     * @param key
     * @param value
     */
    public static void rpush(String key, String value, Integer index) {
        Jedis jedis = null;
        try {

            jedis = jedisPool.getResource();
            jedis.select(index);
            jedis.rpush(key, value);

        } finally {
            //返还到连接池
            close(jedis);
        }
    }


    public static String lindex(String key, Integer index, Integer dbIndex) {
        Jedis jedis = null;
        String value = null;
        try {

            jedis = jedisPool.getResource();
            jedis.select(dbIndex);
            value = jedis.lindex(key, index);

        } finally {

            //返还到连接池
            close(jedis);

        }
        return value;
    }


    public static void hmset(Object key, Map<String, String> hash) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hmset(key.toString(), hash);
        } finally {
            //返还到连接池
            close(jedis);

        }
    }

    public static void hmset(Object key, Map<String, String> hash, int time) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hmset(key.toString(), hash);
            jedis.expire(key.toString(), time);
        } finally {
            //返还到连接池
            close(jedis);

        }
    }

    public static List<String> hmget(Object key, String... fields) {
        List<String> result = null;
        Jedis jedis = null;
        try {

            jedis = jedisPool.getResource();
            result = jedis.hmget(key.toString(), fields);

        } finally {
            //返还到连接池
            close(jedis);
        }
        return result;
    }

    public static Set<String> hkeys(String key) {
        Set<String> result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.hkeys(key);

        } finally {
            //返还到连接池
            close(jedis);

        }
        return result;
    }

    public static List<String> lrange(String key, int from, int to, Integer index) {
        List<String> result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(index);
            result = jedis.lrange(key, from, to);

        } finally {
            //返还到连接池
            close(jedis);

        }
        return result;
    }

    public static Map<String, String> hgetAll(String key) {
        Map<String, String> result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.hgetAll(key);
        } finally {
            //返还到连接池
            close(jedis);
        }
        return result;
    }

    public static void del(String key, Integer index) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(index);
            jedis.del(key);
        } finally {
            //返还到连接池
            close(jedis);
        }
    }

    public static long llen(String key) {

        long len = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            len = jedis.llen(key);
        } finally {
            //返还到连接池
            close(jedis);
        }
        return len;
    }

    public static void main(String[] args) {
        RedisUtils.set("name","zhangsan");
        String name = RedisUtils.get("name");
        System.out.println(name);
    }

}
