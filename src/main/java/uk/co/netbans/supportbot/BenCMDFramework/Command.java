package uk.co.netbans.supportbot.BenCMDFramework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    String name();

    String permission() default "none";

    String[] aliases() default {};

    String usage() default "";

    String displayName();

}