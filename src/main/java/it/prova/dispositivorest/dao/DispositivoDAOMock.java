package it.prova.dispositivorest.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import it.prova.dispositivorest.model.Dispositivo;

public class DispositivoDAOMock {
	
	public static final List<Dispositivo> DB_MOCK = new ArrayList<>();

	static {
		// preparo una lista mock perché ancora non ho il collegamento alla
		// base dati
		DB_MOCK.add(new Dispositivo(1L, "Samsung", "S23", 1200, LocalDate.of(2022, 4, 13)));
		DB_MOCK.add(new Dispositivo(2L, "iPhone", "14ProMax", 1300, LocalDate.of(2022, 9, 2)));
		DB_MOCK.add(new Dispositivo(3L, "Samsung", "A12", 400, LocalDate.of(2018, 6, 22)));
		DB_MOCK.add(new Dispositivo(4L, "Google", "Pixel5", 1100, LocalDate.of(2023, 1, 31)));
		DB_MOCK.add(new Dispositivo(5L, "Samsung", "S21Ultra", 1250, LocalDate.of(2019, 5, 12)));
		DB_MOCK.add(new Dispositivo(6L, "iPhone", "15", 1300, LocalDate.of(2024, 5, 12)));
		DB_MOCK.add(new Dispositivo(7L, "Nokia", "3310", 150, LocalDate.of(2002, 10, 6)));
	}

	public Dispositivo findById(Long idInput) {
		for (Dispositivo dispositivoItem : DB_MOCK) {
			if (dispositivoItem.getId() == idInput) {
				return dispositivoItem;
			}
		}

		return null;
	}

	public void insert(Dispositivo dispositivoInput, String dataDaParsare) {
		Long maxId = 1L;
		for (Dispositivo dispositivoItem : DB_MOCK) {
			if (dispositivoItem.getId() > maxId)
				maxId = dispositivoItem.getId();
		}
		dispositivoInput.setId(++maxId);
		dispositivoInput.setDataProduzione(LocalDate.parse(dataDaParsare));
		DB_MOCK.add(dispositivoInput);
	}

	public List<Dispositivo> findAll() {
		return DB_MOCK;
	}

	public List<Dispositivo> findByFields(String marca, String modello) {
		List<Dispositivo> result = new ArrayList<>();

		if ((marca == null || marca.isEmpty()) && (modello == null || modello.isEmpty()))
			return DB_MOCK;

		for (Dispositivo dispositivoItem : DB_MOCK) {
			if ((marca != null && !marca.isEmpty() && dispositivoItem.getMarca().startsWith(marca))
					&& (modello != null && !modello.isEmpty() && dispositivoItem.getModello().startsWith(modello)))
				result.add(dispositivoItem);
		}
		return result;
	}
	
	public boolean delete(Long idInput) {
		for (Dispositivo dispositivoItem : DB_MOCK) {
			if (dispositivoItem.getId() == idInput) {
				DB_MOCK.remove(dispositivoItem);
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("static-access")
	public Dispositivo update(Dispositivo input, String dataDaAggiornareParsed) {
		for (Dispositivo dispositivoItem : DB_MOCK) {
			if (dispositivoItem.getId() == input.getId()) {
				dispositivoItem.setMarca(input.getMarca());
				dispositivoItem.setModello(input.getModello());
				dispositivoItem.setPrezzo(input.getPrezzo());
				dispositivoItem.setDataProduzione(input.getDataProduzione().parse(dataDaAggiornareParsed));
				return dispositivoItem;
			}
		}
		return null;
	}
	
	
	// Prezzo inferiore a 1000 euro e non ancora prodotto
	public List<Dispositivo> findSotto1000EuroENonAncoraProdotti(){
		List<Dispositivo> result = new ArrayList<>();

		for (Dispositivo dispositivoItem : DB_MOCK) {
			if ((dispositivoItem.getPrezzo() != null && dispositivoItem.getPrezzo()<1000) && (dispositivoItem.getDataProduzione() != null && dispositivoItem.getDataProduzione().isAfter(LocalDate.now())))
				result.add(dispositivoItem);
		}
		return result;
	}
	
	
	// Tutti i Samsung prodotti dopo il 2020
	public List<Dispositivo> findAllSamsungAfter2020(){
		List<Dispositivo> result = new ArrayList<>();

		for (Dispositivo dispositivoItem : DB_MOCK) {
			if ((dispositivoItem.getMarca() != null && !dispositivoItem.getMarca().isEmpty() && dispositivoItem.getMarca().equals("Samsung"))
					&& (dispositivoItem.getDataProduzione() != null && dispositivoItem.getDataProduzione().isAfter(LocalDate.of(2020, 01, 01))))
				result.add(dispositivoItem);
		}
		return result;
	}
	

}
