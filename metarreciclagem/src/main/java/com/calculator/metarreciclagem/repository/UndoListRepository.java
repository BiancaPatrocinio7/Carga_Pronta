package com.calculator.metarreciclagem.repository;

import com.calculator.metarreciclagem.model.UndoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UndoListRepository extends JpaRepository<UndoList, Long> {
    List<UndoList> findByEquipment(String equipmentName);
}
