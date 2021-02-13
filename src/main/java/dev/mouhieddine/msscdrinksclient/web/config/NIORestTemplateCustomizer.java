package dev.mouhieddine.msscdrinksclient.web.config;

import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.IOReactorException;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author : Mouhieddine.dev
 * @since : 2/13/2021, Saturday
 **/
//@Component
public class NIORestTemplateCustomizer implements RestTemplateCustomizer {
  @Override
  public void customize(RestTemplate restTemplate) {
    try {
      restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    } catch (IOReactorException e) {
      e.printStackTrace();
    }
  }

  private ClientHttpRequestFactory clientHttpRequestFactory() throws IOReactorException {
    final DefaultConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(
            IOReactorConfig
                    .custom()
                    .setConnectTimeout(3000)
                    .setIoThreadCount(4)
                    .setSoTimeout(3000)
                    .build()
    );

    final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager((HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection>) ioReactor);
    connectionManager.setDefaultMaxPerRoute(100);
    connectionManager.setMaxTotal(1000);

    CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.custom()
            .setConnectionManager(connectionManager)
            .build();

    return HttpComponentsAsyncClientHttpRequestFactory(httpAsyncClient);
  }
}
