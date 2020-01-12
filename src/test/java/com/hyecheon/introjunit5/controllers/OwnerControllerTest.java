package com.hyecheon.introjunit5.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.hyecheon.introjunit5.fauxspring.BindingResult;
import com.hyecheon.introjunit5.model.Owner;
import com.hyecheon.introjunit5.service.OwnerService;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

  @Captor
  ArgumentCaptor<String> stringArgumentCaptor;

  @Test
  void processFindFormWildcardString() {
    //given
    final var owner = new Owner(1L, "Joe", "Buck");
    var ownerList = new ArrayList<Owner>();
    final var captor = ArgumentCaptor.forClass(String.class);
    given(ownerService.findAllByLastNameLike(captor.capture())).willReturn(ownerList);

    //when
    final var viewName = ownerController.processFindForm(owner, bindingResult, null);

    //then
    assertThat("%Buck%").isEqualToIgnoringCase(captor.getValue());

  }

  @Test
  void processFindFormWildcardStringAnnotation() {
    //given
    final var owner = new Owner(1L, "Joe", "Buck");
    var ownerList = new ArrayList<Owner>();

    given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willReturn(ownerList);

    //when
    final var viewName = ownerController.processFindForm(owner, bindingResult, null);

    //then
    assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());

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