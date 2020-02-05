package com.mzk;

import com.mzk.controller.ControllerProduto;

import io.vertx.core.Vertx;

public class Starter {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
	    vertx.deployVerticle(new ControllerProduto());
	}

}
