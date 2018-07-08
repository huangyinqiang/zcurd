//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.busi.model.ot;

import java.util.ResourceBundle;
import java.util.Set;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {
    private static JedisPool pool;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("redis");
        if (bundle == null) {
            throw new IllegalArgumentException("[redis.properties] is not found!");
        } else {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
            config.setMaxTotal(Integer.valueOf(bundle.getString("redis.pool.maxTotal")));
            config.setMaxWaitMillis((long)Integer.valueOf(bundle.getString("redis.pool.maxWait")));
            config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
            config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));
            pool = new JedisPool(config, bundle.getString("redis.ip"), Integer.valueOf(bundle.getString("redis.port")), 5000, bundle.getString("redis.auth"));
        }
    }

    public RedisUtils() {
    }

    public static void main(String[] args) {
        for(int i = 0; i < 1; ++i) {
            test();
        }

    }

    public static synchronized Jedis getJedis() {
        Jedis jedis = null;
        if (pool != null) {
            try {
                if (jedis == null) {
                    jedis = pool.getResource();
                }
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }

        return jedis;
    }

    public static synchronized void returnResource(Jedis jedis) {
        if (jedis != null) {
            pool.returnResource(jedis);
        }

    }

    public static synchronized void returnBrokenResource(Jedis jedis) {
        if (jedis != null) {
            pool.returnBrokenResource(jedis);
        }

    }

    private static void init() {
    }

    public static void test() {
        System.out.println(GetValue("device:356566071976435"));
    }

    public static String SetValue(String name, String value) {
        Jedis jedis = getJedis();
        String str = "";
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        } else {
            try {
                str = jedis.set(name, value);
            } catch (Exception var8) {
                returnBrokenResource(jedis);
            } finally {
                returnResource(jedis);
            }

            return str;
        }
    }

    public static void RemoveValue(String name) {
        Jedis jedis = getJedis();
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        } else {
            try {
                jedis.del(name);
            } catch (Exception var6) {
                returnBrokenResource(jedis);
            } finally {
                returnResource(jedis);
            }

        }
    }

    public static String GetValue(String key) {
        Jedis jedis = getJedis();
        String str = "";
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        } else {
            try {
                str = jedis.get(key);
            } catch (Exception var7) {
                returnBrokenResource(jedis);
            } finally {
                returnResource(jedis);
            }

            return str;
        }
    }

    public static Set<String> GetKeys(String key) {
        Jedis jedis = getJedis();
        Set<String> keys = null;
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        } else {
            try {
                keys = jedis.keys(key + "*");
            } catch (Exception var7) {
                returnBrokenResource(jedis);
            } finally {
                returnResource(jedis);
            }

            return keys;
        }
    }

    public static boolean exist(String key) {
        Jedis jedis = getJedis();
        boolean flag = false;
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        } else {
            try {
                flag = jedis.exists(key);
            } catch (Exception var7) {
                returnBrokenResource(jedis);
            } finally {
                returnResource(jedis);
            }

            return flag;
        }
    }

    public static void FlushDB() {
        Jedis jedis = getJedis();
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        } else {
            try {
                jedis.flushDB();
            } catch (Exception var5) {
                returnBrokenResource(jedis);
            } finally {
                returnResource(jedis);
            }

        }
    }

    public static void SaveDB() {
        Jedis jedis = getJedis();
        if (jedis == null) {
            throw new NullPointerException("Jedis is Null");
        } else {
            try {
                jedis.save();
            } catch (Exception var5) {
                returnBrokenResource(jedis);
            } finally {
                returnResource(jedis);
            }

        }
    }
}
