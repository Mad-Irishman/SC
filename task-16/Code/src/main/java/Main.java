import autoservice.config.application.AppConfig;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws Exception {

        Tomcat tomcat = new Tomcat();

        final Connector connector = new Connector();
        connector.setPort(8081);
        connector.setScheme("http");
        connector.setSecure(false);
        tomcat.setConnector(connector);

        File baseDir = Files.createTempDirectory("embedded-tomcat").toFile();

        Context context= tomcat.addWebapp("",baseDir.getAbsolutePath());

        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(AppConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);

        Tomcat.addServlet(context, "dispatcherServlet", dispatcherServlet).setLoadOnStartup(1);
        context.addServletMappingDecoded("/*", "dispatcherServlet");


        tomcat.start();
        tomcat.getServer().await();
    }
}