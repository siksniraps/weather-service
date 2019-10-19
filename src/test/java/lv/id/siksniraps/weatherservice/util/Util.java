package lv.id.siksniraps.weatherservice.util;

import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.client.MockRestServiceServer;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class Util {

    public static void mockNextJsonResponse(MockRestServiceServer mockServer, String jsonPath) throws IOException {
        mockServer.expect(anything()).andRespond(withSuccess(
                Streams.asString((new ClassPathResource(jsonPath)).getInputStream()),
                APPLICATION_JSON)
        );
    }

}
