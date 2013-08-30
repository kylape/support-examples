package com.redhat.gss.jaxws;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.MessageContext;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.configuration.security.FiltersType;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.jboss.logging.Logger;

public class TestClient
{
  private Logger log = Logger.getLogger(this.getClass());

  public static void main(String[] args) throws Exception
  {
    System.setProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager");
    Class.forName("java.util.logging.LogManager");
    new TestClient().invoke();
  }

  public void invoke() throws Exception
  {
    URL wsdl = getClass().getClassLoader().getResource("/the.wsdl");
    QName qname = new QName("http://jaxws.gss.redhat.com/", "HelloWS");
    Service service = Service.create(wsdl, qname);
    HelloWS port = service.getPort(HelloWS.class);
    ((BindingProvider)port).getRequestContext()
      .put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,"https://127.0.0.1:8443/cxfSsl/hello");

    setupSsl(getHttpConduit(port));
    setupHttpPolicy(getHttpConduit(port));

    log.info(port.hello("Kyle"));
  }

  public void setupHttpPolicy(HTTPConduit httpConduit) throws Exception
  {
    HTTPClientPolicy policy = new HTTPClientPolicy();
    policy.setReceiveTimeout(10000L);
    policy.setAllowChunking(false);
    httpConduit.setClient(policy);
  }

  public HTTPConduit getHttpConduit(Object port)
  {
    Client client = ClientProxy.getClient(port);
    if(client != null)
      return (HTTPConduit) client.getConduit();
    else
      return null;
  }

  public void setupSsl(HTTPConduit httpConduit) throws Exception
  {
    final String certAlias = "cn=client, ou=gss, o=red hat, l=raleigh, st=nc, c=us";
    TLSClientParameters tlsParams = new TLSClientParameters();  
    // tlsParams.setDisableCNCheck(true);  
    tlsParams.setSecureSocketProtocol("TLSv1");

    //This call is only available and/or required in EAP 6.0.1+
    tlsParams.setCertAlias(certAlias);

    //Set up the truststore for CXF
    KeyStore trustStore = KeyStore.getInstance("JKS");  
    String trustpass = "client"; 
    // File truststoreFile = new File("client.keystore"); 
    // trustStore.load(new FileInputStream(truststoreFile), trustpass.toCharArray());  
    URL truststoreUrl = Thread.currentThread().getContextClassLoader().getResource("/client.keystore");
    trustStore.load(truststoreUrl.openStream(), trustpass.toCharArray());  
    TrustManagerFactory trustFactory =  
        TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());  
    trustFactory.init(trustStore);  
    TrustManager[] tm = trustFactory.getTrustManagers();  
    tlsParams.setTrustManagers(tm);

    //Set up the keystore for CXF
    KeyStore keyStore = KeyStore.getInstance("JKS");  
    String keypass = "client"; 
    // File keystoreFile = new File("client.keystore"); 
    // keyStore.load(new FileInputStream(keystoreFile),  keypass.toCharArray());  
    URL keystoreUrl = Thread.currentThread().getContextClassLoader().getResource("/client.keystore");
    keyStore.load(keystoreUrl.openStream(),  keypass.toCharArray());  
    KeyManagerFactory keyFactory =  
        KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
    keyFactory.init(keyStore, trustpass.toCharArray());  
    KeyManager[] km = keyFactory.getKeyManagers();  
    tlsParams.setKeyManagers(km);

    // FiltersType filter = new FiltersType();  
    // filter.getInclude().add(".*_EXPORT_.*");  
    // filter.getInclude().add(".*_EXPORT1024_.*");  
    // filter.getInclude().add(".*_WITH_DES_.*");  
    // filter.getInclude().add(".*_WITH_NULL_.*");  
    // filter.getExclude().add(".*_DH_anon_.*");  
    // tlsParams.setCipherSuitesFilter(filter); //set all the needed include and exclude filters.

    httpConduit.setTlsClientParameters(tlsParams);  
  }
}
