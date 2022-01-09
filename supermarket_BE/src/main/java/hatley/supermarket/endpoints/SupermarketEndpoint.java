package hatley.supermarket.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hatley.supermarket.entities.Supermarket;
import hatley.supermarket.services.SupermarketService;
import hatley.supermarket.utils.SupermarketManagement;

@RestController
@RequestMapping(value = "/menem_supermarket/")
@CrossOrigin
public class SupermarketEndpoint {
	
	@Autowired
	private SupermarketService supermarketService;
	
	@Value("${image.path}")
	private final String FILE_IMAGE_PATH = null;
	
	/**
	 * getSupermarketList
	 * @return
	 */
	@GetMapping( value = "getSupermarketList" )
	public List<Supermarket> getSupermarketList() {
		List<Supermarket> supermarketList = this.supermarketService.getSupermarketList();
		List<Supermarket> supermarkets = new ArrayList<Supermarket>();
		for(Supermarket supermarket: supermarketList) {
			SupermarketManagement supermarketManagement = new SupermarketManagement();
			String imageBEASE64 = supermarketManagement.getImageBase64(supermarket.getImage(), FILE_IMAGE_PATH);
			supermarket.setImage(imageBEASE64);
			supermarkets.add(supermarket);
		}
		
		return supermarkets;
	}
	
	/**
	 * saveSupermarket
	 * @param file
	 * @param supermarketObj
	 * @return
	 */
	@PostMapping(value = "saveSupermarket")
	public Supermarket saveSupermarket(@RequestParam(value="file", required = false) MultipartFile file,
			@RequestParam(value="supermarketObj") String supermarketObj) {
		Gson gson = null;
		Supermarket supermarket = null;
		
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();

			gson = gsonBuilder.create();
			
			supermarket = gson.fromJson(supermarketObj, Supermarket.class);
			SupermarketManagement supermarketManagement = new SupermarketManagement();

			String fileName = supermarketManagement.updateImage(file, FILE_IMAGE_PATH);
			supermarket.setImage(fileName);
			if (supermarket != null) {
				supermarket = this.supermarketService.saveSupermarket(supermarket);
			}
			
		}catch(Exception ex) {
			
		}
		return supermarket;
		
	}
	
	/**
	 * updateSupermarket
	 * @param file
	 * @param supermarketObj
	 * @return
	 */
	@PostMapping(value = "updateSupermarket")
	public Supermarket updateSupermarket(@RequestParam(value="file", required = false) MultipartFile file,
			@RequestParam(value="supermarketObj") String supermarketObj) {
		Gson gson = null;
		Supermarket supermarket = null;
		String fileName = null;
		
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();

			gson = gsonBuilder.create();
			
			supermarket = gson.fromJson(supermarketObj, Supermarket.class);
			SupermarketManagement supermarketManagement = new SupermarketManagement();

			if(file != null && !file.isEmpty()) {
				fileName = supermarketManagement.updateImage(file, FILE_IMAGE_PATH);
				supermarket.setImage(fileName);
			} else {
				Supermarket supermarketForGetImage = this.supermarketService.getSupermarketById(supermarket.getId());
				supermarket.setImage(supermarketForGetImage.getImage());
			}
			if (supermarket != null) {
				supermarket = this.supermarketService.saveSupermarket(supermarket);
			}
			
		}catch(Exception ex) {
			
		}
		return supermarket;
		
	}
	
	/**
	 * deleteSupermarket
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "deleteSupermarket/{id}")
	public boolean deleteSupermarket(@PathVariable int id) {
		return this.supermarketService.deleteSupermarket(id);
	}

}
