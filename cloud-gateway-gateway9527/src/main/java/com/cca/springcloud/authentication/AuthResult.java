package com.cca.springcloud.authentication;

/**
 * @author cca
 * @date 2020/9/11 17:45
 */
public class AuthResult {

    private boolean success;

    private String code;

    private String message;

    public static AuthResult success(){
        AuthResult result = new AuthResult();
        result.setSuccess(true);
        result.setCode("200");
        return result;
    }

    public static AuthResult failed(String msg){
        AuthResult result = new AuthResult();
        result.setSuccess(false);
        result.setMessage(msg);
        result.setCode("400");
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
