package top.desert2ocean.pages2book.core.config;

import lombok.Builder;
import lombok.Data;
import top.desert2ocean.pages2book.core.utils.ResourceUtils;

import java.net.MalformedURLException;
import java.net.URL;

@Data
@Builder
public class ImageUrl {
    private String base;
    private String src;

    public URL getUrl() throws MalformedURLException {
        URL baseUrl = new URL(base);
        URL targetUrl = new URL(baseUrl, src);
        return targetUrl;
    }

    public String toString() {
        try {
            return getUrl().toString();
        } catch (MalformedURLException e) {
            return base + ResourceUtils.PATH_SEPERATOR + src;
        }
    }
}
