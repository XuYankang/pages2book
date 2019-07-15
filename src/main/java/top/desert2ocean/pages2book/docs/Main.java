package top.desert2ocean.pages2book.docs;

import lombok.extern.slf4j.Slf4j;
import top.desert2ocean.pages2book.core.config.DivSection;
import top.desert2ocean.pages2book.core.downloader.RequestsDownloader;
import top.desert2ocean.pages2book.core.epub.EpubCreator;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

import java.net.MalformedURLException;

@Slf4j
public class Main {

    public static void main(String[] args) {
        DocsConfig config = gradle();
        startCrawler(config);

    }

    private static DocsConfig gradle() {
        String startUrl = "https://docs.gradle.org/current/userguide/userguide.html";
        DocsConfig config = new DocsConfig();
        config.setBaseUrl("https://docs.gradle.org/current/userguide/");
        config.setCatalog(new DivSection("docs-navigation"));
        config.setContent(new DivSection("chapter"));
        config.setStartUrl(startUrl);
        config.setAuthor("Jacob");
        config.setTitle("Gradle");
        return config;
    }

    private static DocsConfig docker() {
        String startUrl = "https://docs.docker.com/";
        DocsConfig config = new DocsConfig();
        config.setBaseUrl("https://docs.docker.com/");
        config.setCatalog(new DivSection("col-nav"));
        config.setContent(new DivSection("main-content"));
        config.setStartUrl(startUrl);
        config.setAuthor("Jacob");
        config.setTitle("Docker");
        return config;
    }


    private static DocsConfig kotlin() {
        String startUrl = "https://kotlinlang.org/docs/tutorials/";
        DocsConfig config = new DocsConfig();
        config.setBaseUrl("https://kotlinlang.org/docs/tutorials/");
        config.setCatalog(new DivSection("js-side-tree-nav"));
        config.setContent(new DivSection("page-content"));
        config.setStartUrl(startUrl);
        config.setAuthor("Jacob");
        config.setTitle("Kotlin");
        return config;
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
        log.info("download finished.");
        try {
            EpubCreator.create(config);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
