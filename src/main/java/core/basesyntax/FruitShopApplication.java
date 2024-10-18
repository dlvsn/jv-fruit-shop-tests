package core.basesyntax;

import core.basesyntax.config.ConfigApp;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.fileservice.DataConverter;
import core.basesyntax.service.fileservice.DataConverterImpl;
import core.basesyntax.service.fileservice.FileReaderService;
import core.basesyntax.service.fileservice.FileReaderServiceImpl;
import core.basesyntax.service.fileservice.FileWriterService;
import core.basesyntax.service.fileservice.FileWriterServiceImpl;
import core.basesyntax.service.reportservice.ReportGeneratorService;
import core.basesyntax.service.shopservice.ShopService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class FruitShopApplication {
    private static final String FILE_TO_READ = "src/main/resources/reportToRead.csv";
    private static final String FILE_TO_WRITE = "src/main/resources/finalReport.csv";
    private static final AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(ConfigApp.class);

    public static void main(String[] args) {
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        List<String> inputReport = fileReaderService.readFromFile(FILE_TO_READ);

        DataConverter dataConverter = new DataConverterImpl();
        List<FruitTransaction> transactions = dataConverter.convertToTransaction(inputReport);

        ShopService shopService = context.getBean(ShopService.class);
        shopService.process(transactions);

        ReportGeneratorService reportGenerator = context.getBean(ReportGeneratorService.class);
        String finalReport = reportGenerator.createReportFromDb();

        FileWriterService fileWriterService = new FileWriterServiceImpl();
        fileWriterService.writeReportToFile(finalReport, FILE_TO_WRITE);
    }
}
