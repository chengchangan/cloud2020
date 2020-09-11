package com.cca.springcloud.authentication;

/**
 * @author cca
 * @date 2020/9/11 15:02
 */
public interface CredentialStorage {

    /**
     * 根据 appId 获取 secret
     */
    String getSecretByAppId(String appId);

}
