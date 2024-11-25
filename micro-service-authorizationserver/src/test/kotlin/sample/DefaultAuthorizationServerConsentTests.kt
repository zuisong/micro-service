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
import org.htmlunit.html.DomElement
import org.htmlunit.html.DomNode
import org.htmlunit.html.HtmlCheckBoxInput
import org.htmlunit.html.HtmlPage
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.util.UriComponentsBuilder
import java.io.IOException
import java.util.function.Consumer

/**
 * Consent screen integration tests for the sample Authorization Server.
 *
 * @author Dmitriy Dubson
 */
@ExtendWith(SpringExtension::class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [DefaultAuthorizationServerApplication::class]
)
@AutoConfigureMockMvc
class DefaultAuthorizationServerConsentTests {
  @Autowired
  private lateinit var webClient: WebClient

  @MockitoBean
  private val authorizationConsentService: OAuth2AuthorizationConsentService? = null

  private val redirectUri = "http://127.0.0.1/login/oauth2/code/messaging-client-oidc"

  private val authorizationRequestUri: String =
    UriComponentsBuilder.fromPath("/oauth2/authorize").queryParam("response_type", "code")
      .queryParam("client_id", "messaging-client").queryParam("scope", "openid message.read message.write")
      .queryParam("state", "state").queryParam("redirect_uri", this.redirectUri).toUriString()

  @BeforeEach
  fun setUp() {
    webClient.options.isThrowExceptionOnFailingStatusCode = false
    webClient.options.isRedirectEnabled = true
    webClient.cookieManager.clearCookies()
    Mockito.`when`(authorizationConsentService!!.findById(ArgumentMatchers.any(), ArgumentMatchers.any()))
      .thenReturn(null)
  }

  @Test
  @WithMockUser("user1")
  @Throws(IOException::class)
  fun whenUserConsentsToAllScopesThenReturnAuthorizationCode() {
    val consentPage = webClient.getPage<HtmlPage>(this.authorizationRequestUri)
    Assertions.assertThat(consentPage.titleText).isEqualTo("Consent required")

    val scopes: MutableList<HtmlCheckBoxInput> = ArrayList()
    consentPage.querySelectorAll("input[name='scope']")
      .forEach(Consumer { scope: DomNode -> scopes.add(scope as HtmlCheckBoxInput) })
    for (scope in scopes) {
      scope.click<Page>()
    }

    val scopeIds: MutableList<String> = ArrayList()
    scopes.forEach(Consumer { scope: HtmlCheckBoxInput ->
      Assertions.assertThat(scope.isChecked).isTrue()
      scopeIds.add(scope.id)
    })
    Assertions.assertThat(scopeIds).containsExactlyInAnyOrder("message.read", "message.write")

    val submitConsentButton = consentPage.querySelector<DomElement>("button[id='submit-consent']")
    webClient.options.isRedirectEnabled = false

    val approveConsentResponse = submitConsentButton.click<Page>().webResponse
    Assertions.assertThat(approveConsentResponse.statusCode).isEqualTo(HttpStatus.MOVED_PERMANENTLY.value())
    val location = approveConsentResponse.getResponseHeaderValue("location")
    Assertions.assertThat(location).startsWith(this.redirectUri)
    Assertions.assertThat(location).contains("code=")
  }

  @Test
  @WithMockUser("user1")
  @Throws(IOException::class)
  fun whenUserCancelsConsentThenReturnAccessDeniedError() {
    val consentPage = webClient.getPage<HtmlPage>(this.authorizationRequestUri)
    Assertions.assertThat(consentPage.titleText).isEqualTo("Consent required")

    val cancelConsentButton = consentPage.querySelector<DomElement>("button[id='cancel-consent']")
    webClient.options.isRedirectEnabled = false

    val cancelConsentResponse = cancelConsentButton.click<Page>().webResponse
    Assertions.assertThat(cancelConsentResponse.statusCode).isEqualTo(HttpStatus.MOVED_PERMANENTLY.value())
    val location = cancelConsentResponse.getResponseHeaderValue("location")
    Assertions.assertThat(location).startsWith(this.redirectUri)
    Assertions.assertThat(location).contains("error=access_denied")
  }
}
