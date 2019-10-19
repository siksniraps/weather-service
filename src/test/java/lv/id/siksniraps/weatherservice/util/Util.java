package lv.id.siksniraps.weatherservice.util;

import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import java.io.IOException;

public class Util {

    public static void mockNextJsonResponse(MockRestServiceServer mockServer, String jsonPath) throws IOException {
        mockServer.expect(MockRestRequestMatchers.anything()).andRespond(MockRestResponseCreators.withSuccess(
                Streams.asString((new ClassPathResource(jsonPath)).getInputStream()),
                MediaType.APPLICATION_JSON)
        );
    }

}
