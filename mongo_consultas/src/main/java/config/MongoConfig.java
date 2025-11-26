package config;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.*;

public class MongoConfig {

    private static final String URI = "mongodb://localhost:27017";
    private static final String DB_NAME = "clinica";

    private final MongoClient client;
    private final MongoDatabase database;

    public MongoConfig() {
        try {
        CodecProvider provider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry registry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(provider)
        );

        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(registry)
                .applyConnectionString(new com.mongodb.ConnectionString(URI))
                .build();

        client = MongoClients.create(settings);
        database = client.getDatabase(DB_NAME);

        System.out.println("Conectado ao MongoDB na base de dados: " + DB_NAME);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar ao MongoDB: " + e.getMessage(), e);
        }
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void close() {
        client.close();
    }
}

