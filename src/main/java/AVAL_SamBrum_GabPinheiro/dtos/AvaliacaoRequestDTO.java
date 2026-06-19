package AVAL_SamBrum_GabPinheiro.dtos;

import jakarta.validation.constraints.NotBlank;

public class AvaliacaoRequestDTO {

    @NotBlank(message = "O campo 'id' é obrigatório")
    private Long id;

    private Long userId;

    


}
