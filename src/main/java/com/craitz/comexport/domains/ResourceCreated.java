package com.craitz.comexport.domains;

// objeto de retorno para a requisição de inserção de lançamento contábil
public class ResourceCreated {
	private Long id;
	
	public ResourceCreated(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
