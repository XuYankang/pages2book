package top.desert2ocean.pages2book.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class UrlUtilsTest {
    @Test
    public void testGetUrlDepth() {
        String baseUrl = "https://docs.gradle.org/current/userguide";
        String url = "https://docs.gradle.org/current/userguide/userguide.html";
        int urlDepth = UrlUtils.getUrlDepth(url, baseUrl);
        log.info("{}", urlDepth);
        assert true;
    }

}
