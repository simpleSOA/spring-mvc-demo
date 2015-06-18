package com.hengtu.weixin.service;

import com.hengtu.weixin.vo.WeixinTicket;
import com.hengtu.weixin.vo.WeixinToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class WeixinSignatureServiceTest {

    @Autowired
    private WeixinSignatureService service;

    @Test
    public void test1(){
        System.out.println("+++++++++++++++");
        WeixinToken token = service.getSignatrue();
        System.out.println(token);
        WeixinTicket weixinTicket = service.getByWeixinToken(token);
        System.out.println(weixinTicket);
    }
}
