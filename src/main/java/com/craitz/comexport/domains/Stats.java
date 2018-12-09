package com.craitz.comexport.domains;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Stats {
	private DecimalFormat decimalFormat;
	DecimalFormatSymbols symbols;
	private Double soma;
	private Double min;
	private Double max;
	private Double media;
	private Long quantidade;
	
	public Stats() {
		soma = 0D;
		min = 0D;
		max = 0D;
		media = 0D;
		quantidade = 0L;
		symbols = new DecimalFormatSymbols(Locale.US);
		decimalFormat = new DecimalFormat(".##", symbols);
	}
	
	public Double getSoma() {
		return Double.valueOf(decimalFormat.format(soma));
	}
	
	public void setSoma(Double soma) {
		this.soma = soma;
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
	
	public Double getMedia() {
		return Double.valueOf(decimalFormat.format(media));
	}
	
	public void setMedia(Double media) {
		this.media = media;
	}
	
	public Long getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
}
