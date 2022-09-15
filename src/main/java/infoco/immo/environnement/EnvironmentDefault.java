package infoco.immo.environnement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
public class EnvironmentDefault implements EnvironmentAware {

    private static Environment environment;


    @Override
    public void setEnvironment(Environment environment) {
        EnvironmentDefault.environment = environment;
    }

    public static String databaseUser(){
        return environment.getProperty("spring.datasource.username");
    }

    public static String databasePassword(){
        return environment.getProperty("spring.datasource.password");
    }

    public static String databaseConnectionUrl(){
        return environment.getProperty("spring.datasource.url");
    }

    public static String secretString(){
        return environment.getProperty("secret-string");
    }

}
