package main;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import reports.ReportsActions;

@SpringBootApplication
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(ReportsActions.class);
	}
}
