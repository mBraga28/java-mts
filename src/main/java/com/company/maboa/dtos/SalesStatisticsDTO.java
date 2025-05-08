package com.company.maboa.dtos;

import java.util.Map;

public class SalesStatisticsDTO {

    private Integer totalSales;
    private Double totalRevenue;
    private Map<String, Integer> productSales; // nome do produto e quantidade vendida

    public SalesStatisticsDTO() {
    }

    public SalesStatisticsDTO(Integer totalSales, Double totalRevenue, Map<String, Integer> productSales) {
        this.totalSales = totalSales;
        this.totalRevenue = totalRevenue;
        this.productSales = productSales;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Map<String, Integer> getProductSales() {
        return productSales;
    }

    public void setProductSales(Map<String, Integer> productSales) {
        this.productSales = productSales;
    }
}
