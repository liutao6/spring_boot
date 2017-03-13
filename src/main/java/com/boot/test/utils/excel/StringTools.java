package com.boot.test.utils.excel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tao
 * Date: 14-3-17
 * Time: 上午11:52
 * To change this template use File | Settings | File Templates.
 */
public class StringTools {
    /**
     * 任意格式转字符串
     *
     * @param obj
     * @return
     */
    public static String numToString(Object obj) {
        String str = "";
        if (isNotempty(obj)) {
            str = obj.toString().trim();
        }
        return str;
    }

    /**
     * 验证一个字符串或者数组是空
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            return list.size() == 0;
        } else {
            return obj.toString().trim().length() == 0;
        }
    }

    /**
     * 验证一个字符串或者数组不为空
     *
     * @param obj
     * @return
     */
    public static boolean isNotempty(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            return list.size() > 0;
        } else {
            return obj.toString().trim().length() > 0;
        }
    }


    /**
     * 获取分页语句
     */
    public static String getPagingSql(String paSql, int page, int pageSize) {
        StringBuffer sql = new StringBuffer("select * from( select ttt.*,rownum rn from( ");
        sql.append(paSql);
        int pages = ((page - 1) * pageSize);
        int pageSizes = page * pageSize;
        sql.append("  ) ttt where rownum<= ");
        sql.append(pageSizes);
        sql.append(" ) where rn > ");
        sql.append(pages);
        return sql.toString();
    }

    /**
     * 获取分页语句
     */
    public static String getPagingSqlTwo(String paSql, int page, int pageSize) {
        StringBuffer sql = new StringBuffer("select ttt.* from( ");
        sql.append(paSql);
        int pages = ((page - 1) * pageSize);
        int pageSizes = page * pageSize;
        sql.append("  ) ttt where  num BETWEEN ");
        sql.append(pages == 0 ? 0 : pages + 1);
        sql.append(" and ");
        sql.append(pageSizes);
        return sql.toString();
    }
}