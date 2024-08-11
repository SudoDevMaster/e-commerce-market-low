package com.marketlow.products;

import com.marketlow.products.domain.constant.ConstantsProperties;
import com.marketlow.products.domain.constant.GlobalProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
@Slf4j
@EnableConfigurationProperties({GlobalProperties.class})
@SpringBootApplication
public class ProductsApplication implements ApplicationListener<ContextRefreshedEvent> {
	private final GlobalProperties globalProperties;
	public ProductsApplication(GlobalProperties globalProperties) {
		this.globalProperties = globalProperties;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info(ConstantsProperties.LONG_LINE);
		log.info(ConstantsProperties.LOG_START_PROJECT, globalProperties.getName());
		log.info(ConstantsProperties.LOG_PORT_OF_PROJECT, globalProperties.getPort());
		log.info(ConstantsProperties.LOG_URL, String.valueOf(globalProperties.getPort()).concat(globalProperties.getRoot()));
		log.info(ConstantsProperties.LOG_PROJECT_VERSION, globalProperties.getVersion());
		log.info(ConstantsProperties.LOG_RUN_OK);
		log.info(ConstantsProperties.LONG_LINE);

	}
}
