package com.app.alianza.Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import com.app.alianza.Model.Entity.Clientes;
import com.opencsv.CSVWriter;

public class CsvUtils {
	
	public static byte[] generarCsv(List<Clientes> clientes) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(outputStream))) {

            String[] header = {"NOMBRE", "DATA", "EMAIL", "PHONE", "SHARED KEY", "ID"};
            csvWriter.writeNext(header);

            for (Clientes cliente : clientes) {
                String[] row = {cliente.getBussinessid(), cliente.getData(), cliente.getEmail(), cliente.getPhone(), cliente.getSharedkey(), String.valueOf(cliente.getId())};
                csvWriter.writeNext(row);
            }

            csvWriter.flush();
            return outputStream.toByteArray();
        } catch (IOException e) {
            // Manejo de errores
        }
        return null;
    }
	//
}
