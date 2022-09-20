package com.miguelet.miguelet.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.junit.Rule;
import lombok.extern.slf4j.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.stubbing.Scenario;
import org.junit.jupiter.api.*;

@Slf4j
public class WireMockScenarioExampleIntegrationTest {

	private static final String THIRD_STATE = "third";
	private static final String SECOND_STATE = "second";
	private static final String TIP_01 = "finally block is not called when System.exit() is called in the try block";
	private static final String TIP_02 = "keep your code clean";
	private static final String TIP_03 = "use composition rather than inheritance";
	private static final String TEXT_PLAIN = "text/plain";
	private static final String JSON = "application/JSON";

	static int port = 9999;

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(port);

	@Test
	public void changeStateOnEachCallTest() throws IOException {

		configureFor("localhost", port);
		createWireMockStub(Scenario.STARTED, SECOND_STATE, TIP_01);
		//createWireMockStub(SECOND_STATE, THIRD_STATE, TIP_02);
		//createWireMockStub(THIRD_STATE, Scenario.STARTED, TIP_03);

	//	assertEquals(TIP_01, nextTip());
	//	assertEquals(TIP_02, nextTip());
	//	assertEquals(TIP_03, nextTip());
	//	assertEquals(TIP_01, nextTip());
	}

	private void createWireMockStub(String currentState, String nextState, String responseBody) {
		stubFor(get(urlEqualTo("v1/rates"))
				.inScenario("java tips")
				.whenScenarioStateIs(currentState)
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", JSON)
						.withBody(responseBody))
				.willSetStateTo(nextState)
		);
	}

	private String nextTip() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet(String.format("http://localhost:%s/java-tip", port));
		HttpResponse httpResponse = httpClient.execute(request);
		return firstLineOfResponse(httpResponse);
	}

	private static String firstLineOfResponse(HttpResponse httpResponse) throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()))) {
			return reader.readLine();
		}
	}

}



