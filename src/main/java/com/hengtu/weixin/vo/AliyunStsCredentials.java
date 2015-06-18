package com.hengtu.weixin.vo;

import java.io.Serializable;

/**
 * 阿里云临时上传到oss需要的凭证
 */
public class AliyunStsCredentials implements Serializable {

    private String accessKeyId;
    private String accessKeySecret;
    private String expiration;
    private String securityToken;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getSecurityToken() {
        return securityToken;
    }

    public void setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AliyunStsCredentials{");
        sb.append("accessKeyId='").append(accessKeyId).append('\'');
        sb.append(", accessKeySecret='").append(accessKeySecret).append('\'');
        sb.append(", expiration='").append(expiration).append('\'');
        sb.append(", securityToken='").append(securityToken).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
