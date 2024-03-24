package com.app.demo.handler;

public class PartNotFoundException extends RuntimeException{

    public PartNotFoundException(String partNum) {
        super("Part with number" + partNum + "not found");
    }
}
