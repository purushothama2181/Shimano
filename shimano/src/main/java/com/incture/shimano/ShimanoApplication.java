package com.incture.shimano;

import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.config.java.ServiceScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger.web.SecurityConfiguration;

import javax.sql.DataSource;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ServiceScan
public class ShimanoApplication {


    public static void main(String[] args)  {
        SpringApplication.run(ShimanoApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    // In this function we are getting DataSource to connect to data base

    // The credentials we are using vcap services
    // And "service_instance_name_added_to_cf" is the instance name same you
    // have to mention in manifest.yml file
    // from where cf will read the application properties you want to deploy
    @Bean
    @Primary
    public DataSource dataSource(
            @Value("${vcap.services.P44_DEV_SCHEMA.credentials.url}") final String url,
            @Value("${vcap.services.P44_DEV_SCHEMA.credentials.user}") final String user,
            @Value("${vcap.services.P44_DEV_SCHEMA.credentials.password}") final String password) {
        DataSource dataSource = DataSourceBuilder.create().type(DriverManagerDataSource.class)
                .driverClassName(com.sap.db.jdbc.Driver.class.getName()).url(url).username(user).password(password)
                .build();
        return dataSource;
    }
}
