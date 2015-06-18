package com.hengtu.weixin.vo;

public class WeixinToken {

    private String access_token;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WeixinToken{");
        sb.append("access_token='").append(access_token).append('\'');
        sb.append(", expires_in=").append(expires_in);
        sb.append('}');
        return sb.toString();
    }
}
