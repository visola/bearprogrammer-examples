package org.visola.springbootspa.controller;

public class HelloVO {

    private final String name;

    public HelloVO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return "Hello " + name + "!";
    }

}
