package com.ordermatic.app.security.infra.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static java.util.Collections.singletonList;

@Configuration
@EnableMongoRepositories(
  basePackages = {"com.ordermatic.app.security.infra.database"},
  mongoTemplateRef = SecurityMongoDbConfiguration.MONGO_TEMPLATE
)
public class SecurityMongoDbConfiguration {

  protected static final String MONGO_TEMPLATE = "securityMongoTemplate";
  private static final String MONGO_SECURITY = "mongodb.security";
  private static final String MONGO_SECURITY_PROPERTIES = "securityMongoProperties";

  @Bean(name = MONGO_SECURITY_PROPERTIES)
  @ConfigurationProperties(prefix = MONGO_SECURITY)
  @Primary
  public MongoProperties mongoProperties() {
    return new MongoProperties();
  }

  @Primary
  @Bean(name = "securityMongoClient")
  public MongoClient mongoClient(@Qualifier(MONGO_SECURITY_PROPERTIES) MongoProperties mongoProperties) {
    MongoCredential credential = MongoCredential.createCredential(
      mongoProperties.getUsername(),
      mongoProperties.getDatabase(),
      mongoProperties.getPassword()
    );
    return MongoClients.create(MongoClientSettings.builder()
        .applyToClusterSettings(builder -> builder.hosts(
          singletonList(new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort()))))
        .credential(credential)
      .build());
  }
}
