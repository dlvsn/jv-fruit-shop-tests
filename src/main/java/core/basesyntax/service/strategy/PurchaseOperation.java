package core.basesyntax.service.strategy;

import core.basesyntax.dao.FruitStorageDao;
import core.basesyntax.exception.NotEnoughProductsException;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.model.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseOperation implements OperationHandler {
    @Autowired
    private FruitStorageDao storageDao;

    @Override
    public void handle(FruitTransaction transaction) {
        int currentFruitQuantity = storageDao.getFruitQuantity(transaction.getFruit());
        int purchaseResult = currentFruitQuantity - transaction.getQuantity();
        if (purchaseResult < 0) {
            throw new NotEnoughProductsException("Not enough fruits: "
                        + transaction.getFruit()
                        + ": "
                        + transaction.getQuantity()
                        + " available: "
                        + currentFruitQuantity);
        }
        storageDao.update(transaction.getFruit(), purchaseResult);
    }

    @Override
    public boolean isApplicable(Operation operation) {
        return operation.equals(Operation.PURCHASE);
    }
}
