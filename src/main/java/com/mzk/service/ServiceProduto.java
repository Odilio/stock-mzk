package com.mzk.service;

import javax.validation.Valid;

import com.mzk.model.Produto;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class ServiceProduto {
	 JsonArray produtos = new JsonArray();
	 
	 public JsonObject getProduto(String id) {
		 JsonObject produto = new JsonObject();
			return produto;
	    }
	 
	 public JsonObject setProduto( Produto pro) {
		 JsonObject produto = new JsonObject();
		 if (pro.getNome() == null || pro.getCodigoBarras() == null || pro.getNumeroSerie() == null)
		 {
			 
			 produto.put("error" , "Possui elementos vazios no body ");
			 return produto;
		 }
		 for(int i = 0; i < produtos.size(); i++){
			 Produto p = produtos.getJsonObject(i).mapTo(Produto.class);
			 if (p.getCodigoBarras().equalsIgnoreCase(pro.getCodigoBarras()) 
					 && p.getNumeroSerie().equalsIgnoreCase(pro.getNumeroSerie()))
			 {
				 System.out.println("fudeu " + produtos.getValue(i));
				 produto.put("error" , "Codigo de barras já adicionado a este numero de série");
			 	 return produto;
			 }
			 else
				 System.out.println("não fudeu " + p.getCodigoBarras() + " oto " + pro.getCodigoBarras());
		 }
		  produto = JsonObject.mapFrom(pro);
		 produtos.add(produto);
		 return produto;
	    }
	 
	 public JsonArray getProdutos() {
	       return produtos;
	    }
	 
	 public JsonObject deleteProduto(String id) {
		 JsonObject produto = new JsonObject();
	       return produto;
	    }
}
