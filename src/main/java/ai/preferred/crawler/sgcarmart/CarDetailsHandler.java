/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.preferred.crawler.sgcarmart;

import ai.preferred.crawler.sgcarmart.csv.CarDetailsStorage;
import ai.preferred.crawler.sgcarmart.model.CarDetails;
import ai.preferred.venom.Handler;
import ai.preferred.venom.Session;
import ai.preferred.venom.Worker;
import ai.preferred.venom.job.Scheduler;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.request.VRequest;
import ai.preferred.venom.response.VResponse;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * @author Ween Jiann Lee
 */
public class CarDetailsHandler implements Handler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarDetailsHandler.class);

    @Override
    public void handle(Request request, VResponse response, Scheduler scheduler, Session session, Worker worker) {
        LOGGER.info("Processing {}", request.getUrl());

        final ArrayList<CarDetails> carDetails = session.get(ListCrawler.CAR_DETAILS_KEY);

        // Get CSV storage file
        final CarDetailsStorage<CarDetails> csvStorage = session.get(ListCrawler.CSV_STORAGE_KEY);

        // Get HTML and JSoup
        final String html = response.getHtml();
        final Document document = response.getJsoup();

        // We will use a parser class
        final CarDetails car = CarDetailsParser.parse(response);
        LOGGER.info("Found car detail: {}", car.getName());
        carDetails.add(car);
        // Add to the array list
        carDetails.add(car);
        // Write record in CSV
        csvStorage.append(car);
    }
}
