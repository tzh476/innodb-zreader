package me.plash.util;

import me.plash.struct.page.Page;
import me.plash.struct.page.PageType;

public class Check {
    public static void checkPageType(Page page, Short targetPageType){
        Short type = page.fileHeader.FIL_PAGE_TYPE;
        if(type != targetPageType){
            System.out.println("页面类型读取错误,期望的页面类型：" + PageType.getValue(targetPageType)
                    + ",实际页面类型：" + PageType.getValue(type));
        }else {
            System.out.println("页面类型校验正确：" + PageType.getValue(targetPageType));
        }
    }
}
