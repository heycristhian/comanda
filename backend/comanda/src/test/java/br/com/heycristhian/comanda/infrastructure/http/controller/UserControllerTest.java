package br.com.heycristhian.comanda.infrastructure.http.controller;

import br.com.heycristhian.comanda.domain.exception.PasswordException;
import br.com.heycristhian.comanda.domain.model.User;
import br.com.heycristhian.comanda.infrastructure.http.dto.request.UserRequest;
import br.com.heycristhian.comanda.mother.user.UserMother;
import br.com.heycristhian.comanda.mother.user.UserRequestMother;
import br.com.heycristhian.comanda.usecase.user.SaveUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.matchesPattern;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends AbstractControllerTest {

    private final static String URL_PATH = "/api/v1/users";

    @MockBean
    private SaveUser saveUser;

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Must save user when calling save user")
    void mustReturn201Created_WhenCallingSaveUser() throws Exception {
        //given:
        var userRequest = UserRequestMother.getUserRequest();
        var user = UserMother.getUser(userRequest);
        when(saveUser.execute(Mockito.any(User.class))).thenReturn(user);

        //when:
        mockMvc.perform(post(URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.usuario").value(userRequest.usuario()))
                .andExpect(jsonPath("$.nomeCompleto").value(userRequest.nomeCompleto()))
                .andReturn();

        //then:
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(saveUser).execute(userArgumentCaptor.capture());
        verifyNoMoreInteractions(saveUser);
    }

    @ParameterizedTest
    @MethodSource("br.com.heycristhian.comanda.mother.user.UserRequestMother#invalidBlankFields")
    @WithMockUser(roles = "USER")
    @DisplayName("Save user must return bad request when sending some blank values")
    void mustReturn400BadRequest_SendingBlankValues_WhenCallingSaveUser(UserRequest userRequest, int size) throws Exception {
        //given:
        var httpStatus = HttpStatus.BAD_REQUEST;

        //when:
        mockMvc.perform(post(URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", equalTo(httpStatus.value())))
                .andExpect(jsonPath("$.message", equalTo(MESSAGE_FIELD_ERRORS)))
                .andExpect(jsonPath("$.correlationId", matchesPattern(UUID_REGEX_PATTERN)))
                .andExpect(jsonPath("$.fields", hasSize(size)));

        //then:
        verifyNoInteractions(saveUser);
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Save user must return bad request sending an invalid email")
    void mustReturn400BadRequest_SendingAnInvalidEmail_WhenCallingSaveUser() throws Exception {
        //give:
        var userRequest = UserRequestMother.getUserRequestWithAnInvalidEmail();

        //when:
        mockMvc.perform(post(URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", equalTo(HttpStatus.BAD_REQUEST.value())))
                .andExpect(jsonPath("$.message", equalTo(MESSAGE_FIELD_ERRORS)))
                .andExpect(jsonPath("$.correlationId", matchesPattern(UUID_REGEX_PATTERN)))
                .andExpect(jsonPath("$.fields", hasSize(1)))
                .andExpect(jsonPath("$.fields[0].field", equalTo("usuario")))
                .andExpect(jsonPath("$.fields[0].message", equalTo(getMessagesProperties("valid.email"))));

        //then:
        verifyNoInteractions(saveUser);
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Save user must return bad request when calling with an invalid password")
    void mustReturnBadRequest_PasswordInvalid_WhenCallingSaveUser() throws Exception {
        //given:
        var userRequest = UserRequestMother.getUserRequestWithAnInvalidPassword();
        var user = UserMother.getUser(userRequest);
        var errorMsg = "Senha invalida";
        when(saveUser.execute(any(User.class))).thenThrow(new PasswordException(errorMsg));

        //when:
        mockMvc.perform(post(URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", equalTo(HttpStatus.BAD_REQUEST.value())))
                .andExpect(jsonPath("$.message", equalTo(errorMsg)))
                .andExpect(jsonPath("$.correlationId", matchesPattern(UUID_REGEX_PATTERN)));

        //then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(saveUser).execute(userArgumentCaptor.capture());
        verifyNoMoreInteractions(saveUser);
    }

    @Test
    @DisplayName("")
    void mustReturn403Forbidden_WhenCallingSaveUser() throws Exception {
        //when:
        mockMvc.perform(post(URL_PATH))
                .andExpect(status().isForbidden());

        //then:
        verifyNoInteractions(saveUser);
    }
}