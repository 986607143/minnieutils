/**
 * luunbeloved.util.HttpClientUtils.java
 *
 * @author:Luun
 * @emile：583853240@qq.com
 * @date:2016-8-22下午3:21:00
 * @version 1.0
 */
package com.sunshine.utils.http;

import com.sunshine.utils.DateUtils;
import com.sunshine.utils.FileUtils;
import com.sunshine.utils.io.WriteFileUtils;
import com.sunshine.utils.lang.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Date;


/**
 * httpclinet重定向问题解决，见如下代码段</br>
 * <code>.setRedirectStrategy(new RedirectStrategy() {
 * public boolean isRedirected(HttpRequest arg0, HttpResponse arg1,
 * HttpContext arg2) throws ProtocolException {
 * return false;
 * }
 * <p>
 * public HttpUriRequest getRedirect(HttpRequest arg0, HttpResponse arg1,
 * HttpContext arg2) throws ProtocolException {
 * return null;
 * });
 * }</code>
 *
 * @author qiushengming
 */
public class HttpClientUtils {

    private static Logger logger = Logger.getLogger(HttpClientUtils.class);

    /**
     * isChangeIp为FALSE的时候不更新代理。
     *
     * @param url        链接地址
     * @param cookie
     * @param isChangeIp 是否更改代理
     * @return
     * @throws IOException
     */
    public static CloseableHttpResponse connection(String url, String cookie,
        Boolean isChangeIp) {

        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        RequestConfig config = null;
        HttpClientBuilder clientBuilder =
            HttpClients.custom().disableAutomaticRetries()
                .setRedirectStrategy(new RedirectStrategy() {

                    @Override
                    public boolean isRedirected(HttpRequest arg0,
                        HttpResponse arg1, HttpContext arg2)
                        throws ProtocolException {
                        return false;
                    }


                    @Override
                    public HttpUriRequest getRedirect(HttpRequest arg0,
                        HttpResponse arg1, HttpContext arg2)
                        throws ProtocolException {
                        return null;
                    }
                });

        Builder configBuilder = RequestConfig.custom();

        config =
            configBuilder.setSocketTimeout(2000 * 3).setConnectTimeout(2000 * 3)
                .build();
        client = clientBuilder.build();
        HttpPost get = new HttpPost(url);
        get.setConfig(config);

        try {
            response = client.execute(get);
        } catch (ClientProtocolException e) {
            logger.warn("重定向开始！！！");
        } catch (IOException e) {
            logger.error(e);
        }

        return response;
    }

    public static String httpGet(String url) {
        CloseableHttpClient client;
        CloseableHttpResponse response;
        HttpClientBuilder clientBuilder = HttpClients.custom();
        client = clientBuilder.build();
        HttpGet get = new HttpGet(url);
        String result = null;
        try {
            response = client.execute(get);
            if(!HttpClientUtils.hasResponse(response, "", "")){
                return "";
            }
            result = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return result;
    }

    /**
     * @param url 地址
     * @author qiushengming
     * @return HttpGet
     * @date 2017-2-4 下午3:14:06
     * <p>
     * Mozilla/5.0 (Windows NT 6.1; WOW64; rv:49.0)
     * Gecko/20100101 Firefox/49.0
     * </p>
     * <p>
     * 国家药监局的header头
     * </p>
     */
    public static HttpGet newGet(String url) {
        HttpGet get = new HttpGet(url);
        get.setHeader("Content-type", "text/html");
        get.setHeader("User-Agent",
            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50");
        get.setHeader("Accept",
            "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        get.setHeader("Accept-Encoding", "gzip, deflate");
        get.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        get.setHeader("Cache-Control", "max-age=0");
        get.setHeader("Connection", "keep-alive");
        // get.setHeader("Cookie",
        // "JSESSIONID=B66502624357C532275F7A94DFE6DDD6.7;
        // _gscu_1586185021=86111945zzpbzz72;
        // yunsuo_session_verify=96dc568d056173ae422e35279f6f298f");
        get.setHeader("Host", "app1.sfda.gov.cn");
        get.setHeader("Referer", url);
        get.setHeader("Upgrade-Insecure-Requests", "1");
        return get;
    }

    public static SSLContext createIgnoreVerifySSL()
        throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSLv3");

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                String paramString) throws CertificateException {
            }


            @Override
            public void checkServerTrusted(
                java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                String paramString) throws CertificateException {
            }


            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sc.init(null, new TrustManager[] {
            trustManager}, null);
        return sc;
    }


    /**
     * 2017年3月31日 qiushengming 验证当前的response是否有效
     *
     * @param response 验证信息载体
     * @param msg      消息
     * @param path     错误信息存储路径
     * @return Boolean true|false
     * @throws IOException Boolean
     */
    public static Boolean hasResponse(CloseableHttpResponse response,
        String msg, String path) throws IOException {

        if (StringUtils.isEmpty(path)) {
            path = "c:\\logs\\httpclient\\httpclient_error_" + DateUtils
                .format(new Date(), "yyyyMMdd") + ".log";
            FileUtils.createFile(path);
        }

        /* 当出错的时候，将出错的链接保存起来 */
        if (response == null || !(
            response.getStatusLine().getReasonPhrase().equals("OK") || (
                response.getStatusLine().getStatusCode() == 301))) {

            /*组装错误*/
            String sb = msg + "#" + DateUtils
                .formateDate(new Date(), "yyyy-MM-dd hh:mm:ss") + "\n";

            /*写入错误*/
            WriteFileUtils.writeFile(path, true, sb);

            return false;
        }
        return true;
    }

    public static String responseToString(CloseableHttpResponse response,
        String msg, String path) {
        /*监测请求是否有效，无效记录错误进入下一个循环*/
        try {
            if (hasResponse(response, msg, path)) {
                return EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
            logger.error(msg);
        }
        return "";
    }

    public static String getUrlHtmlToString(String url) {
        CloseableHttpResponse response = HttpClientUtils.connection(url, null, false);
        return responseToString(response, url, null);
    }
}
