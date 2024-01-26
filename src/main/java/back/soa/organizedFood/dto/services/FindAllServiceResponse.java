package back.soa.organizedFood.dto.services;

import back.soa.organizedFood.validations.ValidationResultEnum;

import java.util.List;

public class FindAllServiceResponse<T>  extends AbstractServiceResponse{
    public FindAllServiceResponse(List<T> payload) {
        super(payload);
    }

    public FindAllServiceResponse(ValidationResultEnum validationResultEnum) {
        super(validationResultEnum);
    }

    @Override
    public List<T> getPayload() {
        return (List<T>) super.getPayload();
    }
}
