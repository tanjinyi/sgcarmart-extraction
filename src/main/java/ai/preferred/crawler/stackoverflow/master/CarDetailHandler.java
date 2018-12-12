package ai.preferred.crawler.stackoverflow.master;

import ai.preferred.venom.Handler;
import ai.preferred.venom.Session;
import ai.preferred.venom.Worker;
import ai.preferred.venom.job.Scheduler;
import ai.preferred.venom.request.Request;
import ai.preferred.venom.response.VResponse;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CarDetailHandler implements Handler{

    private static final Logger LOGGER = LoggerFactory.getLogger(CarDetailHandler.class);

    public void handle(Request request, VResponse response, Scheduler scheduler, Session session, Worker worker){
        LOGGER.info("processing: ()", request.getUrl());

        final Document document = response.getJsoup();
        final List<Cars> CarList = CarParser.parse

    }

}
