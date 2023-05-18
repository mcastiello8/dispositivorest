package it.prova.dispositivorest.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import it.prova.dispositivorest.web.rest.resources.LocalDateAdapter;


public class Dispositivo {

	private Long id;
	private String marca;
	private String modello;
	private Integer prezzo;
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate dataProduzione;
	
	public Dispositivo() {}

	public Dispositivo(Long id, String marca, String modello, Integer prezzo, LocalDate dataProduzione) {
		
		this.id = id;
		this.marca = marca;
		this.modello = modello;
		this.prezzo = prezzo;
		this.dataProduzione = dataProduzione;
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public LocalDate getDataProduzione() {
		return dataProduzione;
	}

	public void setDataProduzione(LocalDate dataProduzione) {
		this.dataProduzione = dataProduzione;
	}
	
}
