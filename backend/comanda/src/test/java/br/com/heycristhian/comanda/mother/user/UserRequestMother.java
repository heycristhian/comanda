package br.com.heycristhian.comanda.mother.user;

import br.com.heycristhian.comanda.infrastructure.http.dto.request.UserRequest;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public abstract class UserRequestMother {

    private static final String USERNAME = "heycristhian@gmail.com";
    private static final String FULL_NAME = "Cristhian Nunes Dias";
    private static final String PASSWORD = "@EhOCristhian010101";

    public static UserRequest getUserRequest() {
        return new UserRequest(USERNAME, FULL_NAME, PASSWORD);
    }

    public static UserRequest getUserRequestWithAnInvalidEmail() {
        return new UserRequest("look_at_him", FULL_NAME, PASSWORD);
    }

    public static UserRequest getUserRequestWithAnInvalidPassword() {
        return new UserRequest(USERNAME, FULL_NAME, "minhasenhafraca");
    }

    public static Stream<Arguments> invalidBlankFields() {
        return Stream.of(
                Arguments.of(getUserRequestUsernameNull(), 1),
                Arguments.of(getUserRequestUsernameEmpty(), 1),
                Arguments.of(getUserRequestFullNameNull(), 1),
                Arguments.of(getUserRequestFullNameEmpty(), 1),
                Arguments.of(getUserRequestPasswordNull(), 1),
                Arguments.of(getUserRequestPasswordEmpty(), 1),
                Arguments.of(getUserRequestAllFieldsNull(), 3),
                Arguments.of(getUserRequestAllFieldsEmpty(), 3)
        );
    }

    private static UserRequest getUserRequestUsernameNull() {
        return new UserRequest(null, FULL_NAME, PASSWORD);
    }

    private static UserRequest getUserRequestUsernameEmpty() {
        return new UserRequest("", FULL_NAME, PASSWORD);
    }

    private static UserRequest getUserRequestFullNameNull() {
        return new UserRequest(USERNAME, null, PASSWORD);
    }

    private static UserRequest getUserRequestFullNameEmpty() {
        return new UserRequest(USERNAME, "", PASSWORD);
    }

    private static UserRequest getUserRequestPasswordNull() {
        return new UserRequest(USERNAME, FULL_NAME, null);
    }

    private static UserRequest getUserRequestPasswordEmpty() {
        return new UserRequest(USERNAME, FULL_NAME, "");
    }

    private static UserRequest getUserRequestAllFieldsNull() {
        return new UserRequest(null, null, null);
    }

    private static UserRequest getUserRequestAllFieldsEmpty() {
        return new UserRequest("", "", "");
    }
}
