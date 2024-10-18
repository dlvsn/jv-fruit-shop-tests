package core.basesyntax.service.strategy;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.model.Operation;

public interface OperationHandler {
    void handle(FruitTransaction transaction);

    boolean isApplicable(Operation operation);
}
