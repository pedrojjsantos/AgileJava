package sis.testing;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Ignore {
    String[] reasons() default TestRunner.DEFAULT_IGNORE_REASON;
    String initials();
    Date date();
}
