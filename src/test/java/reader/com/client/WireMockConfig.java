package reader.com.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

@TestConfiguration
@ActiveProfiles("test")
public class WireMockConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer mockCustomersService() {
        return new WireMockServer(80);
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer mockCustomersService2() {
        return new WireMockServer(81);
    }
}