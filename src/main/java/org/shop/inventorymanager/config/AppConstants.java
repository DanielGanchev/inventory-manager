package org.shop.inventorymanager.config;

import java.util.List;

public class AppConstants {

  private AppConstants() {
  }

  public static final String API_V1 = "/api/v1";
  public static final List<String> WITHOUT_FILTER = List.of("/api/v1/auth/login", "/api/v1/auth/register");
}
