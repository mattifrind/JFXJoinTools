package org.chaoscoders.firstextension;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainControllerTest {

    @Test
    void printToLabel() {
        MainController mc = new MainController();
        String expectedString = "Du hast \"Da Da Da\" eingegeben.";
        Assertions.assertEquals(expectedString, mc.getLabelText("Da Da Da"));
    }
}