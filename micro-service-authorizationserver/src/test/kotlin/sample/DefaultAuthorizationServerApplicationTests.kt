/*
 * Copyright 2020-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample

import cn.mmooo.*
import org.assertj.core.api.Assertions
import org.htmlunit.Page
import org.htmlunit.WebClient
import org.htmlunit.html.HtmlButton
import org.htmlunit.html.HtmlElement
import org.htmlunit.html.HtmlInput
import org.htmlunit.html.HtmlPage
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.util.UriComponentsBuilder
import java.io.IOException

/**
 * Integration tests for the sample Authorization Server.
 *
 * @author Daniel Garnier-Moiroux
 */
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [DefaultAuthorizationServerApplication::class])
@AutoConfigureMockMvc
class DefaultAuthorizationServerApplicationTests {
    @Autowired
    private lateinit var webClient: WebClient

    @BeforeEach
    fun setUp() {
        webClient.options.isThrowExceptionOnFailingStatusCode = true
        webClient.options.isRedirectEnabled = true
        webClient.cookieManager.clearCookies() // log out
    }

    @Test
    @Throws(IOException::class)
    fun whenLoginSuccessfulThenDisplayNotFoundError() {
        val page = webClient.getPage<HtmlPage>("/")

        assertLoginPage(page)

        webClient.options.isThrowExceptionOnFailingStatusCode = false
        val signInResponse = signIn<Page>(page, "user1", "password").webResponse
        Assertions.assertThat(signInResponse.statusCode)
            .isEqualTo(HttpStatus.NOT_FOUND.value()) // there is no "default" index page
    }

    @Test
    @Throws(IOException::class)
    fun whenLoginFailsThenDisplayBadCredentials() {
        val page = webClient.getPage<HtmlPage>("/")

        val loginErrorPage = signIn<HtmlPage>(page, "user1", "wrong-password")

        val alert = loginErrorPage.querySelector<HtmlElement>("div[role=\"alert\"]")
        Assertions.assertThat(alert).isNotNull()
        Assertions.assertThat(alert.textContent).isEqualTo("Bad credentials")
    }

    @Test
    @Throws(IOException::class)
    fun whenNotLoggedInAndRequestingTokenThenRedirectsToLogin() {
        val page = webClient.getPage<HtmlPage>(AUTHORIZATION_REQUEST)

        assertLoginPage(page)
    }

    @Test
    @Throws(IOException::class)
    fun whenLoggingInAndRequestingTokenThenRedirectsToClientApplication() {
        // Log in
        webClient.options.isThrowExceptionOnFailingStatusCode = false
        webClient.options.isRedirectEnabled = false
        signIn<Page>(webClient.getPage("/login"), "user1", "password")

        // Request token
        val response = webClient.getPage<Page>(AUTHORIZATION_REQUEST).webResponse

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.MOVED_PERMANENTLY.value())
        val location = response.getResponseHeaderValue("location")
        Assertions.assertThat(location).startsWith(REDIRECT_URI)
        Assertions.assertThat(location).contains("code=")
    }

    companion object {
        private const val REDIRECT_URI = "http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc"

        private val AUTHORIZATION_REQUEST: String = UriComponentsBuilder
            .fromPath("/oauth2/authorize")
            .queryParam("response_type", "code")
            .queryParam("client_id", "messaging-client")
            .queryParam("scope", "openid")
            .queryParam("state", "some-state")
            .queryParam("redirect_uri", REDIRECT_URI)
            .toUriString()

        @Throws(IOException::class)
        private fun <P : Page?> signIn(page: HtmlPage, username: String, password: String): P {
            val usernameInput = page.querySelector<HtmlInput>("input[name=\"username\"]")
            val passwordInput = page.querySelector<HtmlInput>("input[name=\"password\"]")
            val signInButton = page.querySelector<HtmlButton>("button")

            usernameInput.type(username)
            passwordInput.type(password)
            return signInButton.click()
        }

        private fun assertLoginPage(page: HtmlPage) {
            Assertions.assertThat(page.url.toString()).endsWith("/login")

            val usernameInput = page.querySelector<HtmlInput>("input[name=\"username\"]")
            val passwordInput = page.querySelector<HtmlInput>("input[name=\"password\"]")
            val signInButton = page.querySelector<HtmlButton>("button")

            Assertions.assertThat(usernameInput).isNotNull()
            Assertions.assertThat(passwordInput).isNotNull()
            Assertions.assertThat(signInButton.textContent).isEqualTo("Sign in")
        }
    }
}
