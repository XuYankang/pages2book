package top.desert2ocean.pages2book.core.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageUrl {
    private String base;
    private String src;
}
