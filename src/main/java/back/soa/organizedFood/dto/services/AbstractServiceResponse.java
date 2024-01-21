package back.soa.organizedFood.dto.services;

import back.soa.organizedFood.validations.ValidationResult;
import back.soa.organizedFood.validations.ValidationResultEnum;

public abstract class AbstractServiceResponse {

    protected Object payload;
    protected ValidationResult validationResult;

    protected AbstractServiceResponse(Object payload){
        this.payload = payload;
        this.validationResult = ValidationResultEnum.VALID_RESULT.getValidationResult();
    }

    protected AbstractServiceResponse(ValidationResult validationResult){
        this.validationResult = validationResult;
    }

    public Object getPayload() {
        return payload;
    }

    public ValidationResult getValidationResult() {
        return validationResult;
    }
}
