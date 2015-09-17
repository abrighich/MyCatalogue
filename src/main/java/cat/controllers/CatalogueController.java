package cat.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.dao.IProduitRepository;
import cat.entity.Produit;

@RestController
public class CatalogueController {
	@Autowired
	private IProduitRepository produitRepository;
	
	@RequestMapping("/test")
	public String test(){
		return "test";
	}
	
	@RequestMapping("/save")
	public Produit saveProduit(Produit p){
		produitRepository.save(p);
		return p;
	}
	
	@RequestMapping("/all")
	public List<Produit> getAllProduits(){
		return produitRepository.findAll();
	}
	
	@RequestMapping("/produits")
	public Page<Produit> getProduits(int page){
		return produitRepository.findAll(new PageRequest(page, 5));
	}
	
	@RequestMapping("/produitsByKey")
	public Page<Produit> getProduits(String key,int page){
		return produitRepository.produitByKey("%"+key+"%", new PageRequest(page, 5));
	}
	
	@RequestMapping("/get")
	public Produit getProduits(Long ref){
		return produitRepository.findOne(ref);
	}
	
	@RequestMapping("/delete")
	public boolean delete(Long ref){
		produitRepository.delete(ref);
		return true;
	}
	
	@RequestMapping("/update")
	public Produit update(Produit p){
		produitRepository.saveAndFlush(p);
		return p;
	}
}

