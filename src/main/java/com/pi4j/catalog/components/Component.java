package com.pi4j.catalog.components;

import java.util.logging.Logger;

public abstract class Component {
    /**
     * Logger instance
     */
    private final Logger logger = Logger.getLogger(getClass().getName());

    protected void logError(String msg) {
        logger.severe(() -> msg);
    }

    protected void logDebug(String msg) {
        logger.fine(() -> msg);
    }

    /**
     * Utility function to sleep for the specified amount of milliseconds.
     * An {@link InterruptedException} will be caught and ignored while setting the interrupt flag again.
     *
     * @param milliseconds Time in milliseconds to sleep
     */
    void delay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
