package com.calculator.metarreciclagem.dto;

import com.calculator.metarreciclagem.model.UndoList;

public record AddEquipmentResponse (String message, UndoList undoList) {
}
