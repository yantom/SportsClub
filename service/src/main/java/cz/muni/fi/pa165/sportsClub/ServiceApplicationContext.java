package cz.muni.fi.pa165.sportsClub;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Jan Tomasek
 */
@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackages = { "cz.muni.fi.pa165.sportsClub.dao", "cz.muni.fi.pa165.sportsClub.service" })
public class ServiceApplicationContext {
	
	@Bean
	public Mapper dozer() {
		return new DozerBeanMapper();
	}
}
