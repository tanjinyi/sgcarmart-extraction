package ai.preferred.crawler.sgcarmart.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CarDetailsStorage<T> implements AutoCloseable {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarDetailsStorage.class);

    private final CSVPrinter printer;

    public CarDetailsStorage(String file, Class<T> clazz) throws IOException {
        printer = new CSVPrinter(new FileWriter(file), CSVFormat.EXCEL);
        printer.printRecord(getHeaderList(clazz));
    }

    private static List<String> getHeaderList(Class clazz) {
        final List<String> result = new ArrayList<>();
        for (final Field field : clazz.getDeclaredFields()) {
            result.add(field.getName());
        }
        return result;
    }

    private List<Object> toList(Object object) throws IllegalAccessException {
        final Field[] fields = object.getClass().getDeclaredFields();
        final List<Object> result = new ArrayList<>();
        for (final Field field : fields) {
            field.setAccessible(true);
            result.add(field.get(object));
        }
        return result;
    }

    public synchronized boolean append(T object) {
        try {
            printer.printRecord(toList(object));
            printer.flush();
        } catch (IOException | IllegalAccessException e) {
            LOGGER.error("unable to store property: ", e);
            return false;
        }
        return true;
    }

    @Override
    public void close() throws IOException {
        printer.close(true);
    }

}
