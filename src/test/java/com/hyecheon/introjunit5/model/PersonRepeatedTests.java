package com.hyecheon.introjunit5.model;

import com.hyecheon.introjunit5.ModelRepeatedTests;
import org.junit.jupiter.api.*;

@Tag("repeated")
public class PersonRepeatedTests implements ModelRepeatedTests {

    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} | {totalRepetitions}")
    @DisplayName("My Repeated Test")
    void myRepeatedTest() {
        //todo impl
    }

    @RepeatedTest(5)
    void myRepeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName() + ":" + repetitionInfo.getCurrentRepetition());
    }

    @RepeatedTest(value = 5, name = "{displayName} : {currentRepetition} | {totalRepetitions}")
    @DisplayName("My Assignment Repeated Test")
    void myAssignmentRepeated() {
        //todo impl
    }
}
