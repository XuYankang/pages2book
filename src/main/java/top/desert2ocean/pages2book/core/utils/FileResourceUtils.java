package top.desert2ocean.pages2book.core.utils;

public class FileResourceUtils {
    public static String PATH_SEPERATOR = "/";

    public static String getUserDir() {
        String relativelyPath = System.getProperty("user.dir");
        return relativelyPath;
    }

}
