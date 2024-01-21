package back.soa.organizedFood.dto.dashboard;

import back.soa.organizedFood.dto.controller.Home.HomeRequestDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
public class GetAllInfoDespensaResponseDTO {
    private String result;
    private String description;
    private List<HomeRequestDTO> hogares;

    // Constructores, métodos getter y setter según sea necesario
}
