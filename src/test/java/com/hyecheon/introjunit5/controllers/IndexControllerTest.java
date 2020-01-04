package com.hyecheon.introjunit5.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class IndexControllerTest {
    IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @DisplayName("테스트 한다 적절한 뷰 이름이 리턴 된다 인덱스 페이지 에서 ")
    @Test
    void index() {
        assertEquals("index", controller.index());
        assertEquals("index", controller.index(), "Wrong View Returned");
        assertEquals("index", controller.index(), () -> "This is some expensive Message to build for my test");

        assertThat(controller.index()).isEqualTo("index");
    }

    @DisplayName("예외 테스트")
    @Test
    void oopsHandler() {
        assertThrows(ValueNotFoundException.class, () -> {
            controller.oopsHandler();
        });
    }


    @Disabled("demo of timeout")
    @Test
    void testTimeOut() {
        assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(1900);
            System.out.println("I get here");
        });
    }

    @Disabled("demo of timeout")
    @Test
    void testTimeOutPrempt() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            Thread.sleep(2000);
            System.out.println("I get here 234234234");
        });
    }

    @Test
    void testAssumptionTrue() {
        assumeTrue("hyecheon".equalsIgnoreCase(System.getenv("USERNAME")));
    }

    @Test
    void testAssumptionTrueAssumptionIsTrue() {
        assumeTrue("hyecheon".equalsIgnoreCase("hyecheon"));
    }

    @EnabledOnOs(OS.MAC)
    @Test
    void testMeOnMacOs() {

    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testMeOnWindows() {

    }

    @EnabledOnJre(JRE.JAVA_8)
    @Test
    void testMeOnJava8() {

    }

    @EnabledOnJre(JRE.JAVA_13)
    @Test
    void testMeOnJava13() {

    }

    @EnabledIfEnvironmentVariable(named = "username", matches = "hyecheon")
    @Test
    void testIfUserHyecheon() {

    }

    @EnabledIfEnvironmentVariable(named = "username", matches = "fred")
    @Test
    void testIfUserFred() {

    }
}