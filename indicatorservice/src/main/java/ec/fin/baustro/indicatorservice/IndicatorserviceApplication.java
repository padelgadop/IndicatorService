package ec.fin.baustro.indicatorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableJpaRepositories("ec.fin.baustro.indicatorservice.dao")
@EnableTransactionManagement
public class IndicatorserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndicatorserviceApplication.class, args);
	}

}
