/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.controllers;

import java.util.List;

import org.example.AirportService.AirportDTO;
import org.example.AirportService.AirportService;
/**
 *
 * @author nipatiitti
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/airports")
public class AirportController {
    private AirportService airportService = new AirportService();

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<AirportDTO>> getAllAirports() {
        try {
            List<AirportDTO> airports = airportService.FetchAirports();
            return ResponseEntity.ok(airports);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}