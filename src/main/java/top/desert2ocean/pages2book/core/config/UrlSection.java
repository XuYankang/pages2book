package top.desert2ocean.pages2book.core.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UrlSection {
    private String url;
    private int depth;
    private String title;
    private UrlSection parent;
}
