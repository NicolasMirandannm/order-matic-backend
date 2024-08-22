package com.ordermatic.app.security.infra.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
  basePackages = {"com.ordermatic.app.security.infra.database"},
  mongoTemplateRef = SecurityMongoDbConfiguration.MONGO_TEMPLATE
)
public class SecurityMongoDbConfiguration {

  protected static final String MONGO_TEMPLATE = "securityMongoTemplate";
  private static final String SECURITY_APP_PROPERTIES = "spring.data.mongodb.security";

  @Primary
  @Bean(name = "securityDatabaseProperties")
  @ConfigurationProperties(prefix = SECURITY_APP_PROPERTIES)
  public MongoProperties getSecurityProps() {
    return new MongoProperties();
  }

  @Primary
  @Bean(name = MONGO_TEMPLATE)
  public MongoTemplate securityMongoTemplate() {
    return new MongoTemplate(securityMongoDatabaseFactory(getSecurityProps()));
  }

  @Primary
  @Bean
  public MongoDatabaseFactory securityMongoDatabaseFactory(MongoProperties props) {
    return new SimpleMongoClientDatabaseFactory(props.getUri());
  }
}
