package reader.com.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;


public class CustomerMocks {
    public static void setupMockBooksResponse(WireMockServer mockService) throws IOException {
        String filePath = "src/test/resources/customers-get-response.json";
        String json = Arrays.toString(Files.readAllBytes(Paths.get(filePath)));
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/customers"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(json)
                )
        );
    }
}
