package ec3.idat.ec3.controller;

import ec3.idat.ec3.model.ProductoModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        Double tasa = 0.0;

        if (categoria.equalsIgnoreCase("Electrónica")) {
            tasa = 0.15;
        } else if (categoria.equalsIgnoreCase("Alimentos")) {
            tasa = 0.05;
        } else if (categoria.equalsIgnoreCase("Ropa")) {
            tasa = 0.08;
        } else if (categoria.equalsIgnoreCase("Muebles")) {
            tasa = 0.10;
        }

        Double total = precio * (1 + tasa);

        model.addAttribute("resultado", "Total con impuesto: $" + total);
        return "impuesto";
    }
}
