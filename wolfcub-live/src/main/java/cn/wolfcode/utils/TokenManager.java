package cn.wolfcode.utils;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.vo.LoginInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * token 管理类
 * TODO 超时管理机制
 */
abstract public class TokenManager {

    public static final String TOKEN_NAME = "X-Token";

    // ConcurrentHashMap 保障共享资源线程安全问题
    private static final Map<String, LoginInfo> TOKEN_MAP = new ConcurrentHashMap<>();

    public static String getToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void setInfo(String token, LoginInfo employee) {
        // 并发访问 map
        TOKEN_MAP.put(token, employee);
    }

    public static LoginInfo getInfo(String token) {
        return TOKEN_MAP.get(token);
    }

    public static void removeInfo(String token) {
        TOKEN_MAP.remove(token);
    }
}
