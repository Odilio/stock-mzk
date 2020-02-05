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
	private static final String API_SAVE = "api/produto";
	private static final String API_GET_ONE = "/api/produto/:id";
	private static final String API_DELETE_ONE = "/api/produto/:id";
	
    @Override
    public void start(Promise<Void> startPromise) {

        Router router = Router.router(vertx);
        router.route(API_SAVE).handler(BodyHandler.create());

        
        router.get(API_GET_ONE)
                .handler(this::getProdutos);
        router.post(API_SAVE)
        		.handler(this::setProdutos);
        router.delete(API_DELETE_ONE)
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
