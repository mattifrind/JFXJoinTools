package org.chaoscoders.extensions.sample;

import org.chaoscoders.extensions.sample.MainController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainControllerTest {

    @Test
    void getLabelText() {
        MainController mc = new MainController();
        assertEquals("Du hast \"Da Da Da\" eingegeben.", mc.getLabelText("Da Da Da"));
    }
}