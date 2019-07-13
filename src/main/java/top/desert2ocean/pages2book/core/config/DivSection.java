package top.desert2ocean.pages2book.core.config;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 默认是class，如果设置了isId则认为是ID
 */
@Data
@AllArgsConstructor
public class DivSection {
    private String name;
    private boolean isId;

    public DivSection(String name) {
        this.name = name;
        this.isId = false;
    }

    public String getCssQuery() {
        String cssQuery = name;
        if (isId) {
            cssQuery = "#" + cssQuery;
        } else {
            cssQuery = "." + cssQuery;
        }
        return cssQuery;
    }
}
