package ai.preferred.crawler.sgcarmart;

import ai.preferred.crawler.sgcarmart.model.CarDetails;
import ai.preferred.venom.response.VResponse;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CarDetailsParser {

    public static CarDetails parse(VResponse response) {
        final Document document = response.getJsoup();
        return parseCarDetails(document);
    }

    private static CarDetails parseCarDetails(Document document) {
        final CarDetails carDetails = new CarDetails();
        final Elements carDetailsTab = document.select("div.box:nth-child(2) > table > tbody");
        for (Element element : carDetailsTab) { // tr element
            String description = element.selectFirst("td").text().toLowerCase();
            switch(description) {
                case "price":
                    carDetails.setPrice();
                    break;
                case "depreciation":
                    break;
                case "reg date":
                    break;
                case "manufactured":
                    break;
                case "mileage":
                    break;
                case "transmission":
                    break;
                case "engine cap":
                    break;
                case "road tax":
                    break;
                case "power":
                    break;
                case "curb weight":
                    break;
                case "features":
                    break;
                case "accessories":
                    break;
                case "description":
                    break;
                case "coe":
                    break;
                case "omv":
                    break;
                case "arf":
                    break;
                case "dereg value":
                    break;
                case "no. of owners":
                    break;
                case "type of veh":
                    break;
                case "category":
                    break;
                case "availability":
                    break;
            }
        }

        final Elements sellerInformation = document.select("#upfrontpayment");
        for (Element element : sellerInformation) {

        }

        final Elements upfrontPayment = document.select("#sellerinfo");
        for (Element element : upfrontPayment) {

        }

        return carDetails;
    }
}
