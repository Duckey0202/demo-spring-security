package com.mt.demo.springsecurity.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LogController
 *
 * @author MT.LUO
 * 2018/1/17 16:47
 * @Description:
 */
public class LogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogController.class);

    public static void info(String title, String str) {

        LOGGER.info("["+title+"] "+str);
    }

    public static void warn(String title, String str) {
        LOGGER.warn("["+title+"] "+str);
    }

    public static void debug(String title, String str) {
        LOGGER.debug("["+title+"] "+str);
    }

    public static void error(String title, String str) {
        LOGGER.error("["+title+"] "+str);
    }
}
