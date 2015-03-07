package hoge.web.app.boot;

import hoge.web.app.model.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Boot implements ServletContextListener {

    private static final Logger LOG = LoggerFactory.getLogger(Boot.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.info("initialized");

        try {
            Database.init();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
