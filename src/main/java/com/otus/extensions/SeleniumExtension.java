package com.otus.extensions;

import com.otus.annotations.Driver;
import com.otus.driver.DriverFactory;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SeleniumExtension implements BeforeEachCallback, AfterEachCallback {
  WebDriver driver = null;

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    driver = DriverFactory.getWebDriver(System.getProperty("browser").toLowerCase(Locale.ROOT));
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    Set<Field> fields = getAnnotationFields(Driver.class, extensionContext);
    for (Field field : fields) {
      if (field.getType().getName().equals(WebDriver.class.getName())) {
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
          field.setAccessible(true);
          try {
            field.set(extensionContext.getTestInstance().get(), driver);
          } catch (IllegalAccessException e) {
            e.printStackTrace();
          }
          return null;
        });
      }
    }
  }

  @Override
  public void afterEach(ExtensionContext extensionContext) {
    if (driver != null) {
      driver.quit();
    }
  }

  private Set<Field> getAnnotationFields(Class<? extends Annotation> clazz, ExtensionContext extensionContext) {
    Class<?> testClass = extensionContext.getTestClass().get();
    Set<Field> fields = new HashSet<>();
    while (testClass != null) {
      for (Field field : testClass.getDeclaredFields()) {
        if (field.isAnnotationPresent(clazz)) {
          fields.add(field);
        }
      }
      testClass = testClass.getSuperclass();
    }
    return fields;
  }
}
