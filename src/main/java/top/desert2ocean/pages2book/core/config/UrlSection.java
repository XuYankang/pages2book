package top.desert2ocean.pages2book.core.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UrlSection {
    private String url;
    private int depth;
    private String title;
    private int number;
    private UrlSection parent;

    public String getSerial() {
        String serial = number + " ";
        UrlSection p = parent;
        while (p != null) {
            serial = p.number + "." + serial;
            p = p.parent;
        }
        return serial;
    }
}
