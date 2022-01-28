package com.adjust.testlibrary.test.api.test;

import com.adjust.testlibrary.config.TestConfig;
import com.adjust.testlibrary.model.base.Response;
import com.adjust.testlibrary.test.api.helper.PetHelper;
import com.adjust.testlibrary.test.api.helper.UserHelper;
import com.adjust.testlibrary.util.extension.WatcherExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Execution(ExecutionMode.CONCURRENT)
@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {TestConfig.class})
@TestPropertySource(locations = {"classpath:application-test.properties"})
public class AbstractAPITest {

    @RegisterExtension
    @Autowired
    public WatcherExtension watcherExtension;

    @Autowired
    public UserHelper userHelper;

    @Autowired
    public PetHelper petHelper;

    //--

    public static void assertErrorCode(int errorCode, Response response) {
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(errorCode, response.getError().getCode());
    }
}
