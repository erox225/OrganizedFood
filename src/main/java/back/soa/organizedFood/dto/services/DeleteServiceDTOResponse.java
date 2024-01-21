package back.soa.organizedFood.dto.services;

import back.soa.organizedFood.validations.ValidationResult;

public class DeleteServiceDTOResponse extends AbstractServiceResponse{

    private boolean isDeleted;

    public DeleteServiceDTOResponse() {
        super(null);
        this.isDeleted = true;
    }

    public DeleteServiceDTOResponse(ValidationResult validationResult){
        super(validationResult);
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
