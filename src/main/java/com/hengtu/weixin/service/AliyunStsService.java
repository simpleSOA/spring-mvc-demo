package com.hengtu.weixin.service;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.GetFederationTokenRequest;
import com.aliyuncs.sts.model.v20150401.GetFederationTokenResponse;

public class AliyunStsService {

    public static final String REGION_CN_HANGZHOU = "cn-beijing";
    public static final String STS_API_VERSION = "2015-04-01";
    public static final String STS_VERSION = "1";
    public static final String POLICY = "{\"Version\":\"1\",\"Statement\":[{\"Action\":[\"oss:PutObject\"],\"Resource\":[\"acs:oss:*:302111126760:weixin-upload/*\"],\"Effect\":\"Allow\",\"Condition\":{\"StringEquals\":{\"acs:UserAgent\":\"java-sdk\",\"oss:Delimiter\":\"/\",\"oss:Prefix\":\"foo\"},\"IpAddress\":{\"acs:SourceIp\":\"0.0.0.0\"}}}]}";

    private static final String accessKeyId = "7vclL7XXXXXC5Py";
    private static final String accessKeySecret = "FLVJMKXXXXXBAUe93AFIud";



    private static IAcsClient client;

    static {
        IClientProfile profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, accessKeyId, accessKeySecret);
        client = new DefaultAcsClient(profile);
    }


    public static GetFederationTokenResponse getFederationToken(String name,long expireSeconds, ProtocolType protocolType)
            throws ClientException {
        final GetFederationTokenRequest request = new GetFederationTokenRequest();
        request.setVersion(STS_API_VERSION);
        request.setMethod(MethodType.POST);
        request.setProtocol(protocolType);
        request.setStsVersion(STS_VERSION);
        request.setName(name);
        request.setPolicy(POLICY);
        request.setDurationSeconds(expireSeconds);
        return client.getAcsResponse(request);
    }

    public static void main(String[] args) throws ClientException {
        String name = "test";
        long duration = 3600;
        ProtocolType protocolType = ProtocolType.HTTPS;
        final GetFederationTokenResponse response = getFederationToken(
                name, duration,protocolType);

        System.out.println("Expiration: " + response.getCredentials().getExpiration());
        System.out.println("Access Key Id: " + response.getCredentials().getAccessKeyId());
        System.out.println("Access Key Secret: " + response.getCredentials().getAccessKeySecret());
        System.out.println("Security Token: " + response.getCredentials().getSecurityToken());
    }
}
