package com.shortmarket.system.controller;

import com.shortmarket.system.entities.Produto;
import com.shortmarket.system.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProdutoController {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);
    private final ProdutoRepository produtoRepository;

    @GetMapping("/produto")
    public String showProdutoList(Model model) {
        List<Produto> produto = (List<Produto>) produtoRepository.findAll();
        model.addAttribute("produto", produto);
        return "produto";
    }

    @GetMapping("/add-produto")
    public String showProdutoForm(Produto produto) {
        return "add-produto";
    }

    @PostMapping("/add-produto")
    public String addProduto(@Valid Produto produto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-produto";
        }
        produtoRepository.save(produto);
        return "redirect:/produto";
    }

    @GetMapping("/deleteProduto/{id}")
    public String deleteProduto(@PathVariable("id") long id, Model model) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid produto Id:" + id));
        produtoRepository.delete(produto);
        return "redirect:/produto";
    }

    @GetMapping("/edit-produto/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        try {
            Produto produto = produtoRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid produto Id:" + id));
            model.addAttribute("produto", produto);
            return "edit-produto";
        } catch (Exception e) {
            logger.error("Erro ao editar produto", e);
            model.addAttribute("errorMessage", "Erro ao buscar o produto para edição");
            return "error";
        }
    }

    @PostMapping("/updateProduto/{id}")
    public String updateProduto(@PathVariable("id") long id, @Valid Produto produto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            produto.setId(id);
            model.addAttribute("produto", produto);
            return "edit-produto";
        }
        try {
            produtoRepository.save(produto);
            return "redirect:/produto";
        } catch (Exception e) {
            logger.error("Erro ao atualizar produto", e);
            model.addAttribute("errorMessage", "Erro ao atualizar o produto");
            return "produto";
        }
    }
}
