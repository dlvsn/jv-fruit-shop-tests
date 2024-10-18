package core.basesyntax.service.strategy;

import core.basesyntax.model.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OperationStrategyImpl implements OperationStrategy {
    @Autowired
    private List<OperationHandler> operationHandlers;

    @Override
    public OperationHandler getOperation(Operation operation) {
        return operationHandlers.stream()
                .filter(o -> o.isApplicable(operation))
                .findFirst().orElseThrow(UnsupportedOperationException::new);
    }
}
