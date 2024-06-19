package com.calculator.metarreciclagem.controller;

import com.calculator.metarreciclagem.dto.AddEquipmentResponse;
import com.calculator.metarreciclagem.dto.CarrierQuantitiesResponse;
import com.calculator.metarreciclagem.dto.TotalVolumeResponse;
import com.calculator.metarreciclagem.dto.UnitaryVolumeResponse;
import com.calculator.metarreciclagem.model.UndoList;
import com.calculator.metarreciclagem.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @PostMapping("/equipments")
    public ResponseEntity<AddEquipmentResponse> addEquipment(@RequestBody Map<String, Object> payload) {
        String equipment = (String) payload.get("equipment");
        int quantity = (Integer) payload.get("quantity");
        UndoList undoList = calculatorService.addEquipment(equipment, quantity);
        AddEquipmentResponse response = new AddEquipmentResponse("Equipamento adicionado com sucesso", undoList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/equipments/{equipment}/volume")
    public ResponseEntity<UnitaryVolumeResponse> calculateUnitaryVolume(@PathVariable String equipment) {
        double unitaryVolume = calculatorService.calculateUnitaryVolume(equipment);
        UnitaryVolumeResponse response = new UnitaryVolumeResponse(equipment, unitaryVolume);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/equipments/total-volume")
    public ResponseEntity<TotalVolumeResponse> calculateTotalVolume() {
        double totalVolume = calculatorService.calculateTotalVolume();
        TotalVolumeResponse response = new TotalVolumeResponse(totalVolume);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/carriers/quantities")
    public ResponseEntity<CarrierQuantitiesResponse> calculateCarrierQuantities() {
        Map<String, Integer> quantities = calculatorService.calculateCarrierQuantities();
        CarrierQuantitiesResponse response = new CarrierQuantitiesResponse(
                quantities.get("van"),
                quantities.get("bongo"),
                quantities.get("truck")
        );
        return ResponseEntity.ok(response);
    }
}