package ai.preferred.crawler.sgcarmart;

import ai.preferred.crawler.sgcarmart.model.CarDetails;
import ai.preferred.venom.response.VResponse;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CarDetailsParser {

    public static CarDetails parse(VResponse response) {
        final Document document = response.getJsoup();
        return new FinalResult(
                parseListings(document),
                parseNextPage(document)
        );
    }

    private static List<Listing> parseListings(Document document) {
        final ArrayList<Listing> jobList = new ArrayList<>();

        final Elements jobs = document.select("div.listResults div.-item");

        for (Element job : jobs) {
            final Element title = job.select("div.-job-summary > div.-title > h2 > a").first();
            final String name = title.text();
            final String url = title.attr("abs:href");

            final String company = job.select("div.fc-black-700 span:nth-of-type(1)").first().text();

            final Listing listing = new Listing(url, name, company);

            jobList.add(listing);
        }

        return jobList;
    }

    public static String parseNextPage(Document document) {
        final Element nextPage = document.select("a.prev-next.test-pagination-next").first();
        if (nextPage == null) {
            return null;
        }
        return nextPage.attr("abs:href");
    }

}
