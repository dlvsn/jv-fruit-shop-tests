package core.basesyntax.service.strategy;

import core.basesyntax.dao.FruitStorageDao;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.model.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BalanceOperation implements OperationHandler {
    @Autowired
    private FruitStorageDao storageDao;

    @Override
    public void handle(FruitTransaction transaction) {
        storageDao.update(transaction.getFruit(), transaction.getQuantity());
    }

    @Override
    public boolean isApplicable(Operation operation) {
        return operation.equals(Operation.BALANCE);
    }
}
