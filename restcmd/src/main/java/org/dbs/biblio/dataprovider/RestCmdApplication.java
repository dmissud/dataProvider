package org.dbs.biblio.dataprovider;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "DataProvider for Biblio API", version = "0.0.1", description = "Provide some data to enrich biblio application"))
public class RestCmdApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestCmdApplication.class, args);
    }

}
