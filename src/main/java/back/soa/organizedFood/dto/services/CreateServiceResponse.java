package back.soa.organizedFood.dto.services;

import back.soa.organizedFood.validations.ValidationResultEnum;

public class CreateServiceResponse<T> extends AbstractServiceResponse{

    private boolean isCreated;

    public CreateServiceResponse(T payload) {
        super(payload);
        this.isCreated = true;
    }

    public CreateServiceResponse(ValidationResultEnum validationResult,boolean isCreated){
        super(validationResult);
        this.isCreated = isCreated;
    }

    @Override
    public T getPayload() {
        return (T) super.getPayload();
    }

    public boolean isCreated() {
        return isCreated;
    }
}
