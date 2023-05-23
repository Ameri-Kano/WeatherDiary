package amerikano.weatherdiary.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("diary")
                .packagesToScan("amerikano.weatherdiary")
                .build();
    }

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info().title("날씨 일기 프로젝트 ^~^")
                        .description("날씨 일기를 CRUD 할 수 있는 API 입니다.")
                        .version("2.1.3"));
    }

}
