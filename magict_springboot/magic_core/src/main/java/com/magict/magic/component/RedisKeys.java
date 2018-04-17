package com.magict.magic.component;
/**
 * @author ZP
 * @date 2018/4/1323:40
 * @description:
 */
public class RedisKeys {

    public static final char KEY_WORD_DELIMITER_CHAR = ':';
    private static final String NAMESPACE_PREFIX = "ktao:";

    /**
     * 购物车key前缀 (全部key为 ktao:cart:accountid:storeid:userid)
     */
    public static final String CART_PREFIX = NAMESPACE_PREFIX + "cart:";

    /**
     * 验证码key前缀 (全部key为 ktao:code:TYPE:userid)
     */
    public static final String CODE_PREFIX = NAMESPACE_PREFIX + "code:";

    /**
     * 共享参数类key前缀
     */
    private static final String SHARE_NAMESPACE_PREFIX = "share:";

    /**
     * 实时数据类key前缀
     */
    private static final String REALTIME_NAMESPACE_PREFIX = "realtime:";

    /**
     * 通知类key前缀
     */
    private static final String NOTICE_NAMESPACE_PREFIX = "notice:";

    /**
     * 房态前缀
     */
    public static final String ROOM_NAMESPACE_PREFIX = "room:roomstatus:";

    /**
     * 取后面7天的订房日期
     */
    public static final String AFTER_SEVEN_DAYS = NAMESPACE_PREFIX + SHARE_NAMESPACE_PREFIX + "getSevenAfterDay";

    /**
     * 未支付订单
     */
    public static final String WAIT_PAY_ORDER = NAMESPACE_PREFIX + REALTIME_NAMESPACE_PREFIX + "waitPayOrder";

    /**
     * 前台待处理通知
     */
    public static final String NOTICE_MANAGEMENT_PENDING = NAMESPACE_PREFIX + NOTICE_NAMESPACE_PREFIX
            + "management:pending";

    /**
     * 待处理通知数量
     */
    public static final String NOTICE_MANAGEMENT_COUNT = NAMESPACE_PREFIX + NOTICE_NAMESPACE_PREFIX
            + "management:count";

    /**
     * 催单通知流量控制
     */
    public static final String NOTICE_MANAGEMENT_CONTROL_PRESS = NAMESPACE_PREFIX + NOTICE_NAMESPACE_PREFIX
            + "management:control:press";

    /**
     * 存酒通知流量控制
     */
    public static final String NOTICE_MANAGEMENT_CONTROL_KEEPWINE = NAMESPACE_PREFIX + NOTICE_NAMESPACE_PREFIX
            + "management:control:keepwine";

    /**
     * 取酒通知流量控制
     */
    public static final String NOTICE_MANAGEMENT_CONTROL_TAKEWINE = NAMESPACE_PREFIX + NOTICE_NAMESPACE_PREFIX
            + "management:control:takewine";

    /**
     * 绑定手机验证码
     */
    public static final String CODE_BINDMOBILE = CODE_PREFIX + "bindMobile:";

    /**
     * access token
     */
    public static final String AUTHORIZER_ACCESS_TOKEN = NAMESPACE_PREFIX + REALTIME_NAMESPACE_PREFIX + "accessToken";

    /**
     * 一键呼叫服务流量控制
     */
    public static final String NOTICE_MANAGEMENT_CONTROL_SERVICE = NAMESPACE_PREFIX + NOTICE_NAMESPACE_PREFIX
            + "management:control:service";
    /**
     * 剩余房间数量+accountId+storeId+roomcategoryId+roomTimeId+8位日期
     * 有效期8天
     */
    public static final String ROOM_REMINDER = NAMESPACE_PREFIX
            + "room:reminder";


    /**
     * 存酒通知缓存
     * key后加accountid:storeid：localorderid
     * 类型string 存储 userid
     * 1天过期
     */
    public static final String NOTICE_MANAGEMENT_CACHE_KEEPWINE = NAMESPACE_PREFIX + NOTICE_NAMESPACE_PREFIX
            + "management:cache:keepwine";

    /**
     * 账务日期
     * 6点日切
     * 8位日期
     */
    public static final String CASHIER_DATE = NAMESPACE_PREFIX
            + "cashier:date";
    
    /**
     * 线下活动预览
     */
    public static final String ACTIVITY_PREVIEW = NAMESPACE_PREFIX + "activity:preview";

    /**
     * 用户是否已经赠送优惠券
     * 前缀后加userid
     */
    public static final String FOLLOW_GIFT_COUPON = NAMESPACE_PREFIX + "follow:gift:coupon";//+:userid


}

