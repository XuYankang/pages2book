package top.desert2ocean.pages2book.docs;

import top.desert2ocean.pages2book.core.config.DivSection;
import top.desert2ocean.pages2book.core.downloader.RequestsDownloader;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.downloader.PhantomJSDownloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

public class Main {

    public static void main(String[] args) {
        System.setProperty("https.protocols", "TLSv1.2");
        kotlinDocs();
    }

    private static void gradleDocs() {
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
                .thread(2).run();
    }

    private static void kotlinDocs() {
        Pipeline pipeline = new DocsFilePipeline("pages");
        String startUrl = "https://kotlinlang.org/docs/tutorials/";
        DocsConfig config = new DocsConfig();
        config.setBaseUrl("https://kotlinlang.org/docs/tutorials/");
        config.setCatalog(new DivSection("js-side-tree-nav"));
        config.setContent(new DivSection("page-content"));
        Spider.create(new DocPageProcessor(config))
                .addUrl(startUrl)
                .addPipeline(pipeline)
                .setDownloader(new RequestsDownloader())
                .setScheduler(new FileCacheQueueScheduler("pages"))
                .thread(2).run();
    }
}
