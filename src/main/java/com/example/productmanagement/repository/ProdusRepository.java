package com.example.productmanagement.repository;

import com.example.productmanagement.model.Produs;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProdusRepository extends JpaRepository<Produs, Long> {

}
