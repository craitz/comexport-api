package com.craitz.comexport.domains;

public class Stats {
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
	}
	
	public Double getSoma() {
		return soma;
	}
	
	public void setSoma(Double soma) {
		this.soma = soma;
	}
	
	public Double getMin() {
		return min;
	}
	
	public void setMin(Double min) {
		this.min = min;
	}
	
	public Double getMax() {
		return max;
	}
	
	public void setMax(Double max) {
		this.max = max;
	}
	
	public Double getMedia() {
		return media;
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
