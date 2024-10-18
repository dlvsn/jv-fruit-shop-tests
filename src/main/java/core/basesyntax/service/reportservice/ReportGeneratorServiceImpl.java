package core.basesyntax.service.reportservice;

import core.basesyntax.dao.FruitStorageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ReportGeneratorServiceImpl implements ReportGeneratorService {
    private static final String HEADER = "fruit,quantity";
    private static final String COMMA = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    @Autowired
    private FruitStorageDao fruitStorageDao;

    @Override
    public String createReportFromDb() {
        Map<String, Integer> allFruits = fruitStorageDao.getAllFruits();
        return HEADER + LINE_SEPARATOR + allFruits.entrySet().stream()
                .map(e -> e.getKey() + COMMA + e.getValue())
                .collect(Collectors.joining(LINE_SEPARATOR));
    }
}
