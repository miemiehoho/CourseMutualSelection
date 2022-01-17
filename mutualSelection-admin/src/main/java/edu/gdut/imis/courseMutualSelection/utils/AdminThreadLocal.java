package edu.gdut.imis.courseMutualSelection.utils;


import edu.gdut.imis.courseMutualSelection.dao.pojo.Admin;

/**
 * @author miemiehoho
 * @date 2021/11/18 19:44
 */

public class AdminThreadLocal {

    private AdminThreadLocal() {
    }// 设置为私有的，不能new

    // 线程变量隔离
    private static final ThreadLocal<Admin> LOCAL = new ThreadLocal<>();

    public static void put(Admin admin) {
        LOCAL.set(admin);
    }

    public static Admin get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }

}
