package com.ajahsma.carservice.manager;

import com.ajahsma.carservice.json.JsonResponse;

/**
 * @author SHARAN A
 */

public interface ItemManager extends DefaultManager {

	JsonResponse getAllNomenclature(String urlType);

}
