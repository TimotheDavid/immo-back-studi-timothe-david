package infoco.immo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ConfigurationUtils {

    private Environment environment;

    public ConfigurationUtils(Environment environment) {
        this.environment = environment;
    }

}
