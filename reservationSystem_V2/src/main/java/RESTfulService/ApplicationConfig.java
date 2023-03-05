package RESTfulService;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.persistence.*;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("rs")
public class ApplicationConfig extends Application {
    private final Set<Class<?>> classes;
    public ApplicationConfig() {
        HashSet<Class<?>> c = new HashSet<>();
        c.add(RESTful_RestaurantService.class);
        c.add(MOXyJsonProvider.class);
        classes = Collections.unmodifiableSet(c);
    }
    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}
