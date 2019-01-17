package com.yf.psp;
  

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.TypeUtils;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.yf.psp.common.http.exception.HttpResult;
 
@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
//@EnableAutoConfiguration 
//@EnableRedisHttpSession(redisNamespace="cfMgr",maxInactiveIntervalInSeconds = 3600) //1小时失效
 
@ComponentScan(basePackages = "com.yf.psp")
public class Application { 
	public static void main(String[] args) { 
 
		SpringApplication.run(Application.class, args);
	}
	
	
	 
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
	    return new MappingJackson2HttpMessageConverter() {
	        @Override
	        protected void writeInternal(Object object,Type type,
	                                     HttpOutputMessage outputMessage) throws IOException,
	                HttpMessageNotWritableException {
	            MediaType contentType = outputMessage.getHeaders().getContentType();
	            JsonEncoding encoding = getJsonEncoding(contentType);
	            JsonGenerator generator = this.objectMapper.getFactory().createGenerator(outputMessage.getBody(), encoding);
	            this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);  
	            try {
	            	Object homeRetObj = object;
	            	if (!(object instanceof HttpResult)) {
	            		HttpResult hr = new HttpResult();
	            		hr.setErrorCode(0);
	            		hr.setErrorMsg("Success");
	            		hr.setData(object);
		            	homeRetObj = hr;
	            	}

	            	writePrefix(generator, homeRetObj);
	            	Class<?> serializationView = null;
	            	FilterProvider filters = null;
	            	Object value = homeRetObj;
	            	JavaType javaType = null;
	            	if (homeRetObj instanceof MappingJacksonValue) {
	            		MappingJacksonValue container = (MappingJacksonValue) homeRetObj;
	            		value = container.getValue();
	            		serializationView = container.getSerializationView();
	            		filters = container.getFilters();
	            	}
	            	
	            	if (type != null && value != null && TypeUtils.isAssignable(type, value.getClass())) {
	            		javaType = getJavaType(type, null);
	            	}
	            	
	            	ObjectWriter objectWriter;
	            	if (serializationView != null) {
	            		objectWriter = this.objectMapper.writerWithView(serializationView);
	            	}
	            	else if (filters != null) {
	            		objectWriter = this.objectMapper.writer(filters);
	            	}
	            	else {
	            		objectWriter = this.objectMapper.writer();
	            	}
	            	if (javaType != null && javaType.isContainerType()) {
	            		objectWriter = objectWriter.forType(javaType);
	            	}
	            	SerializationConfig config = objectWriter.getConfig();
	            	DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
	        		prettyPrinter.indentObjectsWith(new DefaultIndenter("  ", "\ndata:"));
	            	if (contentType != null && contentType.isCompatibleWith(new MediaType("text", "event-stream")) &&
	            			config.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
	            		objectWriter = objectWriter.with(prettyPrinter);
	            	}
	            	objectWriter.writeValue(generator, value);
	            	writeSuffix(generator, homeRetObj);
	            	generator.flush();
	            }
	            catch (JsonProcessingException ex) {
	            	throw new HttpMessageNotWritableException("Could not write content: " + ex.getMessage(), ex);
	            }
	        }
	    };
	}

}
