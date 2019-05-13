package com.kiselev.suggester.data.source;

import com.kiselev.suggester.data.source.db.DBConfiguration;
import com.kiselev.suggester.data.source.elasticsearch.ElasticSearch;
import com.kiselev.suggester.data.source.elasticsearch.service.ElasticSearchService;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DBConfiguration.class)
public class DataSourcesConfiguration {

    @Value("${elasticsearch.hostname}")
    private String elasticSearchHostName;

    @Value("${elasticsearch.port}")
    private Integer elasticSearchHostPort;

    @Value("${elasticsearch.protocol}")
    private String elasticSearchProtocol;

    @Bean
    public ElasticSearch elasticSearchService() {
        return new ElasticSearchService();
    }

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost(elasticSearchHostName, elasticSearchHostPort, elasticSearchProtocol)));
    }
}
