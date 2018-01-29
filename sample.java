package cn.com.fengyun.mc.services;

import cn.com.fengyun.util.Convert;
import cn.com.fengyun.util.WebOkHttpClient;
import okhttp3.Response;
import org.apache.commons.lang.StringUtils;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by fengyun on 2017/6/23.
 */
public class McApi4Customer {


    static String SIGN_METHOD = "HmacSHA256";
    static String SIGN_VERSION = "1";
    static String appId = "";
    static String appSecret = "";

    static String baseUrl = "http://report.socialmaster.com.cn/api";
    String method = "post";
    String query = "";
    String uri = "/session";

    public static void main(String[] args) throws Exception {

        McApi4Customer api = new McApi4Customer();
        String data = "";

       System.out.println(api.getSession());
    }

    public String getSession() throws Exception {
        query = "";
        uri = "/session";

        return this.get().body().string();
    }







    private Response get() throws Exception {
        method = "get";
        String url = baseUrl + uri;
        if (!StringUtils.isEmpty(query))
            url = url + "?" + query;
        System.out.println(url);
        return WebOkHttpClient.get(url, this.getHeader());
    }

    private Response post(String body) throws Exception {
        method = "post";
        String url = baseUrl + uri;
        if (!StringUtils.isEmpty(query))
            url = url + "?" + query;

        return WebOkHttpClient.post(url, this.getHeader(), body);
    }

    private Response patch(String body) throws Exception {
        method = "patch";
        String url = baseUrl + uri;
        if (!StringUtils.isEmpty(query))
            url = url + "?" + query;

        return WebOkHttpClient.patch(url, this.getHeader(), body);
    }


    private Response delete() throws Exception {
        method = "delete";
        String url = baseUrl + uri;
        if (!StringUtils.isEmpty(query))
            url = url + "?" + query;
        System.out.println(url);
        return WebOkHttpClient.delete(url, this.getHeader());
    }


    private Map<String, String> getHeader() throws Exception {
        String timestamp = Convert.getDateString(new Date(), "yyyy-MM-dd'T'HH:mm:ssZ");

        String methodUpper = method.toUpperCase();
        String[] reqInfos = {
                methodUpper,
                uri,
                appId,
                timestamp,
                SIGN_METHOD,
                SIGN_VERSION,
                query
        };



        String reqInfo = String.join("\n", reqInfos);

        String sign = HmacUtils.hmacSha256(appSecret, reqInfo);

        Map<String, String> headers = new HashMap();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        headers.put("Cache-Control", "no-cache");
        headers.put("X-Auth-Signature", sign.trim());
        headers.put("X-Auth-Key", appId);
        headers.put("X-Auth-Timestamp", timestamp);
        headers.put("X-Auth-Sign-Method", SIGN_METHOD);
        headers.put("X-Auth-Sign-Version", SIGN_VERSION);
        return headers;
    }
}
