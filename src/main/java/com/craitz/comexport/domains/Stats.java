package com.craitz.comexport.domains;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Stats {
	// variáveis usadas na formatação de valores Double
	private DecimalFormat decimalFormat;
	private DecimalFormatSymbols symbols;
	
	// demais variáveis de classe
	private Double sum;
	private Double min;
	private Double max;
	private Double average;
	private Long quantity;
	
	public Stats() {
		symbols = new DecimalFormatSymbols(Locale.US);
		decimalFormat = new DecimalFormat(".##", symbols);
		sum = 0D;
		min = 0D;
		max = 0D;
		average = 0D;
		quantity = 0L;
	}
	
	public Double getSum() {
		return Double.valueOf(decimalFormat.format(sum));
	}
	
	public void setSum(Double sum) {
		this.sum = sum;
	}
	
	public Double getMin() {
		return Double.valueOf(decimalFormat.format(min));
	}
	
	public void setMin(Double min) {
		this.min = min;
	}
	
	public Double getMax() {
		return Double.valueOf(decimalFormat.format(max));
	}
	
	public void setMax(Double max) {
		this.max = max;
	}
	
	public Double getAverage() {
		return Double.valueOf(decimalFormat.format(average));
	}
	
	public void setAverage(Double average) {
		this.average = average;
	}
	
	public Long getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
}
