package com.mzk.controller;


import java.util.ArrayList;

import com.mzk.model.Produto;
import com.mzk.service.ServiceProduto;
import com.mzk.util.Constants;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class ControllerProduto extends AbstractVerticle {
	
	ServiceProduto service = new ServiceProduto();
	
    @Override
    public void start(Promise<Void> startPromise) {

        Router router = Router.router(vertx);
        router.route(Constants.API_CREATE).handler(BodyHandler.create());

        
        router.get(Constants.API_GET_ONE)
                .handler(this::getProdutos);
        router.post(Constants.API_CREATE)
        		.handler(this::setProdutos);
        router.delete(Constants.API_DELETE_ONE)
		.handler(this::deleteProduto);

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(config().getInteger("http.port", Constants.PORT), result -> {
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
        JsonObject produto = service.getProduto(produtoId);

        routingContext.response()
                .putHeader("content-type", "application/json")
                .setStatusCode(200)
                .end(Json.encodePrettily(produto));
    }
    
    private void setProdutos(RoutingContext routingContext) {
    	try {
    	Produto produto = routingContext.getBodyAsJson().mapTo(Produto.class);
    	System.out.println(produto.getNome());
    	//JsonObject produto = routingContext.getBodyAsJson();
    	JsonObject produtos = service.setProduto(produto);
       
        routingContext.response()
                .putHeader("content-type", "application/json")
                .setStatusCode(200)
                .end(Json.encodePrettily(produtos));
    	}catch (Exception e) {e.printStackTrace();
			// TODO: handle exception
		}
    }

    private void deleteProduto(RoutingContext routingContext) {
        String produtoId = routingContext.request()
                .getParam("id");
        JsonObject produto = service.deleteProduto(produtoId);

        routingContext.response()
                .putHeader("content-type", "application/json")
                .setStatusCode(200)
                .end(Json.encodePrettily(produto));
    }

}
