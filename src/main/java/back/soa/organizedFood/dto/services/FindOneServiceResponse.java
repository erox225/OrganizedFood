package back.soa.organizedFood.dto.services;

import back.soa.organizedFood.validations.ValidationResultEnum;

import java.util.List;

public class FindOneServiceResponse<T> extends AbstractServiceResponse {

    private boolean isPresent;

    public FindOneServiceResponse(T payload) {
        super(payload);
        this.isPresent = true;
    }

    public FindOneServiceResponse(ValidationResultEnum validationResultEnum) {
        super(validationResultEnum);
    }

    @Override
    public T getPayload() {
        return (T) super.getPayload();
    }

    public boolean isPresent() {
        return isPresent;
    }
}
