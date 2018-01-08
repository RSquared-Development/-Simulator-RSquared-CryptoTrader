/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Gareth Jon Lynch
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.gazbert.bxbot.rest.api.v1.config;

import com.gazbert.bxbot.core.engine.TradingEngine;
import com.gazbert.bxbot.core.mail.EmailAlerter;
import com.gazbert.bxbot.domain.engine.EngineConfig;
import com.gazbert.bxbot.services.EngineConfigService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests the Engine config controller behaviour.
 *
 * @author gazbert
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestEngineConfigController extends AbstractConfigControllerTest {

    private static final String ENGINE_CONFIG_ENDPOINT_URI = CONFIG_ENDPOINT_BASE_URI + "/engine";
    
    private static final String BOT_ID = "avro-707_1";
    private static final String BOT_NAME = "Avro 707";
    private static final String ENGINE_EMERGENCY_STOP_CURRENCY = "BTC";
    private static final BigDecimal ENGINE_EMERGENCY_STOP_BALANCE = new BigDecimal("0.9232320");
    private static final int ENGINE_TRADE_CYCLE_INTERVAL = 60;

    @MockBean
    private EngineConfigService engineConfigService;

    // Need this even though not used in the test directly because Spring loads it on startup...
    @MockBean
    private TradingEngine tradingEngine;

    // Need this even though not used in the test directly because Spring loads it on startup...
    @MockBean
    private EmailAlerter emailAlerter;

    @Before
    public void setupBeforeEachTest() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).addFilter(springSecurityFilterChain).build();
    }

    @Test
    public void testGetEngineConfig() throws Exception {

        given(engineConfigService.getEngineConfig()).willReturn(someEngineConfig());

        mockMvc.perform(get(ENGINE_CONFIG_ENDPOINT_URI)
                .header("Authorization", buildAuthorizationHeaderValue(VALID_USER_LOGINID, VALID_USER_PASSWORD)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.botId").value(BOT_ID))
                .andExpect(jsonPath("$.botName").value(BOT_NAME))
                .andExpect(jsonPath("$.emergencyStopCurrency").value(ENGINE_EMERGENCY_STOP_CURRENCY))
                .andExpect(jsonPath("$.emergencyStopBalance").value(ENGINE_EMERGENCY_STOP_BALANCE.doubleValue()))
                .andExpect(jsonPath("$.tradeCycleInterval").value(ENGINE_TRADE_CYCLE_INTERVAL));

        verify(engineConfigService, times(1)).getEngineConfig();
    }

    @Test
    public void testGetEngineConfigWhenUnauthorizedWithBadCredentials() throws Exception {

        mockMvc.perform(get(ENGINE_CONFIG_ENDPOINT_URI)
                .header("Authorization", buildAuthorizationHeaderValue(VALID_USER_LOGINID, INVALID_USER_PASSWORD))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetEngineConfigWhenUnauthorizedWithMissingCredentials() throws Exception {

        mockMvc.perform(get(ENGINE_CONFIG_ENDPOINT_URI)
                .header("Authorization", buildAuthorizationHeaderValue(VALID_USER_LOGINID, INVALID_USER_PASSWORD))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testUpdateEngineConfig() throws Exception {

        given(engineConfigService.updateEngineConfig(any())).willReturn(someEngineConfig());

        mockMvc.perform(put(ENGINE_CONFIG_ENDPOINT_URI)
                .header("Authorization", buildAuthorizationHeaderValue(VALID_USER_LOGINID, VALID_USER_PASSWORD))
                .contentType(CONTENT_TYPE)
                .content(jsonify(someEngineConfig())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.botId").value(BOT_ID))
                .andExpect(jsonPath("$.botName").value(BOT_NAME))
                .andExpect(jsonPath("$.emergencyStopCurrency").value(ENGINE_EMERGENCY_STOP_CURRENCY))
                .andExpect(jsonPath("$.emergencyStopBalance").value(ENGINE_EMERGENCY_STOP_BALANCE.doubleValue()))
                .andExpect(jsonPath("$.tradeCycleInterval").value(ENGINE_TRADE_CYCLE_INTERVAL));

        verify(engineConfigService, times(1)).updateEngineConfig(any());
    }

    @Test
    public void testUpdateEngineConfigWhenUnauthorizedWithMissingCredentials() throws Exception {

        mockMvc.perform(put(ENGINE_CONFIG_ENDPOINT_URI)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testUpdateEngineConfigWhenUnauthorizedWithInvalidCredentials() throws Exception {

        mockMvc.perform(put(ENGINE_CONFIG_ENDPOINT_URI)
                .header("Authorization", buildAuthorizationHeaderValue(VALID_USER_LOGINID, INVALID_USER_PASSWORD))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    // ------------------------------------------------------------------------------------------------
    // Private utils
    // ------------------------------------------------------------------------------------------------

    private static EngineConfig someEngineConfig() {
        final EngineConfig engineConfig = new EngineConfig();
        engineConfig.setBotId(BOT_ID);
        engineConfig.setBotName(BOT_NAME);
        engineConfig.setEmergencyStopCurrency(ENGINE_EMERGENCY_STOP_CURRENCY);
        engineConfig.setEmergencyStopBalance(ENGINE_EMERGENCY_STOP_BALANCE);
        engineConfig.setTradeCycleInterval(ENGINE_TRADE_CYCLE_INTERVAL);
        return engineConfig;
    }
}
