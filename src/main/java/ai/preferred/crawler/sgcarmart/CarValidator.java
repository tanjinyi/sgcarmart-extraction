package ai.preferred.crawler.sgcarmart;

import ai.preferred.venom.request.Request;
import ai.preferred.venom.response.Response;
import ai.preferred.venom.response.VResponse;
import ai.preferred.venom.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarValidator implements Validator {

  private static final Logger LOGGER = LoggerFactory.getLogger(CarValidator.class);

  /**
   * Use this to positively validate your page.
   * <p>
   * For example, if you are crawling store ABC, you would find.
   * </p>
   *
   * @param request  The request used to fetch.
   * @param response The response fetched using request.
   * @return status of the validation
   */
  @Override
  public Status isValid(Request request, Response response) {
    final VResponse vResponse = new VResponse(response);

    String overview = vResponse.getJsoup().select(
            "#car_menu > li:nth-child(1) > a > span").text();

    if (overview.equals("Overview")) {
      return Status.VALID;
    }

    LOGGER.info("Invalid content");
    return Status.INVALID_CONTENT;
  }
}
