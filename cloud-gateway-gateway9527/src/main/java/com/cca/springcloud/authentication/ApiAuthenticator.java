package com.cca.springcloud.authentication;

/**
 * @author cca
 * @date 2020/9/11 15:07
 */
public interface ApiAuthenticator {

    AuthResult auth(String url);

    AuthResult auth(ApiRequest apiRequest);

}
