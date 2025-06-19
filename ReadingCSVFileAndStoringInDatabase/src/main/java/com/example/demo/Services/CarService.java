package com.example.demo.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.CarRepository;
import com.example.demo.entities.Car;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;

	public void saveCSVData(InputStream inputStream) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			reader.readLine(); // Skip header
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				Car car = new Car();
				String carName = data[0].replace("\"", "").trim(); // car name without quotes

				car.setModel(carName);
				car.setMilesPerGallone(Double.parseDouble(data[1]));
				car.setNumberOfCylinders(Integer.parseInt(data[2]));
				car.setDisplacement(Double.parseDouble(data[3]));
				car.setHorsePower(Double.parseDouble(data[4]));
				car.setRearAxelRatio(Double.parseDouble(data[5]));
				car.setWeight(Double.parseDouble(data[6]));
				car.setMileTime(Double.parseDouble(data[7]));
				//car.setEngineShape(Integer.parseInt(data[8]);
				String shape =(Integer.parseInt(data[8]) == 0)?"V-shaped": "Straight/Inline";

				/*
				 * String shape = ""; if (Integer.parseInt(data[8]) == 0) { shape = "V-shaped";
				 * } else { shape = "Straight/Inline"; }
				 */ 
				car.setEngineShape(shape);

				String transmission =(Integer.parseInt(data[9]) == 0)?"Automatic":"Manual";
				//car.setTransmission(Integer.parseInt(data[9]));
				/*
				 * String transmission = ""; if (Intege  r.parseInt(data[9]) == 0) { transmission
				 * = "Automatic"; } else { transmission = "Manual"; }
				 */
				car.setTransmission(transmission);
				car.setEngineShape(shape);
				car.setNumberOfForwaedGears(Integer.parseInt(data[10]));
				car.setNumerOfCaburetors(Integer.parseInt(data[11]));
				carRepository.save(car);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
