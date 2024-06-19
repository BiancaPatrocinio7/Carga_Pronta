package com.calculator.metarreciclagem.service;

import com.calculator.metarreciclagem.model.UndoList;

import java.util.Map;

public interface CalculatorService {

    UndoList addEquipment(String equipment, int quantity);

    double calculateUnitaryVolume(String equipment);

    double calculateTotalVolume();

    Map<String, Integer> calculateCarrierQuantities();
}
