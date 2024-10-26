/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DevPointSystem.Comptabilite.Parametrage.repository;
 
import com.DevPointSystem.Comptabilite.Parametrage.domaine.TypeDepense;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface TypeDepenseRepo  extends JpaRepository<TypeDepense, Integer>{
      TypeDepense findByCode(Integer code);   
      
      List<TypeDepense> findByCodeCategorieDepense(Integer codeCategorieDepense);

}
