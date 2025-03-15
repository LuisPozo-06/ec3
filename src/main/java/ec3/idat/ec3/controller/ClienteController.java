package ec3.idat.ec3.controller;

import ec3.idat.ec3.model.ClienteModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClienteController {

    @GetMapping("/descuento")
    public String formularioDescuento(Model model) {
        model.addAttribute("clienteModel", new ClienteModel());
        return "descuento";
    }

    @PostMapping("/calculardescuento")
    public String calcularDescuento(@ModelAttribute("clienteModel") ClienteModel cliente, Model model) {
        Integer años = cliente.getAñosComoCliente();
        Double monto = cliente.getMontoCompra();
        Double descuento = 0.0;

        if (años < 1) {
            descuento = 0.02;
        } else if (años < 3) {
            descuento = 0.05;
        } else if (años < 5) {
            descuento = 0.08;
        } else {
            descuento = 0.12;
        }

        Double total = monto * (1 - descuento);

        model.addAttribute("resultado", "Total con descuento: $" + total);
        return "descuento";
    }
}
