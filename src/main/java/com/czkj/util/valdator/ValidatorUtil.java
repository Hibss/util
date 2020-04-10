package com.czkj.util.valdator;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验工具
 * @Author steven.sheng
 * @Date 2020/4/10/01010:25
 */
public class ValidatorUtil {
    private static final Pattern mobile_pattern =
            Pattern.compile("1\\d{10}");
    private static final Pattern money_pattern =
            Pattern.compile("^([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(.[0-9]{1,2})?$");
    private static final Pattern chinese_pattern =
            Pattern.compile("/^[\\u4E00-\\u9FA5]+$/");
    private static final Pattern phone_pattern =
            Pattern.compile("^((\\d{3,4}-)|\\d{3,4})?\\d{7,8}$");
    private static final Pattern email_pattern =
            Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
    private static final Pattern ip_pattern =
            Pattern.compile("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)");
    private static final Pattern url_pattern =
            Pattern.compile("/^(?=^.{3,255}$)(http(s)?:\\/\\/)?(www\\.)?[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+(:\\d+)*(\\/\\w+\\.\\w+)*([\\?&]\\w+=\\w*)*$/");

    /**
     * 验证手机号码
     * @param src
     * @return
     */
    public static boolean checkMobile(String src) {
        if (StringUtils.isEmpty(src)) {
            return false;
        }
        Matcher m = mobile_pattern.matcher(src);
        return m.matches();
    }

    /**
     * 验证邮箱格式
     * @param value
     * @return
     */
    public static boolean checkEmail(String value){
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        Matcher m = email_pattern.matcher(value);
        return m.matches();
    }

    /**
     * 验证ip格式
     * @param value
     * @return
     */
    public static boolean checkIp(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        Matcher m = ip_pattern.matcher(value);
        return m.matches();
    }

    /**
     * 验证url格式
     * @param value
     * @return
     */
    public static boolean checkUrl(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        Matcher m = url_pattern.matcher(value);
        return m.matches();
    }

    /**
     * 验证汉字格式
     * @param value
     * @return
     */
    public static boolean checkChinese(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        Matcher m = chinese_pattern.matcher(value);
        return m.matches();
    }

    /**
     * 验证电话号码格式
     * @param value
     * @return
     */
    public static boolean checkPhone(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        Matcher m = phone_pattern.matcher(value);
        return m.matches();
    }

    /**
     * 校验金额格式
     * @param value
     * @return
     */
    public static boolean checkMoney(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        Matcher m = money_pattern.matcher(value);
        return m.matches();
    }
}
