package com.cca.springcloud.authentication.impl;


import com.cca.springcloud.authentication.CredentialStorage;

/**
 *
 * 根据appId获取用户secret
 *
 *
 * @author cca
 * @date 2020/9/11 15:06
 */
public class DefaultCredentialStorage implements CredentialStorage {

    @Override
    public String getSecretByAppId(String appId) {

        return "12345";
    }
}
