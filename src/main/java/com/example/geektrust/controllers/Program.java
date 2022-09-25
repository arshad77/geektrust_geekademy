package com.example.geektrust.controllers;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.datastore.Data;

public class Program {
	Data data = null;

	public Program(Data data) {
		this.data = data;
	}

	public void handleProgram(String[] inputs) {
		String programName = inputs[1];
		String quantity = inputs[2];
		float cost = 0.0f;
		if (programName.equals("CERTIFICATION"))
			cost = Constants.CERTIFICATION_PRICE;
		else if (programName.equals("DEGREE"))
			cost = Constants.DEGREE_PRICE;
		else if (programName.equals("DIPLOMA"))
			cost = Constants.DIPLOMA_PRICE;
		data.addToProgramsList(programName);
		data.addToProgramsCount(Integer.parseInt(quantity));
		data.addToProgramsAmount(cost);
	}
}
