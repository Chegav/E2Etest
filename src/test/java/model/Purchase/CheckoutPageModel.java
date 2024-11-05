package model.Purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CheckoutPageModel {

    private String nombre;
    private String apellido;
    private String codigoPostal;

    public static CheckoutPageModel fromMap(Map<String, String> entry) {
        CheckoutPageModel inputData = new CheckoutPageModel();

        inputData.setNombre(entry.get("Nombre"));
        inputData.setApellido(entry.get("Apellido"));
        inputData.setCodigoPostal(entry.get("CodigoPostal"));



        return inputData;
    }
}
