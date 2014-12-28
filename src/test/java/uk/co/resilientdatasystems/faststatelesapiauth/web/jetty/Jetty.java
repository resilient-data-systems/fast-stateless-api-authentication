package uk.co.resilientdatasystems.faststatelesapiauth.web.jetty;

import static java.lang.System.getProperty;
import static java.lang.System.setProperty;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.util.ConcurrentHashSet;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.WebApplicationInitializer;

import uk.co.resilientdatasystems.faststatelesapiauth.config.FilterChainConfig;
import uk.co.resilientdatasystems.faststatelesapiauth.config.WebConfig;

public class Jetty {

    private static final Logger log = LoggerFactory.getLogger(Jetty.class);

    private static final String contextPath = "/";
    private static final String ip = "0.0.0.0";
    private static final int port = 9090;

    public static void main(String[] args) throws Exception {
        StopWatch sw = createStartedStopWatch();
        Server server = createStartedServer();
        sw.stop();
        System.out.println("Started in " + sw.getTotalTimeMillis() + "ms");
        server.join();
    }

    public static Server createStartedServer() throws Exception {
        assignPropertyDefault("spring.profiles.active", "sampleData");

        WebAppContext webApp = createWebapp();
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[] { webApp });
        Server server = new Server();
        server.setHandler(contexts);
        server.setConnectors(new Connector[] { addConnectorTo(server) });
        server.start();
        return server;
    }

    private static WebAppContext createWebapp() throws Exception {
        String webAppDir = "src/main/webapp/";
        WebAppContext webApp = new WebAppContext();
        webApp.setContextPath(contextPath);
        webApp.setConfigurations(new Configuration[] { new AnnotationConfiguration() {
            @Override
            public void preConfigure(WebAppContext context) throws Exception {
                ConcurrentHashSet<String> implementations = new ConcurrentHashSet<>();
                implementations.add(WebConfig.class.getName());
                implementations.add(FilterChainConfig.class.getName());

                ClassInheritanceMap map = new ClassInheritanceMap();
                map.put(WebApplicationInitializer.class.getName(), implementations);
                context.setAttribute(CLASS_INHERITANCE_MAP, map);
            }
        } });
        webApp.setResourceBase(webAppDir);
        webApp.setParentLoaderPriority(true);
        return webApp;
    }

    private static Connector addConnectorTo(Server server) {
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        connector.setHost(ip);
        return connector;
    }

    private static StopWatch createStartedStopWatch() {
        StopWatch sw = new StopWatch();
        sw.start();
        return sw;
    }

    private static String assignPropertyDefault(String name, String defaultValue) {
        if (getProperty(name) == null) {
            log.debug("Setting default value {} for {}", defaultValue, name);
            setProperty(name, defaultValue);
        }
        return getProperty(name);
    }

}
