package com.ordermatic.app.security;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

@Testcontainers
@EnableMongoRepositories
public class MongoDbTestContainer {
  private final static String MONGODB_IMAGE = "mongo:4.2.3";
  private final static String MONGO_ROOT_USERNAME="admin";
  private final static String MONGO_ROOT_PASSWORD="admin";
  private final static String MONGO_INITDB_DATABASE="admin";
  private final static int MONGO_PORT = 27017;


  @Container
  public static final MongoDBContainer mongoDbContainer = new MongoDBContainer(MONGODB_IMAGE)
    .withEnv("MONGO_INITDB_ROOT_USERNAME", MONGO_ROOT_USERNAME)
    .withEnv("MONGO_INITDB_ROOT_PASSWORD", MONGO_ROOT_PASSWORD)
    .withEnv("MONGO_INITDB_DATABASE", MONGO_INITDB_DATABASE)
    .withExposedPorts(MONGO_PORT)
    .withCommand("--replSet rs0")
    .withCopyFileToContainer(MountableFile.forClasspathResource("./init-schema.js"), "/docker-entrypoint-initdb.d/init-script.js");

  static {
    mongoDbContainer.start();

    String connectionString = String.format("mongodb://%s:%s@%s:%d/admin",
      MONGO_ROOT_USERNAME,
      MONGO_ROOT_PASSWORD,
      mongoDbContainer.getHost(),
      mongoDbContainer.getFirstMappedPort()
    );

    MongoClient mongoClient = MongoClients.create(connectionString);
    mongoClient.getDatabase("admin").runCommand(new Document("replSetInitiate", new Document()));
  }


  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("mongodb.security.port", mongoDbContainer::getFirstMappedPort);
  }
}
