package com.shortmarket.system.controller;

import com.shortmarket.system.entities.Cliente;
import com.shortmarket.system.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ClienteController { private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository) {this.clienteRepository = clienteRepository;}

    @GetMapping("/index")
    public String showClienteList(Model model){
        List<Cliente> Cliente = (List<Cliente>) clienteRepository.findAll();
        model.addAttribute("Cliente", Cliente);
        return "index";

    }
    @GetMapping("/add-cliente")
    public String showClienteForm(Cliente cliente) {
        return "add-cliente";
    }

    @PostMapping("/add-cliente")
    public String addCliente(Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteCliente(@PathVariable("id") long id, org.springframework.ui.Model model) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid cliente Id:" + id));
        clienteRepository.delete(cliente);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, org.springframework.ui.Model model) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid cliente Id:" + id));

        model.addAttribute("cliente", cliente);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateCliente(@PathVariable("id") long id, @Valid Cliente cliente,
                            BindingResult result, org.springframework.ui.Model model) {
        if (result.hasErrors()) {
            cliente.setId(id);
            return "update";

        }
        clienteRepository.save(cliente);
        return "redirect:/index";

    }
}
