package ai.preferred.crawler.sgcarmart;

import ai.preferred.venom.request.Request;
import ai.preferred.venom.response.Response;
import ai.preferred.venom.response.VResponse;
import ai.preferred.venom.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListingValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListingValidator.class);

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


        if (request.getUrl().startsWith("https://www.sgcarmart.com/used_cars/listing.php")) {
            String text = vResponse.getJsoup().select(
                    "#footerfollow > ul > li:nth-child(1) > strong").text();

            String regdate = vResponse.getJsoup().select(
                    "#date_attach_menu_parent > div").text();

            if (text.contains("Follow sgCarMart.com") && regdate.contains("Reg Date")) {
                return Status.VALID;
            }

        } else {
            String overview = vResponse.getJsoup().select(
                    "#car_menu > li:nth-child(1) > a > span").text();

            if (overview.equals("Overview")) {
                return Status.VALID;
            }

        }


        LOGGER.info("Invalid content");
        return Status.INVALID_CONTENT;
    }
}
