package com.hyecheon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.Map;
import org.junit.jupiter.api.Test;

public class InlineMockTest {

  @Test
  void testInlineMock() {
    final var mapMock = mock(Map.class);
    assertThat(mapMock.size()).isZero();
  }
}
