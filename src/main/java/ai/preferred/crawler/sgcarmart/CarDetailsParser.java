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
            String value = element.select("td:nth-of-type(2)").get(0).text();
            switch(description) {
                case "price":
                    carDetails.setPrice(Helper.parseStringtoNum(value));
                    break;
                case "depreciation":
                    carDetails.setDepreciation(Helper.parseStringtoNum(value));
                    break;
                case "reg date":
                    carDetails.setRegDate(value);
                    break;
                case "manufactured":
                    carDetails.setManufactured(value);
                    break;
                case "mileage":
                    carDetails.setMileage(Helper.parseStringtoNum(value));
                    break;
                case "transmission":
                    carDetails.setTransmission(value);
                    break;
                case "engine cap":
                    carDetails.setEngineCap(Helper.parseStringtoNum(value));
                    break;
                case "road tax":
                    carDetails.setRoadTax(Helper.parseStringtoNum(value));
                    break;
                case "power":
                    carDetails.setPower(value);
                    break;
                case "curb weight":
                    carDetails.setCurbWeight(Helper.parseStringtoNum(value));
                    break;
                case "features":
                    carDetails.setFeatures(value);
                    break;
                case "accessories":
                    carDetails.setAccessories(value);
                    break;
                case "description":
                    carDetails.setDescription(value);
                    break;
                case "coe":
                    carDetails.setCoe(Helper.parseStringtoNum(value));
                    break;
                case "omv":
                    carDetails.setOmv(Helper.parseStringtoNum(value));
                    break;
                case "arf":
                    carDetails.setArf(Helper.parseStringtoNum(value));
                    break;
                case "dereg value":
                    carDetails.setDeregValue(Helper.parseStringtoNum(value));
                    break;
                case "no. of owners":
                    carDetails.setNoOfOwners(Helper.parseStringtoNum(value));
                    break;
                case "type of veh":
                    carDetails.setTypeOfVeh(value);
                    break;
                case "category":
                    carDetails.setCategory(value);
                    break;
                case "availability":
                    carDetails.setAvailability(value.toLowerCase().contains("available"));
                    break;
            }
        }

        /*final Elements sellerInformation = document.select("#upfrontpayment > table > tbody");
        for (Element element : sellerInformation) { // tr element
            String description = element.selectFirst("td").text().toLowerCase();
            String value = element.select("td:nth-of-type(2)").get(0).text();
            switch(description) {
                case "company":
                    break;
            }

        }*/

        /*final Elements upfrontPayment = document.select("#sellerinfo  > table > tbody");
        for (Element element : upfrontPayment) {

        }*/

        return carDetails;
    }
}
