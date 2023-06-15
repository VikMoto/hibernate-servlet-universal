package com.universal.hibernate.app.configuration.hibernate;

import com.universal.hibernate.app.configuration.Environment;
import com.universal.hibernate.app.util.Constants;
import jakarta.persistence.Entity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
/**
 The provided code is the HibernateConfiguration class, which is responsible for
 setting up the Hibernate configuration based on the provided Environment and a list
 of classes to register.

 Here's an overview of the HibernateConfiguration class:

 It imports various dependencies, including Environment, Constants, Hibernate
 configuration settings, and other utility classes.
 The class is annotated with @Slf4j, which enables logging using the Simple Logging
 Facade for Java (SLF4J).

 The setup method is the main entry point of the class. It takes an Environment object
 and an array of classes (classesToRegister) as parameters and returns a Configuration
 object.

 Inside the setup method, a new Configuration object is created.

 The method retrieves various Hibernate configuration properties from the Environment
 object, such as the driver, URL, show SQL flag, dialect, username, and password.
 These properties are then set on the Configuration object using the setProperty method.

 The method uses the Reflections library to scan for classes annotated with @Entity.

 It first attempts to retrieve the base package from the Environment using the
 Constants.BASE_PACKAGE property. If the base package is found, it creates
 a Reflections object using that base package; otherwise, it creates a Reflections
 object without any specific base package.

 The Reflections object is then used to get a set of classes annotated with @Entity.
 The number of entities found is logged using SLF4J.

 If the classesToRegister array is empty, the method adds all the annotated classes
 to the Configuration object using the addAnnotatedClass method. Otherwise, it adds
 only the classes specified in the classesToRegister array.

 Finally, the Configuration object is returned.

 This class helps in dynamically registering annotated entity classes with Hibernate
 based on the provided configuration and class scanning. It allows for flexible
 configuration and avoids the need to explicitly list all entity classes manually.
 */
@Slf4j
public class HibernateConfiguration {

    public static Configuration setup(Environment environment, Class<?>... classesToRegister) {
        Configuration configuration = new Configuration();
        String driverProperty = environment.getProperty(AvailableSettings.DRIVER);
        String urlProperty = environment.getProperty(AvailableSettings.URL);
        String showSqlProperty = environment.getProperty(AvailableSettings.SHOW_SQL);
        Optional<String> dialectProperty = environment.getPropertyOptional(AvailableSettings.DIALECT);
        Optional<String> userProperty = environment.getPropertyOptional(AvailableSettings.USER);
        Optional<String> passwordProperty = environment.getPropertyOptional(AvailableSettings.PASS);

        configuration.setProperty(AvailableSettings.DRIVER, driverProperty);
        configuration.setProperty(AvailableSettings.URL, urlProperty);
        configuration.setProperty(AvailableSettings.SHOW_SQL, showSqlProperty);

        dialectProperty.ifPresent(dialect -> configuration.setProperty(AvailableSettings.DIALECT, dialect));
        userProperty.ifPresent(userName -> configuration.setProperty(AvailableSettings.USER, userName));
        passwordProperty.ifPresent(password -> configuration.setProperty(AvailableSettings.PASS, password));


        Reflections reflections = environment.getPropertyOptional(Constants.BASE_PACKAGE)
                .map(Reflections::new)
                .orElse(new Reflections());
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Entity.class);
        log.info("Found:[{}] entities", classes.size());
        if (classesToRegister.length == 0) {
            classes.forEach(configuration::addAnnotatedClass);
        } else {
            Arrays.stream(classesToRegister).forEach(configuration::addAnnotatedClass);
        }
        return configuration;
    }
}
