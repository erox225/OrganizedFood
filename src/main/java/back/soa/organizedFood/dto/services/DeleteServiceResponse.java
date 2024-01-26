package back.soa.organizedFood.dto.services;

import back.soa.organizedFood.validations.ValidationResultEnum;

public class DeleteServiceResponse extends AbstractServiceResponse{

    private boolean isDeleted;

    public DeleteServiceResponse(Object payload, ValidationResultEnum validationResultenum) {
        super(null,validationResultenum);
        this.isDeleted = true;
    }

    public DeleteServiceResponse(ValidationResultEnum validationResult){
        super(validationResult);
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
