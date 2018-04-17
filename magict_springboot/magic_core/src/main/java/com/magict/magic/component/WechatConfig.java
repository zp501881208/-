package com.magict.magic.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * 微信配置
 * @date 2018-4-14 13:47:17
 * @author ZP
 */
@Component
public class WechatConfig {
    /**
     * 微信应用ID
     */
    public static String appID;

    /**
     * 微信API密钥
     */
    public static String appsecret;

    /**
     * 支付密钥
     */
    public static String key;

    /**
     * 微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
     */
    public static String mchId;

    /**
     * HTTPS证书的本地路径
     */
    public static String certPath;

    /**
     * HTTPS证书密码，默认密码等于商户号MCHID
     */
    public static String certPassword;

    /**
     * 1）支付API
     */
    public static String payApi;

    /**
     * 2）支付查询API
     */
    public static String payQueryApi;

    /**
     * 3）退款API
     */
    public static String refundApi;

    /**
     * 4）退款查询API
     */
    public static String refundQueryApi;

    /**
     * 5）下载对账单API
     */
    public static String downloadBillApi;

    /**
     * 6）关闭订单API
     */
    public static String closeApi;

    /**
     * 7）转账API
     */
    public static String transfersApi = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";

    /**
     * 8）转账查询API
     */
    public static String transfersQueryApi = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo";

    /**
     * 支付回调
     */
    public static String notifyUrl;

    /**
     * 交易类型
     */
    public static String tradeType = "JSAPI";

    /**
     * 签名类型
     */
    public static String signType = "MD5";

    /**
     * 签名字段
     */
    public static String fieldSign = "sign";

    /**
     * 编码方式
     */
    public static String charset = "utf-8";

    /**
     * 成功
     */
    public static String SUCCESS = "SUCCESS";

    /**
     * 失败
     */
    public static String FAIL = "FAIL";

    /**
     * 最大轮询查询重复次数
     */
    public static int maxQueryRetry = 3;

    /**
     * 轮询时间间隔
     */
    public static int queryDuration = 2000;

    
    /**
     * 微信模板消息请求API
     */
    public static String sendApi;
    
    /**
     * 微信1号消息模板
     */
    public static String templateid1;
    
    
    public static String getTemplateid1() {
		return templateid1;
	}
    
	@Value("${wxmsg.templateid1}")
	public void setTemplateid1(String templateid1) {
		WechatConfig.templateid1 = templateid1;
	}

	public static String getSendApi() {
		return sendApi;
	}

	@Value("${wxmsg.sendApi}")
	public void setSendApi(String sendApi) {
		WechatConfig.sendApi = sendApi;
	}

	@Value("${wechat.appID}")
    public void setAppID(String appID) {
        WechatConfig.appID = appID;
    }

    @Value("${wechat.appsecret}")
    public void setAppsecret(String appsecret) {
        WechatConfig.appsecret = appsecret;
    }

    @Value("${wxpay.key}")
    public void setKey(String key) {
        WechatConfig.key = key;
    }

    @Value("${wxpay.mchId}")
    public void setMchId(String mchId) {
        WechatConfig.mchId = mchId;
    }

    @Value("${wxpay.certPath}")
    public void setCertPath(String certPath) {
        WechatConfig.certPath = certPath;
    }

    @Value("${wxpay.certPassword}")
    public void setCertPassword(String certPassword) {
        WechatConfig.certPassword = certPassword;
    }

    @Value("${wxpay.payApi}")
    public void setPayApi(String payApi) {
        WechatConfig.payApi = payApi;
    }

    @Value("${wxpay.payQueryApi}")
    public void setPayQueryApi(String payQueryApi) {
        WechatConfig.payQueryApi = payQueryApi;
    }

    @Value("${wxpay.refundApi}")
    public void setRefundApi(String refundApi) {
        WechatConfig.refundApi = refundApi;
    }

    @Value("${wxpay.refundQueryApi}")
    public void setRefundQueryApi(String refundQueryApi) {
        WechatConfig.refundQueryApi = refundQueryApi;
    }

    @Value("${wxpay.downloadBillApi}")
    public void setDownloadBillApi(String downloadBillApi) {
        WechatConfig.downloadBillApi = downloadBillApi;
    }

    @Value("${wxpay.closeApi}")
    public void setCloseApi(String closeApi) {
        WechatConfig.closeApi = closeApi;
    }

    public void setTransfersApi(String transfersApi) {
        WechatConfig.transfersApi = transfersApi;
    }

    public void setTransfersQueryApi(String transfersQueryApi) {
        WechatConfig.transfersQueryApi = transfersQueryApi;
    }

    public void setTradeType(String tradeType) {
        WechatConfig.tradeType = tradeType;
    }

    public void setSignType(String signType) {
        WechatConfig.signType = signType;
    }

    public void setCharset(String charset) {
        WechatConfig.charset = charset;
    }

    public void setMaxQueryRetry(int maxQueryRetry) {
        WechatConfig.maxQueryRetry = maxQueryRetry;
    }

    public void setQueryDuration(int queryDuration) {
        WechatConfig.queryDuration = queryDuration;
    }

    public void setFieldSign(String fieldSign) {
        WechatConfig.fieldSign = fieldSign;
    }

    public void setSUCCESS(String SUCCESS) {
        WechatConfig.SUCCESS = SUCCESS;
    }

    public void setFAIL(String FAIL) {
        WechatConfig.FAIL = FAIL;
    }

    @Value("${wxpay.notifyUrl}")
    public void setNotifyUrl(String notifyUrl) {
        WechatConfig.notifyUrl = notifyUrl;
    }

    public String getAppID() {
        return appID;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public String getKey() {
        return key;
    }

    public String getMchId() {
        return mchId;
    }

    public String getCertPath() {
        return certPath;
    }

    public String getCertPassword() {
        return certPassword;
    }

    public String getPayApi() {
        return payApi;
    }

    public String getPayQueryApi() {
        return payQueryApi;
    }

    public String getRefundApi() {
        return refundApi;
    }

    public String getRefundQueryApi() {
        return refundQueryApi;
    }

    public String getDownloadBillApi() {
        return downloadBillApi;
    }

    public String getCloseApi() {
        return closeApi;
    }

    public String getTransfersApi() {
        return transfersApi;
    }

    public String getTransfersQueryApi() {
        return transfersQueryApi;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public String getSignType() {
        return signType;
    }

    public String getFieldSign() {
        return fieldSign;
    }

    public String getCharset() {
        return charset;
    }

    public String getSUCCESS() {
        return SUCCESS;
    }

    public String getFAIL() {
        return FAIL;
    }

    public int getMaxQueryRetry() {
        return maxQueryRetry;
    }

    public int getQueryDuration() {
        return queryDuration;
    }
}
