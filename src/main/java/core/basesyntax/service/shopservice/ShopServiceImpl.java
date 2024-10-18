package core.basesyntax.service.shopservice;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.strategy.OperationHandler;
import core.basesyntax.service.strategy.OperationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShopServiceImpl implements ShopService {
    @Autowired
    private OperationStrategy operationStrategy;

    @Override
    public void process(List<FruitTransaction> fruitTransactions) {
        for (FruitTransaction transaction : fruitTransactions) {
            OperationHandler operationHandler = operationStrategy
                    .getOperation(transaction.getOperation());
            operationHandler.handle(transaction);
        }
    }
}
