package top.desert2ocean.pages2book.core.epub;

import lombok.extern.slf4j.Slf4j;
import nl.siegmann.epublib.domain.*;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;
import org.apache.commons.io.FileUtils;
import top.desert2ocean.pages2book.core.utils.ResourceUtils;
import top.desert2ocean.pages2book.docs.DocsConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class EpubCreator {
    public static void main(String[] args) throws MalformedURLException {
        DocsConfig docsConfig = new DocsConfig();
        docsConfig.setTitle("gradle");
        docsConfig.setAuthor("Jacob");
        docsConfig.setBaseUrl("https://docs.gradle.org/current/userguide/");
        new EpubCreator().create(docsConfig);
    }

    public void create(DocsConfig docsConfig) throws MalformedURLException {
        String userDir = ResourceUtils.getUserDir();
        String subDir = docsConfig.getHost();
        try {
            // Create new Book
            Book book = new Book();

            Metadata metadata = book.getMetadata();

            // Set the title
            metadata.addTitle(docsConfig.getTitle());

            // Add an Author
            metadata.addAuthor(new Author(docsConfig.getAuthor()));

            //list html
            Collection<File> htmlFiles = FileUtils.listFiles(new File(userDir + ResourceUtils.PATH_SEPERATOR + docsConfig.getSaveDir() + ResourceUtils.PATH_SEPERATOR + subDir), new String[]{"html", "htm"}, false);
            List<File> list = new ArrayList<>(htmlFiles);
            list.sort(Comparator.comparing(File::getName));
            list.forEach(file -> {
                try {
                    book.addSection(file.getName(), getResource(file));
                } catch (Exception e) {
                    log.error("get resource of {} error.", file.getAbsolutePath());
                    e.printStackTrace();
                }
            });

            // Create EpubWriter
            EpubWriter epubWriter = new EpubWriter();

            // Write the Book as Epub
            epubWriter.write(book, new FileOutputStream(docsConfig.getTitle() + ".epub"));
            log.info("success create book {}", docsConfig.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Resource getResource(File file) throws IOException {
        byte[] bytes = FileUtils.readFileToByteArray(file);
        String name = file.getName();
        //找到后缀名
        String fileType = ResourceUtils.getFileType(name);

        switch (fileType) {
            case "png":
                return new Resource(bytes, MediatypeService.PNG);
            case "html":
            case "htm":
                return new Resource(bytes, MediatypeService.XHTML);
            case "jpg":
            case "jpeg":
                return new Resource(bytes, MediatypeService.JPG);
            case "gif":
                return new Resource(bytes, MediatypeService.GIF);
        }
        return null;
    }
}
