package com.mzk.util;
/**
 * Constant class.
 *
 *
 */
public final class Constants {

  private Constants() {}

  /** API Route */
  public static final String API_GET_ONE = "/api/produto/:id";
  public static final String API_LIST_ALL = "/api/produto/:id";
  public static final String API_CREATE = "/api/produto";
  public static final String API_UPDATE = "/api/produto/:id";
  public static final String API_DELETE_ONE = "/api/produto/:id";
  public static final String API_DELETE_ALL = "/api/produto";
  
  /** API */
  public static final String HOST = "0.0.0.0";
  public static final int PORT = 8080;


}