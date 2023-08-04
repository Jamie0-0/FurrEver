package tw.idv.tibame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan
@ComponentScan("tw.idv.tibame.*.*")
@EnableJpaRepositories("tw.idv.tibame.*.*")
@EnableTransactionManagement
public class FurrEverApplication {

	public static void main(String[] args) {
		SpringApplication.run(FurrEverApplication.class, args);
	}

}
