package cz.muni.fi.pa165.sportsClub.service;

import cz.muni.fi.pa165.sportsClub.PersistenceApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Simon Sudora 461460
 */

@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackages = "cz.muni.fi.pa165.sportsClub")
public class ServiceApplicationContext {
}
