package com.nowcoder.community.community;

import java.io.IOException;

public class WkTests {

    public static void main(String[] args) {
        String cmd = "d:/softwareInstall/wkhtmltopdf/bin/wkhtmltoimage --quality 75  https://www.nowcoder.com d:/code/data/wk-images/4.png";
        try {
            //java的这个与操作系统是并发的
            //所以我们会看到先输出的ok,然后才是输出的图片
            Runtime.getRuntime().exec(cmd);
            System.out.println("ok.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
