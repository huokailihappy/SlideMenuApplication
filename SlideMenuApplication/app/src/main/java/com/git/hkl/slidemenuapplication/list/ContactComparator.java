package com.git.hkl.slidemenuapplication.list;

import java.util.Comparator;

/**
 * 项目名称：SlideMenuApplication
 * 类描述：
 * 创建人：霍凯丽
 * 创建时间：2016/11/30 10:38
 */
public class ContactComparator implements Comparator<String> {

    /**
     * 这里取得前两位做到比较,如有需要可以自己更改
     * 比较对象包括数字和字母
     */
    @Override
    public int compare(String o1, String o2) {
        int c1 = (o1.charAt(0) + "").toUpperCase().hashCode();
        int c11 = '#', c21 = '#';
        if (o1.length() > 1) {
            c11 = (o1.charAt(1) + "").toUpperCase().hashCode();
        }
        int c2 = (o2.charAt(0) + "").toUpperCase().hashCode();
        if (o1.length() > 1) {
            c21 = (o2.charAt(1) + "").toUpperCase().hashCode();
        }

        boolean c1Flag = notChara(c1); // 不是字母
        boolean c2Flag = notChara(c2); // 不是字母
        if (c1Flag && !c2Flag) {
            return 1;
        } else if (!c1Flag && c2Flag) {
            return -1;
        }
        if (c1 == c2) {
            return c11 - c21;
        } else {
            return c1 - c2;
        }
    }

    // true不是字母数字
    public static boolean notChara(int c1) {
        return (c1 < "0".hashCode() || (c1 > "9".hashCode() && c1 < "A".hashCode())
                || c1 > "Z".hashCode());
    }

    // true是字母数字
    public static boolean isChara(int c1) {
        return ((c1 >= "0".hashCode() && c1 <= "9".hashCode()) || (c1 >= "A".hashCode() && c1 <= "Z".hashCode()));
    }


}