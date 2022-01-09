package hatley.supermarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hatley.supermarket.entities.Supermarket;
import hatley.supermarket.repositories.SupermarketRepository;

@Service
public class SupermarketService {
	
	/**
	 * i can use this repository .
	 * it prevent me to use DAO 
	 * and use it's embedded function as findAll(), findById(), save(object), ... 
	 */
	@Autowired
	private SupermarketRepository supermarketRepository;
	
	/**
	 * getSupermarketById fun.
	 * using Repository ...
	 * @param id
	 * @return
	 */
	public Supermarket getSupermarketById(int id) {
		if(id != 0)
			return this.supermarketRepository.findById(id).get();
		
		return null;
	}
	
	/**
	 * getSupermarketList fun.
	 * using Repository ...
	 * @return
	 */
	public List<Supermarket> getSupermarketList() {
		return this.supermarketRepository.findAll();
	}
	
	/**
	 * saveSupermarket fun.
	 * using Repository ...
	 * @param supermarket
	 * @return
	 */
	public Supermarket saveSupermarket(Supermarket supermarket) {
		return this.supermarketRepository.save(supermarket);
	}
	
	/**
	 * deleteSupermarket fun. 
	 * useing Repository ... 
	 * @param id
	 * @return
	 */
	public boolean deleteSupermarket(int id) {
		Supermarket supermarket = this.supermarketRepository.findById(id).get();
		boolean status = false;
		if(supermarket != null) {
			this.supermarketRepository.delete(supermarket);
			status = true;
		}
		return status;
	}
	
}
