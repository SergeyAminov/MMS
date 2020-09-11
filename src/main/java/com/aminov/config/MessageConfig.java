package com.aminov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@Lazy
@ComponentScan(basePackages = "com.aminov")
@Configuration
public class MessageConfig {

    private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DEFAULT_DESTINATION = "jms/queue/myQueue";
    private static final String USERNAME = "test";
    private static final String PASSWORD = "test";
    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8080";

    @Lazy
    @Bean
    public ConnectionFactory connectionFactory() throws NamingException {

        String username = System.getProperty("username", USERNAME);
        String password = System.getProperty("password", PASSWORD);

        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        properties.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
        properties.put(Context.SECURITY_PRINCIPAL, username);
        properties.put(Context.SECURITY_CREDENTIALS, password);

        Context context = new InitialContext(properties);
        String connectionFactoryString = System.getProperty("connection.factory", DEFAULT_CONNECTION_FACTORY);

        ConnectionFactory factory = (ConnectionFactory) context.lookup(connectionFactoryString);

        UserCredentialsConnectionFactoryAdapter adapter = new UserCredentialsConnectionFactoryAdapter();
        adapter.setUsername(username);
        adapter.setPassword(password);
        adapter.setTargetConnectionFactory(factory);

        return adapter;
    }

    @Lazy
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