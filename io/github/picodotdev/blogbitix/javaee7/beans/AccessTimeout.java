package io.github.picodotdev.blogbitix.javaee7.beans;

import java.util.concurrent.TimeUnit;

public @interface AccessTimeout {

 int value();

 TimeUnit unit();

}
