package ai.preferred.crawler.sgcarmart;

import ai.preferred.crawler.sgcarmart.csv.CarDetailsStorage;
import ai.preferred.crawler.sgcarmart.model.CarDetails;
import ai.preferred.venom.Crawler;
import ai.preferred.venom.Session;
import ai.preferred.venom.SleepScheduler;
import ai.preferred.venom.fetcher.AsyncFetcher;
import ai.preferred.venom.fetcher.Fetcher;
import ai.preferred.venom.request.VRequest;
import ai.preferred.venom.validator.EmptyContentValidator;
import ai.preferred.venom.validator.StatusOkValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListCrawler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListCrawler.class);

    static final Session.Key<List<CarDetails>> CAR_KEY = new Session.Key<>();
    static final Session.Key<CarDetailsStorage<CarDetails>> CSV_KEY = new Session.Key<>();

    public static void main(String[] args) {
        final String filename = "/data/car.csv";
        String workingDir = System.getProperty("user.dir");
        try (final CarDetailsStorage<CarDetails> storage = new CarDetailsStorage<>
                (workingDir + filename, CarDetails.class)) {
            final List<CarDetails> carDetailsList = new ArrayList<>();
            final Session session = Session.builder()
                    .put(CAR_KEY, carDetailsList)
                    .put(CSV_KEY, storage)
                    .build();

            try (final Crawler crawler = crawler(fetcher(), session).start()) {
                LOGGER.info("starting crawler...");

                final String startUrl = "https://www.sgcarmart.com/used_cars/listing.php";
                crawler.getScheduler().add(new VRequest(startUrl), new CarListHandler());
            } catch (Exception e) {
                LOGGER.error("Could not run crawler: ", e);
            }

        } catch (IOException e) {
            LOGGER.error("unable to open file: {}, {}", filename, e);
        }
    }

    private static Fetcher fetcher() {
        return AsyncFetcher.builder()
                .validator(
                        EmptyContentValidator.INSTANCE,
                        StatusOkValidator.INSTANCE,
                        new ListingValidator())
                .build();
    }

    private static Crawler crawler(Fetcher fetcher, Session session) {
        return Crawler.builder()
                .fetcher(fetcher)
                .session(session)
                // Just to be polite
                .sleepScheduler(new SleepScheduler(1500, 3000))
                .build();
    }

}
