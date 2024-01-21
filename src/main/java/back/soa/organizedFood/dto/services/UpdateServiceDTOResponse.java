package back.soa.organizedFood.dto.services;

import back.soa.organizedFood.validations.ValidationResult;

public class UpdateServiceDTOResponse<T> extends AbstractServiceResponse {
    private boolean isUpdated;

    public UpdateServiceDTOResponse(T payload) {
        super(payload);
        this.isUpdated = true;
    }

    protected UpdateServiceDTOResponse(ValidationResult validationResult){
        super(validationResult);
        this.isUpdated = false;
    }

    public boolean isUpdated() {
        return isUpdated;
    }
    @Override
    public T getPayload() {
        return (T) super.getPayload();
    }
}
