package dev.mouhieddine.msscdrinksclient.web.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author : Mouhieddine.dev
 * @since : 2/13/2021, Saturday
 **/
@Component
//@ConfigurationProperties(prefix = "dev.mouhieddine.restTemplate", ignoreInvalidFields = false)
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {
  private final Integer maxTotalConnections;
  private final Integer defaultMaxTotalConnections;
  private final Integer connectionRequestTimeout;
  private final Integer socketTimeout;

  public BlockingRestTemplateCustomizer(@Value("${dev.mouhieddine.resttemplate.connectionmanager.maxtotal}") int maxTotalConnections,
                                        @Value("${dev.mouhieddine.resttemplate.connectionmanager.defaultmaxperroute}") int defaultMaxTotalConnections,
                                        @Value("${dev.mouhieddine.resttemplate.requestconfig.connectionrequesttimeout}") int connectionRequestTimeout,
                                        @Value("${dev.mouhieddine.resttemplate.requestconfig.sockettimeout}") int socketTimeout) {
    this.maxTotalConnections = maxTotalConnections;
    this.defaultMaxTotalConnections = defaultMaxTotalConnections;
    this.connectionRequestTimeout = connectionRequestTimeout;
    this.socketTimeout = socketTimeout;
  }

  @Override
  public void customize(RestTemplate restTemplate) {
    restTemplate.setRequestFactory(this.clientHttpRequestFactory());
  }

  private ClientHttpRequestFactory clientHttpRequestFactory() {
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();

    connectionManager.setMaxTotal(maxTotalConnections);
    connectionManager.setDefaultMaxPerRoute(defaultMaxTotalConnections);

    RequestConfig requestConfig = RequestConfig
            .custom()
            .setConnectionRequestTimeout(connectionRequestTimeout)
            .setSocketTimeout(socketTimeout)
            .build();

    CloseableHttpClient httpClient = HttpClients
            .custom()
            .setConnectionManager(connectionManager)
            .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
            .setDefaultRequestConfig(requestConfig)
            .build();

    return new HttpComponentsClientHttpRequestFactory(httpClient);
  }
}
