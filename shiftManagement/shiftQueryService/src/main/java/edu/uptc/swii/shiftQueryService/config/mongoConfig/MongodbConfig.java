package edu.uptc.swii.shiftQueryService.config;

import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.MongoDbFactory;


public class MongodbConfig {

    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDbFactory mongoDbFactory, MongoMappingContext mongoMappingContext) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        converter.setCustomConversions(customConversions());
        return converter;
    }

    private CustomConversions customConversions() {
        return new CustomConversions(Arrays.asList(
                new ZonedDateTimeJsonDeserializer(),
                new ZonedDateTimeJsonSerializer()
        ));
    }
}