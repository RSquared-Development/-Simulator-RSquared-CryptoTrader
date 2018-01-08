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

import com.gazbert.bxbot.domain.emailalerts.EmailAlertsConfig;
import com.gazbert.bxbot.services.EmailAlertsConfigService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.gazbert.bxbot.rest.api.v1.config.AbstractConfigController.CONFIG_ENDPOINT_BASE_URI;

/**
 * <p>
 * Controller for directing Email Alerts config requests.
 * <p>
 * Email Alerts config can only be fetched and updated - it cannot be deleted or created.
 * <p>
 * There is only 1 Email Alerter per bot.
 *
 * @author gazbert
 * @since 1.0
 */
@RestController
@RequestMapping(CONFIG_ENDPOINT_BASE_URI)
public class EmailAlertsConfigController extends AbstractConfigController {

    private static final Logger LOG = LogManager.getLogger();
    private static final String EMAIL_ALERTS_RESOURCE_PATH = "/email-alerts";
    private final EmailAlertsConfigService emailAlertsConfigService;

    @Autowired
    public EmailAlertsConfigController(EmailAlertsConfigService emailAlertsConfigService) {
        this.emailAlertsConfigService = emailAlertsConfigService;
    }

    /**
     * Returns the Email Alerts configuration for the bot.
     *
     * @param user the authenticated user making the request.
     * @return the Email Alerts configuration.
     */
    @RequestMapping(value = EMAIL_ALERTS_RESOURCE_PATH, method = RequestMethod.GET)
    public EmailAlertsConfig getEmailAlerts(@AuthenticationPrincipal User user) {

        LOG.info("GET " + EMAIL_ALERTS_RESOURCE_PATH + " - getEmailAlerts() - caller: " + user.getUsername());

        final EmailAlertsConfig emailAlertsConfig = emailAlertsConfigService.getEmailAlertsConfig();

        LOG.info("Response: " + emailAlertsConfig);
        return emailAlertsConfig;
    }

    /**
     * Updates the Email Alerts configuration for the bot.
     *
     * @param user   the authenticated user making the request.
     * @param config the Email Alerts config to update.
     * @return 200 'OK' HTTP status code and Email Alerts config in response body if update successful,
     * some other HTTP status code otherwise.
     */
    @RequestMapping(value = EMAIL_ALERTS_RESOURCE_PATH, method = RequestMethod.PUT)
    public ResponseEntity<?> updateEmailAlerts(@AuthenticationPrincipal User user, @RequestBody EmailAlertsConfig config) {

        LOG.info("PUT " + EMAIL_ALERTS_RESOURCE_PATH + " - updateEmailAlerts() - caller: " + user.getUsername());
        LOG.info("Request: " + config);

        final EmailAlertsConfig updatedConfig = emailAlertsConfigService.updateEmailAlertsConfig(config);
        return buildResponseEntity(updatedConfig, HttpStatus.OK);
    }
}

