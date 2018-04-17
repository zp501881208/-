package com.magict.magic.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HTTP 请求工具类
 * @author ZP
 * @date 2018-4-14 14:34:04
 */
public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig poolRequestConfig;
    private static RequestConfig requestConfig;
    private static CloseableHttpClient httpClient;

    //默认编码
    public static String DEFAULT_CHARSET = UtilConst.CHARSET;
    //请求超时2秒
    private static final int CONNECTION_TIMEOUT = 2000;
    //读取超时2秒
    private static final int SOCKET_TIMEOUT = 2000;
    //从连接池获取连接实例的超时500毫秒
    private static final int POOL_TIMEOUT = 500;
    //连接池大小
    private static final int MAX_TOTAL = 200;
    //路由连接数大小
    private static final int MAX_PER_ROUTE = 20;

    //不使用连接池配置
    //请求超时5秒
    private static final int CONN_TIMEOUT = 5000;
    //读取超时5秒
    private static final int ST_TIMEOUT = 5000;

    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(MAX_TOTAL);
        // 设置每个路由连接数大小
        connMgr.setDefaultMaxPerRoute(MAX_PER_ROUTE);

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(CONNECTION_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(SOCKET_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(POOL_TIMEOUT);
        poolRequestConfig = configBuilder.build();
        //使用默认连接池默认请求设置创建客户端
        httpClient = HttpClients.custom().setConnectionManager(connMgr).setDefaultRequestConfig(poolRequestConfig).build();

        //不使用连接池请求配置
        requestConfig = RequestConfig.custom().setSocketTimeout(ST_TIMEOUT).setConnectTimeout(CONN_TIMEOUT).build();
    }

    /**
     * 发送GET请求(http,https单向),不带输入数据
     * @param url 路径
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, new HashMap<String, String>());
    }

    /**
     * 发送GET请求(http,https单向),K-V形式
     * @param url 路径
     * @param params map参数
     * @return
     */
    public static String doGet(String url, Map<String, String> params) {
        url += RequestUtil.mapToUrl(params);
        String result = null;
        logger.info("GET url:{}",url);
        try {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            logger.info("response code :{} ",statusCode);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity,DEFAULT_CHARSET);
                logger.debug("response content:{}",result);
            }
        } catch (IOException e) {
            logger.error("doget error!", e);
        }
        return result;
    }

    /**
     * 发送POST请求(http,https单向),不带输入数据
     * @param url 路径
     * @return
     */
    public static String doPost(String url) throws UnsupportedEncodingException {
        return doPost(url, new HashMap<String, String>());
    }

    /**
     * 发送POST请求(http,https单向),httpPost对象参数
     * @param httpClient 请求客户端
     * @param httpPost post请求参数
     * @return
     */
    public static String doPost(HttpClient httpClient,HttpPost httpPost) {
        logger.info("POST url:{};params:{}",httpPost.getURI());
        String result = null;
        try {
            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            logger.info("response code :{} ",statusCode);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, DEFAULT_CHARSET);
                logger.debug("response content:{}",result);
            }
        } catch (IOException e) {
            logger.error("dopost error!", e);
        }
        return result;
    }

    /**
     * 发送POST请求(http,https单向),K-V形式
     * @param url 路径
     * @param params map参数
     * @return
     */
    public static String doPost(String url, Map<String, String> params) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> pairList = new ArrayList<>(params.size());
        for (Map.Entry<String, String> entry : params.entrySet()) {
            NameValuePair pair = new BasicNameValuePair(entry.getKey(),
                    entry.getValue());
            pairList.add(pair);
        }
        httpPost.setEntity(new UrlEncodedFormEntity(pairList, DEFAULT_CHARSET));
        return doPost(httpClient,httpPost);
    }

    /**
     * 发送POST请求(http,https单向)
     * @param url 路径
     * @param json json字符串
     * @return
     */
    public static String doPostJson(String url, String json) {
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(json, DEFAULT_CHARSET);// 解决中文乱码问题
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        return doPost(httpClient,httpPost);
    }

    /**
     * 发送POST请求(http,https单向)
     * @param url 路径
     * @param xml xml字符串
     * @return
     */
    public static String doPostXml(String url, String xml) {
        return doPost(httpClient,xmlHttpPost(url, xml));
    }

    /**
     * 获取PKCS12双向认证客户端
     * @param certPath cert文件路径(classpath下)
     * @param pwd 密码
     * @return
     * @throws Exception
     */
    public static HttpClient httpClientPKCS12(String certPath, String pwd) throws Exception {
        char[] password = pwd.toCharArray();
        HttpClient httpClient;
        FileInputStream instream = null;
		try {
			// 证书
			instream = new FileInputStream(HttpUtil.class.getClassLoader().getResource(certPath).getPath());
			logger.info("{} cert文件加载成功.",certPath);
			KeyStore ks = KeyStore.getInstance("PKCS12");
			ks.load(instream, password);

			// 实例化密钥库&初始化密钥工厂
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(ks, password);

			// 创建 SSLContext
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());

			SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
			        sslContext,new String[]{"TLSv1"},null,new DefaultHostnameVerifier());

			httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory)
					.setDefaultRequestConfig(requestConfig).build();
		} catch (Exception e) {
			logger.error("双向认证client创建失败.",e);
			throw e;
		}finally{
			if(instream!=null) instream.close();
		}

        logger.info("双向认证client创建成功.");
        return httpClient;
    }

    /**
     * 发送POST请求(https双向)
     * @param url 路径
     * @param xml xml字符串
     * @param certPath 证书路径
     * @param pwd 密码
     * @return
     */
    public static String doPostXmlPKCS12(String url,String xml,String certPath,String pwd) throws Exception {
        return doPost(httpClientPKCS12(certPath,pwd),xmlHttpPost(url,xml));
    }

    /**
     * 生成xml类型HttpPost
     * @param url 路径
     * @param xml xml字符串
     * @return
     */
    public static HttpPost xmlHttpPost(String url, String xml){
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(xml, DEFAULT_CHARSET);
        stringEntity.setContentType("text/xml");
        httpPost.setEntity(stringEntity);
        return httpPost;
    }
}