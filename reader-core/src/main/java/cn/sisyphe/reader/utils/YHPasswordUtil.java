package cn.sisyphe.reader.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/20.
 */
public class YHPasswordUtil {
    /**
     * 对员工密码进行加密
     */
    static String encryptionForOperator(String strInput) {
        String strPwd = "";
        List<String> strList = new ArrayList<String>();
        // System.out.println("密码长度：\t" + strInput.length());
        // 计算
        for (int i = 0; i < strInput.length(); i++) {
            char cha = '0';
            cha = strInput.charAt(i);
            // charAt(int index)返回指定索引处的 char 值。索引范围为从 0 到 length() - 1。
            // System.out.println("index " + i + ":\t" + cha + "\t十进制:\t" +
            // (int) cha);
            // 依次遍历密码字符串，获取Dec(十进制)，对十进制加1变成明文
            int dec = cha + 1;// 字符转换为数值
            char char2 = '0';
            char2 = (char) dec;// 数值转换为字符
            // System.out.println("转化后char--------->:\t" + char2);
            strList.add("" + char2);// 添加到集合
        }
        // 当集合不为空
        if (strList.size() > 0) {
            // 将十进制转换为字符串
            for (String s : strList) {
                strPwd += s;
            }
        }
        // System.out.println("密码明文:\t" + strPwd);
        return strPwd;
    }

    /**
     * 对员工密码进行解密
     */
    public static String decryptionForOperator(String strInput) {

        String decryptionPwd = "";
        if (strInput != null & !strInput.trim().toString().equals("")) {

            // System.out.println("加密后密码:\t" + strInput.trim());//
            // 如果前包含YH,后面包含YH，去掉YH的头或者尾
            // 包含YH的
            if (strInput.startsWith("YH") && strInput.endsWith("YH")) {

                //System.out.println("截取首位字符串后:\t" + strInput.substring(2, strInput.length() - 2));
                String subEncryptionPwd = strInput.substring(2, strInput.length() - 2);// 去掉YH字符串操作
                decryptionPwd = encryptionForOperator(subEncryptionPwd.trim());//
                return decryptionPwd;
            }
            // 不包含YH的
            else {
                decryptionPwd = encryptionForOperator(strInput.trim());//
                return decryptionPwd;
            }
        }
        return decryptionPwd;

    }

}
