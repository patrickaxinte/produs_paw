package com.example.productmanagement.controller;

import com.example.productmanagement.model.Produs;
import com.example.productmanagement.repository.ProdusRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// controller REST pentru CRUD produse
@RestController
@RequestMapping("/api/produse")
public class ProdusController {

    private final ProdusRepository produsRepository;

    // Constructor injection
    public ProdusController(ProdusRepository produsRepository) {
        this.produsRepository = produsRepository;
    }


    @GetMapping
    public List<Produs> obtineToateProdusele() {
        return produsRepository.findAll();
    }

    // obtine un produs dupa ID
    @GetMapping("/{id}")
    public Produs obtineProdusDupaId(@PathVariable Long id) {
        return produsRepository.findById(id).orElse(null);
    }

    // creeaza un nou produs
    @PostMapping
    public Produs creeazaProdus(@RequestBody Produs produs) {
        return produsRepository.save(produs);
    }

    // actualizeaza un produs existent
    @PutMapping("/{id}")
    public Produs actualizeazaProdus(@PathVariable Long id, @RequestBody Produs produsActualizat) {
        return produsRepository.findById(id)
                .map(produs -> {
                    produs.setNume(produsActualizat.getNume());
                    produs.setDescriere(produsActualizat.getDescriere());
                    produs.setPret(produsActualizat.getPret());
                    return produsRepository.save(produs);
                })
                .orElseGet(() -> {
                    produsActualizat.setId(id);
                    return produsRepository.save(produsActualizat);
                });
    }

    // sterge un produs dupa ID
    @DeleteMapping("/{id}")
    public void stergeProdus(@PathVariable Long id) {
        produsRepository.deleteById(id);
    }
}
