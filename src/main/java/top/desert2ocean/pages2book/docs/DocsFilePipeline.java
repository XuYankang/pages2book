package top.desert2ocean.pages2book.docs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

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
    }
}
