package top.desert2ocean.pages2book.core.utils;

import org.springframework.util.StringUtils;

public class UrlUtils {

    public static final String SLASH = "/";

    public static int getUrlDepth(String url, String baseUrl) {
        //去掉baseURL最后一个斜杠和URL最后的斜杠
        url = eliminateLastSlash(url);
        baseUrl = eliminateLastSlash(baseUrl);
        if (url.equals(baseUrl)) {
            return 0;
        }
        boolean startsWith = url.startsWith(baseUrl);
        if (startsWith) {
            String extraPart = url.substring(baseUrl.length() + 1);
            int depth = 1;
            for (char c : extraPart.toCharArray()) {
                if (c == '/') {
                    depth++;
                }
            }
            return depth;
        }
        return -1;
    }


    private static String eliminateLastSlash(String url) {
        if (StringUtils.isEmpty(url)) {
            return "";
        }
        if (url.endsWith(SLASH)) {
            return url.substring(0, url.length() - 1);
        }
        return url;
    }

}
