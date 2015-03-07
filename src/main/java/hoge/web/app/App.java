package hoge.web.app;

import hoge.web.app.rest.api.MasterResource;
import hoge.web.app.rest.api.SqlExceptionHandler;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class App extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return super.getClasses();
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> set = new HashSet<>();
        set.add(new MasterResource());
        set.add(new SqlExceptionHandler());
        return set;
    }
}
