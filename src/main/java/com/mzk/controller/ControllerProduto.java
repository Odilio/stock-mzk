package com.mzk.controller;


import java.util.ArrayList;

import com.mzk.model.Produto;
import com.mzk.service.ServiceProduto;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class ControllerProduto extends AbstractVerticle {
	
	ServiceProduto service = new ServiceProduto();
	
    @Override
    public void start(Promise<Void> startPromise) {

        Router router = Router.router(vertx);
        router.route("/api/produto").handler(BodyHandler.create());

        
        router.get("/api/produto/:id")
                .handler(this::getProdutos);
        router.post("/api/produto")
        		.handler(this::setProdutos);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(config().getInteger("http.port", 8080), result -> {
                    if (result.succeeded()) {
                    	startPromise.complete();
                    } else {
                    	startPromise.fail(result.cause());
                    }
                });
    }

    private void getProdutos(RoutingContext routingContext) {
        String produtoId = routingContext.request()
                .getParam("id");
        Produto produto = service.getProduto(produtoId);

        routingContext.response()
                .putHeader("content-type", "application/json")
                .setStatusCode(200)
                .end(Json.encodePrettily(produto));
    }
    
    private void setProdutos(RoutingContext routingContext) {
    	try {
    	 System.out.println("post" + routingContext.getBodyAsString());
    	Produto produto = routingContext.getBodyAsJson().mapTo(Produto.class);
    	System.out.println(produto.toString());
    	ArrayList<Produto> produtos = service.setProduto(produto);
       
        routingContext.response()
                .putHeader("content-type", "application/json")
                .setStatusCode(200)
                .end(Json.encodePrettily(produtos));
        System.out.println(produtos.size());
    	}catch (Exception e) {e.printStackTrace();
			// TODO: handle exception
		}
    }

}
