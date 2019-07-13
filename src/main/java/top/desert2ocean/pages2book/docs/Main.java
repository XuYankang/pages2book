package top.desert2ocean.pages2book.docs;

import top.desert2ocean.pages2book.core.config.DivSection;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

public class Main {

    public static void main(String[] args) {
        Pipeline pipeline = new DocsFilePipeline("pages");
        String startUrl = "https://docs.gradle.org/current/userguide/userguide.html";
        DocsConfig config = new DocsConfig();
        config.setBaseUrl("https://docs.gradle.org/current/userguide/");
        config.setCatalog(new DivSection("docs-navigation"));
        config.setContent(new DivSection("chapter"));
        Spider.create(new DocPageProcessor(config))
                .addUrl(startUrl)
                .addPipeline(pipeline)
                .setScheduler(new FileCacheQueueScheduler("pages"))
                .thread(1).run();
    }
}
