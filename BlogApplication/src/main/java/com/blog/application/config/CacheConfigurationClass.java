package com.blog.application.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Configuration;

import com.blog.application.service.impl.CategoryServiceImpl;

@Configuration
public class CacheConfigurationClass {
	
	Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	CacheManagerCustomizer<ConcurrentMapCacheManager> coustomizer(){
		return new ConcurrentCoustomizer();
	}
	
	class ConcurrentCoustomizer implements CacheManagerCustomizer<ConcurrentMapCacheManager> {

		@Override
		public void customize(ConcurrentMapCacheManager cacheManager) {
			cacheManager.setAllowNullValues(false);
			cacheManager.setStoreByValue(true);
			log.info("ConcurrentCoustomizer invoked");
		}

	
		
	}

}
