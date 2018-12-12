package ai.preferred.crawler.iproperty.master;

import ai.preferred.crawler.iproperty.entity.Property;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ListingParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListingParser.class);

    public static List<Property> parseListing(Document document) {
        final Elements properties = document.select("ul.listing-list.dZUSMd > li[class~=rent]");
        final ArrayList<Property> result = new ArrayList<>(properties.size());
        for (final Element p : properties) {
            result.add(parseProperty(p));
        }
        return result;
    }

    private static String textOrNull(Element element) {
        return null == element ? null : element.text();
    }

    private static Integer intOrNull(Element element) {
        if (element == null) {
            return null;
        }
        try {
            return Integer.parseInt(element.text());
        } catch (NumberFormatException e) {
            LOGGER.error("could not parse integer", e);
            return null;
        }
    }

    private static Property parseProperty(Element e) {
        final Property property = new Property();

        property.setPrice(textOrNull(e.select("li[class~=listing-primary-price-item]").first()));
        property.setArea(textOrNull(e.select("li.attributes-price-per-unit-size-item.fsbnan > a").first()));
        property.setPsf(textOrNull(e.select("p[class~=secondary-price ]").first()));

        for (final Element facility : e.select("li.attributes-facilities-item-wrapper")) {
            if (facility.hasClass("bedroom-facility")) {
                property.setNumBeds(intOrNull(facility));
            } else if (facility.hasClass("bathroom-facility")) {
                property.setNumBaths(intOrNull(facility));
            } else if (facility.hasClass("carPark-facility")) {
                property.setCarpark(textOrNull(facility));
            } else {
                LOGGER.info("unrecognized facility: {}", facility.text());
            }
        }

        if (e.select("div.gPrykj").isEmpty()) {
            property.setAddress(e.select("p.row-one-left-col-listing-location").text());
            property.setTitle(e.select("h3.cgiArp").text());
            property.setUrl(e.select("h3.cgiArp > a").attr("abs:href"));
            property.setType(e.select("p.property-type-content").text());
        } else {
            property.setAddress(e.select("div.fsKEtj > a").text());
            property.setTitle(e.select("p.jPjrzv").text());
            property.setUrl(e.select("p.jPjrzv > a").attr("abs:href"));
            property.setType(e.select("div.eqkyrG").text());
            property.setPsf(textOrNull(e.select("div.boastAt > p > a").first()));
        }

        return property;
    }

    private ListingParser() {
        throw new AssertionError();
    }

}
