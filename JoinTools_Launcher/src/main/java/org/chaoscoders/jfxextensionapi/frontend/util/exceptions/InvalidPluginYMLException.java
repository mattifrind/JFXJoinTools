package org.chaoscoders.jfxextensionapi.frontend.util.exceptions;

public class InvalidPluginYMLException extends Exception{

    public InvalidPluginYMLException(String errormsg){
        super(errormsg);
    }

    public InvalidPluginYMLException(String errormsg, Throwable err){
        super(errormsg, err);
    }

}
