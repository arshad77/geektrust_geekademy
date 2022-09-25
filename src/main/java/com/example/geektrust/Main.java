package com.example.geektrust;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import com.example.geektrust.controllers.Billing;
import com.example.geektrust.controllers.Program;
import com.example.geektrust.datastore.Data;

public class Main {

	public static void main(String[] args) {
		Data data = new Data();
		Billing billing = new Billing(data);
		Program program = new Program(data);
		try {
			FileInputStream fis = new FileInputStream(args[0]);
			Scanner sc = new Scanner(fis);
			while (sc.hasNextLine()) {
				String input = sc.nextLine();
				String[] inputs = input.split(" ");

				if (inputs[0].equals("ADD_PROGRAMME"))
					program.handleProgram(inputs);
				else if (inputs[0].equals("ADD_PRO_MEMBERSHIP"))
					data.enableProMembership();
				else if (inputs[0].equals("APPLY_COUPON"))
					data.addToCoupons(inputs[1]);
				else if (inputs[0].equals("PRINT_BILL"))
					billing.bill();

			}
			sc.close();
		} catch (IOException e) {
		}
	}

}
