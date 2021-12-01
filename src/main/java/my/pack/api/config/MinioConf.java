package my.pack.api.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotEmpty;

@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioConf {
    @NotEmpty
    private String key;
    @NotEmpty
    private String secret;
    @NotEmpty
    private String url;
    @NotEmpty
    private String bucket;

    @Bean
    public MinioClient getMinioClient() {
        try {
            return new MinioClient(url, key, secret);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}