package com.calculator.metarreciclagem.service;

import com.calculator.metarreciclagem.model.Equipment;
import com.calculator.metarreciclagem.model.UndoList;
import com.calculator.metarreciclagem.repository.EquipmentRepository;
import com.calculator.metarreciclagem.repository.UndoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService {
    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private UndoListRepository undoListRepository;

    @Override
    public UndoList addEquipment(String equipment, int quantity) {
        UndoList undoList = new UndoList();
        undoList.setEquipment(equipment);
        undoList.setQuantity(quantity);
        return undoListRepository.save(undoList);
    }

    @Override
    public double calculateUnitaryVolume(String equipment) {
        List<UndoList> undoLists = undoListRepository.findByEquipment(equipment);
        if (undoLists.isEmpty()) {
            return 0;
        }

        int quantity = undoLists.stream().mapToInt(UndoList::getQuantity).sum();
        Equipment eq = equipmentRepository.findByName(equipment)
                .orElseThrow(() -> new IllegalArgumentException("Equipment not found: " + equipment));
        return quantity * eq.getUnitVolume();
    }

    @Override
    public double calculateTotalVolume() {
        List<UndoList> undoLists = undoListRepository.findAll();
        double totalVolume = 0;

        for (UndoList undoList : undoLists) {
            Equipment equipment = equipmentRepository.findByName(undoList.getEquipment())
                    .orElseThrow(() -> new IllegalArgumentException("Equipment not found: " + undoList.getEquipment()));
            totalVolume += undoList.getQuantity() * equipment.getUnitVolume();
        }

        return totalVolume;
    }

    @Override
    public Map<String, Integer> calculateCarrierQuantities() {
        double volume = calculateTotalVolume();
        Map<String, Double> carrierMap = Map.of(
                "Carrier_Van", 8.0,
                "Carrier_Bongo", 14.0,
                "Carrier_Caminhao", 40.0
        );

        int van = 0;
        int bongo = 0;
        int truck = 0;

        while (volume > 0) {
            if (volume < carrierMap.get("Carrier_Van")) {
                van++;
                break;
            } else if (volume < carrierMap.get("Carrier_Bongo")) {
                bongo++;
                volume -= carrierMap.get("Carrier_Bongo");
            } else if (volume < carrierMap.get("Carrier_Caminhao")) {
                truck++;
                volume -= carrierMap.get("Carrier_Caminhao");
            } else {
                truck++;
                volume -= carrierMap.get("Carrier_Caminhao");
            }
        }

        Map<String, Integer> carrierQuantities = new HashMap<>();
        carrierQuantities.put("van", van);
        carrierQuantities.put("bongo", bongo);
        carrierQuantities.put("truck", truck);

        return carrierQuantities;
    }
}
