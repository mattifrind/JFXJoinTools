package org.chaoscoders.jfxextensionapi.frontend.util.exceptions;

public class PluginUUIDNotResolvedException extends Exception{

    public PluginUUIDNotResolvedException(String errormsg){
        super(errormsg);
    }

    public PluginUUIDNotResolvedException(String errormsg, Throwable err){
        super(errormsg, err);
    }

}
