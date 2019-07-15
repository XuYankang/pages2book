package top.desert2ocean.pages2book.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ResourceUtilsTest {
    @Test
    public void testGetImageStringFromRemote() {
        String imageStringFromRemote = ResourceUtils.getImageStringFromRemote("https://docs.gradle.org/current/userguide/img/taskInputsOutputs.png");
        log.info("{}", imageStringFromRemote);
    }

}
