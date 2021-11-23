package edu.gdut.imis.courseMutualSelection.service.impl;

import org.apache.commons.codec.digest.DigestUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author miemiehoho
 * @date 2021/11/23 23:26
 */
class LoginServiceImplTest {

    @org.junit.jupiter.api.Test
    void login() {
        String slat = "miemiehoho!@#";// 加密盐
        String password = "root";
        password = DigestUtils.md5Hex(password + slat);
        System.out.println(password);
    }
}