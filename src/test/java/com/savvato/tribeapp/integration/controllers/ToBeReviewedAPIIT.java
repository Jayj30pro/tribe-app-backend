package com.savvato.tribeapp.integration.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.savvato.tribeapp.config.SecurityConfig;
import com.savvato.tribeapp.config.principal.UserPrincipal;
import com.savvato.tribeapp.constants.PhraseTestConstants;
import com.savvato.tribeapp.constants.UserTestConstants;
import com.savvato.tribeapp.controllers.ToBeReviewedAPIController;
import com.savvato.tribeapp.dto.ToBeReviewedDTO;
import com.savvato.tribeapp.entities.User;
import com.savvato.tribeapp.repositories.ToBeReviewedRepository;
import com.savvato.tribeapp.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Type;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ToBeReviewedAPIController.class)
@Import(SecurityConfig.class)
public class ToBeReviewedAPIIT implements UserTestConstants, PhraseTestConstants {

  private UserPrincipal userPrincipal;
  private User user;
  @Autowired private MockMvc mockMvc;

  @MockBean private AuthService authService;

  @Autowired private WebApplicationContext webApplicationContext;

  @Autowired private Gson gson;

  @MockBean private UserDetailsServiceTRIBEAPP userDetailsServiceTRIBEAPP;

  @MockBean private UserPrincipalService userPrincipalService;

  @MockBean private ToBeReviewedService toBeReviewedService;
  @MockBean private ToBeReviewedRepository toBeReviewedRepository;

  @Captor private ArgumentCaptor<Long> notificationIdCaptor;

  @BeforeEach
  public void setUp() throws Exception {
    mockMvc =
        MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
            .apply(springSecurity())
            .build();

    user = UserTestConstants.getUser3();
    
  }

  @Test
  public void getPhraseHappyPath() throws Exception {
    when(userPrincipalService.getUserPrincipalByEmail(Mockito.anyString()))
        .thenReturn(new UserPrincipal(user));
    String auth = AuthServiceImpl.generateAccessToken(user);

    ToBeReviewedDTO expectedTbr =
        ToBeReviewedDTO.builder()
            .adverb(ADVERB1_WORD)
            .verb(VERB1_WORD)
            .noun(NOUN1_WORD)
            .preposition(PREPOSITION1_WORD)
            .build();
    when(toBeReviewedService.getReviewPhrase()).thenReturn(Optional.of(expectedTbr));

    MvcResult result =
        this.mockMvc
            .perform(
                get("/api/review")
                    .header("Authorization", "Bearer " + auth)
                    .characterEncoding("utf-8"))
            .andExpect(status().isOk())
            .andReturn();

    Type toBeReviewedDTOType = new TypeToken<ToBeReviewedDTO>() {}.getType();
    ToBeReviewedDTO actualTbr =
        gson.fromJson(result.getResponse().getContentAsString(), toBeReviewedDTOType);
    assertThat(actualTbr).usingRecursiveComparison().isEqualTo(expectedTbr);
    verify(toBeReviewedService, times(1)).getReviewPhrase();
  }

  @Test
  public void getPhraseWhenNoneFound() throws Exception {
    when(userPrincipalService.getUserPrincipalByEmail(Mockito.anyString()))
        .thenReturn(new UserPrincipal(user));
    String auth = AuthServiceImpl.generateAccessToken(user);

    when(toBeReviewedService.getReviewPhrase()).thenReturn(Optional.empty());

    this.mockMvc
        .perform(
            get("/api/review").header("Authorization", "Bearer " + auth).characterEncoding("utf-8"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$").doesNotExist())
        .andReturn();

    verify(toBeReviewedService, times(1)).getReviewPhrase();
  }
}
