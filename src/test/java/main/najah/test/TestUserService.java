package main.najah.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import main.najah.code.UserService;

@DisplayName("TestUserService")
@Execution(ExecutionMode.CONCURRENT)
public class TestUserService {

    private final UserService userService = new UserService();

    static Stream<Arguments> emailData() {
        return Stream.of(
            Arguments.of("user@mail.com", true),
            Arguments.of("a@b.c", true),
            Arguments.of("bademail", false),
            Arguments.of("user@", false),
            Arguments.of("user.domain", false),
            Arguments.of("", false),
            Arguments.of(null, false)
        );
    }

    @DisplayName("isValidEmail returns expected result (parameterized)")
    @ParameterizedTest(name = "email={0}, expected={1}")
    @MethodSource("emailData")
    void isValidEmail_validAndInvalid(String email, boolean expected) {
        if (expected) {
            assertTrue(userService.isValidEmail(email));
        } else {
            assertFalse(userService.isValidEmail(email));
        }
    }

    @DisplayName("authenticate returns true for valid credentials and false for invalid")
    @Test
    void authenticate_validAndInvalid() {
        assertTrue(userService.authenticate("admin", "1234"));
        assertFalse(userService.authenticate("admin", "wrong"));
    }

    @DisplayName("authenticate rejects wrong username and null values")
    @Test
    void authenticate_additionalInvalidCases() {
        assertFalse(userService.authenticate("user", "1234"));
        assertFalse(userService.authenticate(null, "1234"));
        assertFalse(userService.authenticate("admin", null));
    }
}
