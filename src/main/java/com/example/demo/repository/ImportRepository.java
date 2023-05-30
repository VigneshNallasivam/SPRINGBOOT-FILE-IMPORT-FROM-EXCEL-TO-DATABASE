package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Import;

@Repository
public interface ImportRepository extends JpaRepository<Import,Integer>
{

}
