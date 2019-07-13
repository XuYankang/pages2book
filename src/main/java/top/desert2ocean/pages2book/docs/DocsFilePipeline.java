package top.desert2ocean.pages2book.docs;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;
import top.desert2ocean.pages2book.core.config.ImageUrl;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Slf4j
public class DocsFilePipeline extends FilePersistentBase implements Pipeline {
    public DocsFilePipeline(String path) {
        setPath(path);
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        String title = resultItems.get("title");
        String serial = resultItems.get("serial");
        if (StringUtils.isEmpty(title)) {
            return;
        }
        String path = this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;
        try {
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(getFile(path + serial + title + ".html")), "UTF-8"));
            String html = resultItems.get("html");
            printWriter.println(html);
            printWriter.close();
            log.info("write <{}> to file.", title);
        } catch (IOException e) {
            log.warn("write file error", e);
        }
        //处理图片
        List<ImageUrl> images = resultItems.get("images");

        for (ImageUrl image : images) {
            try {
                URL baseUrl = new URL(image.getBase());
                URL targetUrl = new URL(baseUrl, image.getSrc());
                InputStream in = targetUrl.openStream();
                String relativelyPath = System.getProperty("user.dir");
                byte[] bytes = IOUtils.toByteArray(in);
                FileUtils.writeByteArrayToFile(getFile(relativelyPath + PATH_SEPERATOR + path + image.getSrc()), bytes);
                log.info("save image {}.", image.getSrc());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
