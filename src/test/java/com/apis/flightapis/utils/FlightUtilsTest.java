package com.apis.flightapis.utils;

import com.apis.flightapis.testUtils.TestConstants;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FlightUtilsTest {

    @Test
    void checkStringExistenceSuccess() {
        boolean is_exists = FlightUtils.doesStringValuePresent(TestConstants.TEST_FLIGHT_AIRLINE_NAME);
        assertThat(is_exists).isEqualTo(true);
    }

    @Test
    void checkStringExistenceFailure() {
        boolean is_exists = FlightUtils.doesStringValuePresent("");
        assertThat(is_exists).isEqualTo(false);
        assertThat(FlightUtils.doesStringValuePresent(null)).isFalse();
    }

}