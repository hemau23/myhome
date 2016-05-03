import com.analytique.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

@Configuration
@Import(ApplicationConfig.class)
@Primary
public class DeployableApplicationConfig {

    protected DeployableApplicationConfig() {
    }

    public static void main(String[] args) {
        SpringApplication.run(DeployableApplicationConfig.class, args);
    }
}
