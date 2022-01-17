package edu.gdut.imis.courseMutualSelection.utils;


import edu.gdut.imis.courseMutualSelection.dao.pojo.User;

/**
 * @author miemiehoho
 * @date 2021/11/18 19:44
 */

public class UserThreadLocal {

    private UserThreadLocal() {
    }// 设置为私有的，不能new

    // 线程变量隔离
    private static final ThreadLocal<User> LOCAL = new ThreadLocal<>();

    public static void put(User user) {
        LOCAL.set(user);
    }

    public static User get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }

}
