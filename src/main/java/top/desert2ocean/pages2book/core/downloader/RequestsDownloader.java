package top.desert2ocean.pages2book.core.downloader;

import net.dongliu.requests.Requests;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.AbstractDownloader;
import us.codecraft.webmagic.selector.PlainText;

public class RequestsDownloader extends AbstractDownloader {


    @Override
    public Page download(Request request, Task task) {
        byte[] bytes = Requests.get(request.getUrl()).send().readToBytes();
        Page page = new Page();
        page.setUrl(new PlainText(request.getUrl()));
        page.setRequest(request);
        page.setDownloadSuccess(true);
        page.setBytes(bytes);
        page.setRawText(new String(bytes));

        return page;
    }

    @Override
    public void setThread(int threadNum) {

    }
}
