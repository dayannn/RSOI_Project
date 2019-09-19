package com.dayannn.RSOI2.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.content.commons.repository.Store;
import org.springframework.content.fs.config.EnableFilesystemStores;
import org.springframework.content.fs.io.FileSystemResourceLoader;
import org.springframework.content.rest.StoreRestResource;
import org.springframework.content.rest.config.RestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.File;

@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Configuration
	@EnableFilesystemStores
	@Import(RestConfiguration.class)
	public static class StoreConfig {
		File filesystemRoot() {
			return new File("audioStore");
		}

		// this bean is the spring resource loader that will be used by
		// the product store
		@Bean
		public FileSystemResourceLoader fsResourceLoader() throws Exception
		{
			return new FileSystemResourceLoader(filesystemRoot().getAbsolutePath());
		}
	}

	@StoreRestResource(path="audiosrc")
	public interface AudioStore extends Store<String> {
		//
	}
}
