package version;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author weijianyu
 */
@Configuration
public class AppConfig {

    @Bean(name = "version")
    public Version getVersion() {
        return new Version();
    }
}
