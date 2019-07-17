package top.desert2ocean.pages2book.core.utils;

import net.dongliu.requests.Requests;

import java.util.Base64;

public class ResourceUtils {
    public static final String PATH_SEPERATOR = "/";

    public static String getUserDir() {
        String relativelyPath = System.getProperty("user.dir");
        return relativelyPath;
    }

    public static String encodeBytes2Base64String(byte[] data, String contentType) {

        String base64str = Base64.getEncoder().encodeToString(data);
        StringBuilder sb = new StringBuilder();
        sb.append("data:");
        sb.append(contentType);
        sb.append(";base64,");
        sb.append(base64str);

        return sb.toString();
    }

    public static String getFileType(String path) {
        int lastDot = path.lastIndexOf(".");

        if (lastDot > 0) {
            return path.substring(lastDot + 1);
        }
        return "";
    }

    public static byte[] getStreamFromRemote(String url) {
        return Requests.get(url).send().readToBytes();
    }

    public static String getImageStringFromRemote(String url) {
        byte[] streamFromRemote = getStreamFromRemote(url);
        String fileType = getFileType(url);
        return encodeBytes2Base64String(streamFromRemote, fileType);
    }
}
