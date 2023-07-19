package com.example.dxc.Service;

import org.springframework.stereotype.Service;

@Service
public class QuoteService {
    private static final double COMPREHENSIVE_CHARGE = 2.5;
    private static final double LIABILITY_CHARGE = 2;
    private static final double FIXED_AMOUNT = 1000;

    public double calculateQuote(double vehiclePrice, int vehicleAge) {
        double comprehensiveCharge = COMPREHENSIVE_CHARGE;
        
        // Increase comprehensive charge for older vehicles
        if (vehicleAge > 0) {
            double increaseFactor = 1.0; // Adjust the increase factor as per your requirements
            comprehensiveCharge += vehicleAge * increaseFactor;
        }
        
        return ((vehiclePrice * comprehensiveCharge + FIXED_AMOUNT) / 100 +LIABILITY_CHARGE);
    }
}
