package com.hyecheon.introjunit5.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.hyecheon.introjunit5.fauxspring.BindingResult;
import com.hyecheon.introjunit5.fauxspring.Model;
import com.hyecheon.introjunit5.model.Owner;
import com.hyecheon.introjunit5.service.OwnerService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

  private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
  private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

  @Mock
  OwnerService ownerService;

  @InjectMocks
  OwnerController ownerController;

  @Mock
  BindingResult bindingResult;

  @Mock
  Model model;

  @Captor
  ArgumentCaptor<String> stringArgumentCaptor;


  @BeforeEach
  void setUp() {
    given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture()))
        .willAnswer(invocation -> {
          List<Owner> owners = new ArrayList<>();
          String name = invocation.getArgument(0);
          if (name.equals("%Buck%")) {
            owners.add(
                new Owner(1L, "Joe", "Buck"));
            return owners;
          } else if (name.equals("%DontFindMe%")) {
            return owners;
          } else if (name.equals("%FindMe%")) {
            owners.add(new Owner(1L, "joe", "Buck"));
            owners.add(new Owner(2L, "joe2", "Buck2"));
            return owners;
          }
          throw new RuntimeException("Invalid Argument");
        });
  }

  @Test
  void processFindFormWildcardStringAnnotation() {
    //given
    final var owner = new Owner(1L, "Joe", "Buck");

    //when
    final var viewName = ownerController.processFindForm(owner, bindingResult, null);

    //then
    assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
    assertThat("redirect:/owners/1").isEqualToIgnoringCase(viewName);
  }

  @Test
  void processFindFormWildcardFound() {
    //given
    final var owner = new Owner(1L, "joe", "FindMe");
    InOrder inOrder = inOrder(ownerService, model);
    //when
    final var viewName = ownerController
        .processFindForm(owner, bindingResult, model);

    //then
    assertThat("%FindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
    assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);

    // inOrder asserts
    inOrder.verify(ownerService).findAllByLastNameLike(anyString());

    inOrder.verify(model, timeout(1)).addAttribute(anyString(), anyList());
  }

  @Test
  void processFindFormWildcardNotFound() {
    //given
    final var owner = new Owner(1L, "joe", "DontFindMe");

    //when
    final var viewName = ownerController.processFindForm(owner, bindingResult, null);
    verifyNoMoreInteractions(ownerService);

    assertThat("%DontFindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
    assertThat("owners/findOwners").isEqualToIgnoringCase(viewName);
    verifyNoMoreInteractions(model);
  }

  @Test
  void processCreationForm() {
    //given
    final var owner = new Owner(1L, "Jim", "Bob");
    given(bindingResult.hasErrors()).willReturn(true);

    //when
    String viewName = ownerController.processCreationForm(owner, bindingResult);

    //then
//    then(viewName).should().equalsIgnoreCase("owners/createOrUpdateOwnerForm");
    assertThat(viewName).isEqualToIgnoringCase(OWNERS_CREATE_OR_UPDATE_OWNER_FORM);
    verifyNoMoreInteractions(model);
  }

  @Test
  void processCreationNoErrors() {

    //given
    final var owner = new Owner(5L, "Jim", "Bob");
    given(bindingResult.hasErrors()).willReturn(false);
    given(ownerService.save(any())).willReturn(owner);

    //when
    final var viewName = ownerController.processCreationForm(owner, bindingResult);

    //then
    assertThat(viewName).isEqualToIgnoringCase(REDIRECT_OWNERS_5);
  }
}