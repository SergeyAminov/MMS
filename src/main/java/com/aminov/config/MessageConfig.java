package com.aminov.config;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@ComponentScan(basePackages = "com.aminov")
@Configuration
public class MessageConfig {

    private static final String DEFAULT_CONNECTION_FACTORY = "java:jboss/exported/jms/RemoteConnectionFactory";
    private static final String DEFAULT_DESTINATION = "java:jboss/exported/jms/queue/myQueue";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String INITIAL_CONTEXT_FACTORY = "org.wildfly.naming.client.WildFlyInitialContextFactory";
    private static final String PROVIDER_URL = "http://localhost:8080/";

    @Bean
    public ActiveMQConnectionFactory connectionFactory() throws NamingException {

        String username = System.getProperty("username", USERNAME);
        String password = System.getProperty("password", PASSWORD);

        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        properties.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
        properties.put(Context.SECURITY_PRINCIPAL, username);
        properties.put(Context.SECURITY_CREDENTIALS, password);

        Context context = new InitialContext(properties);
        String connectionFactoryString = System.getProperty("connection.factory", DEFAULT_CONNECTION_FACTORY);

        return (ActiveMQConnectionFactory) context.lookup(connectionFactoryString);
    }

    @Bean
    public JmsTemplate jmsTemplate() throws NamingException{
        String destinationName = System.getProperty("destination", DEFAULT_DESTINATION);
        JmsTemplate template = new JmsTemplate();

        String username = System.getProperty("username", USERNAME);
        String password = System.getProperty("password", PASSWORD);

        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        properties.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
        properties.put(Context.SECURITY_PRINCIPAL, username);
        properties.put(Context.SECURITY_CREDENTIALS, password);

        Context context = new InitialContext(properties);

        Destination destination = (Destination) context.lookup(destinationName);
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(destinationName);
        template.setDefaultDestination(destination);
        return template;
    }

}





































