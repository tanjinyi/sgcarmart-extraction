package ai.preferred.crawler.sgcarmart;

import ai.preferred.venom.Handler;
import ai.preferred.venom.Session;
import ai.preferred.venom.Worker;
import ai.preferred.venom.job.Scheduler;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.request.VRequest;
import ai.preferred.venom.response.VResponse;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarListHandler implements Handler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarListHandler.class);

    @Override
    public void handle(Request request, VResponse response, Scheduler scheduler, Session session, Worker worker) {
        LOGGER.info("processing: {}", request.getUrl());

        final Document document = response.getJsoup();
        final String selector = "#contentblank > table > tbody > tr > td > table > tbody > tr > td:nth-child(6) > table > tbody > tr > td > table > tbody > tr:nth-child(1) > td:nth-child(2) > table > tbody > tr > td:nth-child(2) > div > strong > a";
        final Elements CarList = document.select(selector);
        if (CarList.isEmpty()) {
            LOGGER.info("there is no results on this page, stopping: {}", request.getUrl());
            return;
        }

        for (final Element c : CarList) {
            LOGGER.info("storing property: {} [{}]", c.attr("abs:href"));
            scheduler.add(new VRequest(c.attr("abs:href")), new CarDetailsHandler());
        }

        String page_selector = "#contentblank > div:nth-child(4) > div:nth-child(4) > a:nth-last-child(1)";
        String nextPageUrl = document.select(page_selector).attr("abs:href");

        scheduler.add(new VRequest(nextPageUrl), this);
    }
}


