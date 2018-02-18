package com.ajahsma.carservice.manager;

import com.ajahsma.carservice.dto.NomenclatureDTO;
import com.ajahsma.carservice.model.NomenclatureTO;

/**
 * @author SHARAN A
 */

public interface NomenclatureManager extends DefaultManager {

	public NomenclatureTO convertNomenclatureDTOToNomenclatureTO(NomenclatureDTO nomenclatureDTO) throws InstantiationException, IllegalAccessException;

}
