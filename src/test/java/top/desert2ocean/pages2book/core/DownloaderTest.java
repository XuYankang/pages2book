package top.desert2ocean.pages2book.core;

import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class DownloaderTest {

    private final String startUrl = "https://docs.docker.com/";

    @Test
    public void testJSoup() throws IOException {
        String body = Jsoup.connect(startUrl).execute().body();
        log.info("{}", body);
    }

    @Test
    public void testRequests() {
        String text = Requests.get(startUrl).send().readToText();
        log.info("{}", text);
    }

}
