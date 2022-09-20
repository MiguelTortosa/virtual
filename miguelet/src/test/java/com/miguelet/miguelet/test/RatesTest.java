package com.miguelet.miguelet.test;


import com.fasterxml.jackson.databind.*;
import com.google.gson.*;
import com.miguelet.miguelet.*;
import com.miguelet.miguelet.api.configuration.*;
import com.miguelet.miguelet.api.controller.interfaces.currency.*;
import com.miguelet.miguelet.api.controller.interfaces.rates.*;
import com.miguelet.miguelet.core.exceptions.specific_exception.*;
import com.miguelet.miguelet.core.services.interfaces.*;
import com.miguelet.miguelet.model.*;
import lombok.extern.slf4j.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.data.web.*;
import org.springframework.data.web.config.*;
import org.springframework.http.*;
import org.springframework.test.context.*;
import org.springframework.test.context.testng.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;
import org.testng.*;
import org.testng.annotations.*;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = TestApi.class)
@ActiveProfiles("test")
@Slf4j
@AutoConfigureMockMvc
@EnableSpringDataWebSupport
public class RatesTest extends AbstractTestNGSpringContextTests {

  /**
   * Se obtienen todos los tipos de personas por rol.
   *
   * @throws Exception
   */
@Autowired
  RateController rateController;


 @Autowired RatesService ratesService;
  @Autowired
  private MockMvc mockMvc;
  int idAdd= 0;
  @BeforeSuite
  public static void beforeClass() {

  }
  @AfterSuite
  public static void afterClass() {

  }

  @Test()
  public void AAgetRates() throws Exception {
    MockitoAnnotations.openMocks(this);

    mockMvc =
            MockMvcBuilders.standaloneSetup(rateController)
                    .setControllerAdvice(new CustomizedResponseEntityExceptionHandler()).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                    .build();

    var result =  this.mockMvc
            .perform(
                    get("/v1/rates"))
            .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();




  }


  @Test()
  public void AgetRatesById() throws Exception {
      MockitoAnnotations.openMocks(this);

    mockMvc =
            MockMvcBuilders.standaloneSetup(rateController)
            .setControllerAdvice(new CustomizedResponseEntityExceptionHandler())
            .build();
    this.mockMvc
        .perform(
            get("/v1/rates/1"))
        .andExpect(status().isOk());
  }


  @Test()
  public void gAetRatesByIdNoCOntent() throws Exception {
      MockitoAnnotations.openMocks(this);

    mockMvc =
            MockMvcBuilders.standaloneSetup(rateController)
                    .setControllerAdvice(new CustomizedResponseEntityExceptionHandler())
                    .build();
    this.mockMvc
            .perform(
                    get("/v1/rates/500"))
            .andExpect(status().isNoContent());
  }


  @Test()
  public void AaddRate() throws Exception {
      MockitoAnnotations.openMocks(this);
    RatesDto rate = new RatesDto();
    rate.setCurrency("EUR");
    rate.setBrand("1");
    rate.setProduct("2");
    rate.setPrice("2");
    rate.setStartDate(new Date());
    rate.setEndDate(new Date());
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson=ow.writeValueAsString(rate );
    mockMvc =
            MockMvcBuilders.standaloneSetup(rateController)
                    .setControllerAdvice(new CustomizedResponseEntityExceptionHandler())
                    .build();
    idAdd = Integer.parseInt(this.mockMvc
            .perform(
                    post("/v1/rates").content(requestJson).contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk()).andReturn().getResponse().getContentAsString());
  }

// test de servicio

  @Test()
  public void BAgetRateByIdLastInsert() throws Exception {
    ratesService.getRateById(idAdd);
  }

  @Test(expectedExceptions = {NoContentRequestException.class})
  public void BAgetRateByIdNoContent()  throws Exception {
    ratesService.getRateById(500);
  }

  @Test()
  public void BFilterAndGetRate() throws Exception {

      mockMvc =
              MockMvcBuilders.standaloneSetup(rateController)
                      .setControllerAdvice(new CustomizedResponseEntityExceptionHandler()).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                      .build();
     var date  = new Date();
     Calendar calendar = new GregorianCalendar();
     calendar.setTime(date);
     var dateString = calendar.get(Calendar.DAY_OF_MONTH) + "-0"+ (calendar.get(Calendar.MONTH)+1) +"-"+calendar.get(Calendar.YEAR);

      var result =  this.mockMvc
              .perform(

                      get("/v1/rates?q= startDate: '"+dateString+"' and  brand.id :1 and product.id :1 "))
              .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
  var object =  new JsonParser().parse(result).getAsJsonObject().get("content").getAsJsonArray().get(0).getAsJsonObject().get("id");
      Assert.assertEquals(object,16);
      logger.info(new JsonParser().parse(result).getAsJsonObject().get("content").getAsJsonArray().getAsString());
  }
}
