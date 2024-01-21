package back.soa.organizedFood.dto.services;

import back.soa.organizedFood.validations.ValidationResult;

public class CreateServiceDTOResponse<T> extends AbstractServiceResponse{

    private boolean isCreated;

    public CreateServiceDTOResponse(T payload) {
        super(payload);
        this.isCreated = true;
    }

    protected CreateServiceDTOResponse(ValidationResult validationResult){
        super(validationResult);
        this.isCreated = false;
    }

    @Override
    public T getPayload() {
        return (T) super.getPayload();
    }

    public boolean isCreated() {
        return isCreated;
    }
}
