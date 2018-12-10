package sk.stopangin.expensemanager;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by martin.cuchran on 12/6/2018.
 */
@Configuration
@EnableWebMvc
@ComponentScan(excludeFilters ={ @ComponentScan.Filter(Configuration.class),@ComponentScan.Filter(Repository.class),@ComponentScan.Filter(Service
        .class)})
public class WebConfig {
}
