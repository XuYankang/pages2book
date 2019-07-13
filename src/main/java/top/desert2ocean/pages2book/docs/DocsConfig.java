package top.desert2ocean.pages2book.docs;

import lombok.Data;
import top.desert2ocean.pages2book.core.config.DivSection;

@Data
public class DocsConfig {
    /**
     * 一定要斜杠结尾
     */
    private String baseUrl;
    /**
     * 是否包含在startPage内
     */
    private boolean urlRestriction = true;
    private DivSection catalog;
    private DivSection content;
    /**
     * 这里的depth是针对从startPage而言的最大爬取层次
     */
    private int depth = 2;
}
