package top.desert2ocean.pages2book.docs;

import top.desert2ocean.pages2book.core.config.DivSection;
import top.desert2ocean.pages2book.core.downloader.RequestsDownloader;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

public class Main {

    public static void main(String[] args) {
        gradleDocs();
    }

    private static void gradleDocs() {
        String startUrl = "https://docs.gradle.org/current/userguide/userguide.html";
        DocsConfig config = new DocsConfig();
        config.setBaseUrl("https://docs.gradle.org/current/userguide/");
        config.setCatalog(new DivSection("docs-navigation"));
        config.setContent(new DivSection("chapter"));
        config.setStartUrl(startUrl);
        startCrawler(config);
    }


    private static void kotlinDocs() {
        String startUrl = "https://kotlinlang.org/docs/tutorials/";
        DocsConfig config = new DocsConfig();
        config.setBaseUrl("https://kotlinlang.org/docs/tutorials/");
        config.setCatalog(new DivSection("js-side-tree-nav"));
        config.setContent(new DivSection("page-content"));
        config.setStartUrl(startUrl);
        startCrawler(config);
    }

    private static void startCrawler(DocsConfig config) {
        System.setProperty("https.protocols", "TLSv1.2");
        Pipeline pipeline = new DocsFilePipeline("pages");
        Spider.create(new DocPageProcessor(config))
                .addUrl(config.getStartUrl())
                .addPipeline(pipeline)
                .setDownloader(new RequestsDownloader())
                .setScheduler(new FileCacheQueueScheduler(config.getSaveDir()))
                .thread(2).run();
    }
}
