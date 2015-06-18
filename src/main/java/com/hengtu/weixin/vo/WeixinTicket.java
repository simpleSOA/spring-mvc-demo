package com.hengtu.weixin.vo;

/**
 * Created by ly on 15-6-17.
 */
public class WeixinTicket {

    private int errcode;
    private String errmsg;
    private String ticket;
    private int expires_in;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WeixinTicket{");
        sb.append("errcode=").append(errcode);
        sb.append(", errmsg='").append(errmsg).append('\'');
        sb.append(", ticket='").append(ticket).append('\'');
        sb.append(", expires_in=").append(expires_in);
        sb.append('}');
        return sb.toString();
    }
}
