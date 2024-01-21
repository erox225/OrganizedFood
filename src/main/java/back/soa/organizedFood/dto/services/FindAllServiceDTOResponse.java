package back.soa.organizedFood.dto.services;

import java.util.List;

public class FindAllServiceDTOResponse<T>  extends AbstractServiceResponse{
    public FindAllServiceDTOResponse(List<T> payload) {
        super(payload);
    }

    @Override
    public List<T> getPayload() {
        return (List<T>) super.getPayload();
    }
}
