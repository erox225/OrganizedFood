package back.soa.organizedFood.dto.services;

import back.soa.organizedFood.validations.ValidationResultEnum;

public abstract class AbstractServiceResponse {

    protected Object payload;
    protected ValidationResultEnum validationResultEnum;

    protected AbstractServiceResponse(Object payload){
        this.payload = payload;
        this.validationResultEnum = ValidationResultEnum.VALID_RESULT.getValidationResult();
    }

    protected AbstractServiceResponse(ValidationResultEnum validationResultenum){
        this.validationResultEnum = validationResultenum;
    }

    protected AbstractServiceResponse(Object payload,ValidationResultEnum validationResultenum){
        this.payload = payload;
        this.validationResultEnum = validationResultenum;
    }

    public Object getPayload() {
        return payload;
    }

    public ValidationResultEnum getValidationResult() {
        return validationResultEnum;
    }
}
