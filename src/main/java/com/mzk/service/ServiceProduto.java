package com.mzk.service;

import java.util.ArrayList;

import com.mzk.model.Produto;

public class ServiceProduto {
	ArrayList<Produto> produtos = new ArrayList<Produto>();

	 public Produto getProduto(String id) {
	        Produto produto = new Produto();
			return produto;
	    }
	 
	 public ArrayList<Produto> setProduto(Produto pro) {
	        produtos.add(pro);
			return produtos;
	    }
	 
	 public ArrayList<Produto> getProdutos() {
	       return produtos;
	    }
}
