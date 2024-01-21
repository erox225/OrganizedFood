package back.soa.organizedFood.dto.services;

import back.soa.organizedFood.validations.ValidationResult;

public class FindOneServiceDTOResponse<T> extends AbstractServiceResponse {

    private boolean isPresent;

    public FindOneServiceDTOResponse(T payload) {
        super(payload);
        this.isPresent = true;
    }

    protected FindOneServiceDTOResponse(ValidationResult validationResult) {
        super(validationResult);
        this.isPresent = false;
    }

    @Override
    public T getPayload() {
        return (T) super.getPayload();
    }

    public boolean isPresent() {
        return isPresent;
    }
}
