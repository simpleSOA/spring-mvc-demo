package com.hengtu.weixin.service;

import com.hengtu.weixin.vo.WeixinTicket;
import com.hengtu.weixin.vo.WeixinToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Service
public class WeixinSignatureService {
    private final ObjectMapper mapper = new ObjectMapper();

    private static Client client;
    static {
        ClientConfig cc = new DefaultClientConfig();
        cc.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT,1000*5);
        cc.getProperties().put(ClientConfig.PROPERTY_READ_TIMEOUT,1000*5);

        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, null, null);
        } catch (NoSuchAlgorithmException e1) {
            throw new RuntimeException(e1);
        }  catch (KeyManagementException e2) {
            e2.printStackTrace();
        }
        cc.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(
                new HostnameVerifier() {
                    @Override
                    public boolean verify( String s, SSLSession sslSession ) {
                        return true;
                    }
                },sslcontext
        ));

        cc.getClasses().add(ObjectMapperProvider.class);
        client =  Client.create(cc);
    }

    public WeixinToken getSignatrue(){
        String url = "https://api.weixin.qq.com/cgi-bin/token?" +
                "grant_type=client_credential&appid=wx8cd997b34bbca4d4&secret=13f54a3a7cc9f5802ffe3c2a6dc73f4f";

        ClientResponse res = client.resource(url)
                .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .accept("application/json")
                .get(ClientResponse.class);

        String responseString = "";
        try {
            responseString = res.getEntity(String.class);
            int statusCode = res.getClientResponseStatus().getStatusCode();
            if (statusCode == HttpServletResponse.SC_OK) {
                return  mapper.readValue(responseString, WeixinToken.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public WeixinTicket getByWeixinToken(WeixinToken token){
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+token.getAccess_token()+"&type=jsapi";

        ClientResponse res = client.resource(url)
                .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .accept("application/json")
                .get(ClientResponse.class);

        String responseString = "";
        try {
            responseString = res.getEntity(String.class);
            int statusCode = res.getClientResponseStatus().getStatusCode();
            if (statusCode == HttpServletResponse.SC_OK) {
                return  mapper.readValue(responseString, WeixinTicket.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
