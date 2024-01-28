package back.soa.organizedFood.dto.services;

import back.soa.organizedFood.validations.ValidationResultEnum;

public class UpdateServiceResponse<T> extends AbstractServiceResponse {
    private boolean isUpdated;

    public UpdateServiceResponse(ValidationResultEnum validationResult, boolean isUpdated){
        super(validationResult);
        this.isUpdated = isUpdated;
    }

    public boolean isUpdated() {
        return isUpdated;
    }
    @Override
    public T getPayload() {
        return (T) super.getPayload();
    }
}
