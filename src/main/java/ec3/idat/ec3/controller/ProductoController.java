package ec3.idat.ec3.controller;

import ec3.idat.ec3.model.ProductoModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Map;

@Controller
public class ProductoController {

    @GetMapping("/impuesto")
    public String formularioImpuesto(Model model) {
        model.addAttribute("productoModel", new ProductoModel());
        return "impuesto";
    }

    @PostMapping("/calcularimpuesto")
    public String calcularImpuesto(@ModelAttribute("productoModel") ProductoModel producto, Model model) {
        String categoria = producto.getCategoria();
        Double precio = producto.getPrecio();

        Map<String, Double> impuestos = Map.of(
                "Electrónica", 0.15,
                "Alimentos", 0.05,
                "Ropa", 0.08,
                "Muebles", 0.10
        );

        Double tasa = impuestos.getOrDefault(categoria, 0.0);
        Double total = precio * (1 + tasa);

        model.addAttribute("resultado", "Total con impuesto: $" + total);
        return "impuesto";
    }
}
