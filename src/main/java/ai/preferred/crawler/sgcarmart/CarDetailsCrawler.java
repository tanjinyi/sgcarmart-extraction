/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.preferred.crawler.sgcarmart;

import ai.preferred.crawler.sgcarmart.csv.CarDetailsStorage;
import ai.preferred.crawler.sgcarmart.model.CarDetails;
import ai.preferred.venom.Crawler;
import ai.preferred.venom.Session;
import ai.preferred.venom.fetcher.AsyncFetcher;
import ai.preferred.venom.fetcher.Fetcher;
import ai.preferred.venom.request.VRequest;
import ai.preferred.venom.validator.EmptyContentValidator;
import ai.preferred.venom.validator.StatusOkValidator;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class CarDetailsCrawler {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CarDetailsCrawler.class);

    // Create session keys for things you would like to retrieve from handler
    static final Session.Key<ArrayList<CarDetails>> CAR_DETAILS_KEY = new Session.Key<>();

    // Create session keys for CSV printer to print from handler
    static final Session.Key<CarDetailsStorage<CarDetails>> CSV_STORAGE_KEY = new Session.Key<>();

    public static void main(String[] args) {

        // Get project directory
        String workingDir = System.getProperty("user.dir");

        // Start CSV printer
        try (final CarDetailsStorage<CarDetails> printer = new CarDetailsStorage<>(
                workingDir + "data/results.csv", CarDetails.class)) {

            // Let's init the session, this allows us to retrieve the array list in the handler
            final ArrayList<CarDetails> jobCarDetails = new ArrayList<>();
            final Session session = Session.builder()
                    .put(CAR_DETAILS_KEY, jobCarDetails)
                    .put(CSV_STORAGE_KEY, printer)
                    .build();

            // Start crawler
            try (final Crawler crawler = crawler(fetcher(), session).start()) {
                LOGGER.info("Starting crawler...");

                final String startUrl = "https://stackoverflow.com/jobs?l=Singapore&d=20&u=Km";

                // pass in URL and handler or use a HandlerRouter
                crawler.getScheduler().add(new VRequest(startUrl), new CarDetailsHandler());
            }

            // We will retrieve all the listing here
            LOGGER.info("We have found {} listings!", jobCarDetails.size());

        } catch (Exception e) {
            LOGGER.error("Could not run crawler: ", e);
        }

    }


    private static Fetcher fetcher() {
        // You can look in builder the different things you can add
        return AsyncFetcher.builder()
                .validator(
                        EmptyContentValidator.INSTANCE,
                        StatusOkValidator.INSTANCE,
                        new CarDetailsValidator())
                .build();
    }

    private static Crawler crawler(Fetcher fetcher, Session session) {
        // You can look in builder the different things you can add
        return Crawler.builder()
                .fetcher(fetcher)
                .session(session)
                .build();
    }
}
