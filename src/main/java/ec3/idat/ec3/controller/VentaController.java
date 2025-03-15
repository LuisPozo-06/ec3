package ec3.idat.ec3.controller;

import ec3.idat.ec3.model.VentaModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VentaController {

    @GetMapping("/bonificacion")
    public String formularioBonificacion(Model model) {
        model.addAttribute("ventaModel", new VentaModel());
        return "bonificacion";
    }

    @PostMapping("/calcularbonificacion")
    public String calcularBonificacion(@ModelAttribute("ventaModel") VentaModel venta, Model model) {
        Integer dias = venta.getDiasParaPagar();
        Double monto = venta.getMontoCompra();
        Double descuento = 0.0;

        if (dias < 7) {
            descuento = 0.10;
        } else if (dias <= 15) {
            descuento = 0.05;
        }

        Double total = monto * (1 - descuento);

        model.addAttribute("resultado", "Total con bonificación: $" + total);
        return "bonificacion";
    }
}
