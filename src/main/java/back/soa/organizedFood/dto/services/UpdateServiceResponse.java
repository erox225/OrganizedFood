package back.soa.organizedFood.dto.services;

import back.soa.organizedFood.validations.ValidationResultEnum;

public class UpdateServiceResponse<T> extends AbstractServiceResponse {
    private boolean isUpdated;

    public UpdateServiceResponse(T payload) {
        super(payload);
        this.isUpdated = true;
    }

    protected UpdateServiceResponse(ValidationResultEnum validationResult){
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
