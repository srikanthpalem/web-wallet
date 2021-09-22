package org.cap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication 
@ComponentScan("org.cap")
@ServletComponentScan
public class WalletWebApp {

	public static void main(String[] args) {
		SpringApplication.run(WalletWebApp.class, args);
	}

}
