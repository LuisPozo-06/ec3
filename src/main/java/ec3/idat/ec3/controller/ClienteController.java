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
        Double descuento = (años < 1) ? 0.02 :
                (años < 3) ? 0.05 :
                        (años < 5) ? 0.08 : 0.12;
        Double total = monto * (1 - descuento);

        model.addAttribute("resultado", "Total con descuento: $" + total);
        return "descuento";
    }
}
