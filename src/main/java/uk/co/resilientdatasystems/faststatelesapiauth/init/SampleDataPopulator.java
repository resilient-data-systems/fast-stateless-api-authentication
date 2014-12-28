package uk.co.resilientdatasystems.faststatelesapiauth.init;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Component;

@Component
@DependsOn({ "dataSource" })
public class SampleDataPopulator {
    private final static Logger log = LoggerFactory.getLogger(SampleDataPopulator.class);

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void populateSampleData() throws ScriptException, SQLException {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("users.sql"));
        populator.addScript(new ClassPathResource("authorities.sql"));

        populator.populate(dataSource.getConnection());
        
        log.info("Populated DB with sample data");
    }
}