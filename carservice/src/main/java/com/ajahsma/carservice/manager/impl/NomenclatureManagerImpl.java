package com.ajahsma.carservice.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajahsma.carservice.dao.NomenclatureDao;
import com.ajahsma.carservice.dto.NomenclatureDTO;
import com.ajahsma.carservice.manager.NomenclatureManager;
import com.ajahsma.carservice.model.NomenclatureTO;
import com.ajahsma.carservice.utils.CarServiceUtils;

/**
 * @author SHARAN A
 */

@Service
public class NomenclatureManagerImpl extends DefaultManagerImpl implements NomenclatureManager {


	@Autowired
	public void setDefaultDao(NomenclatureDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	private NomenclatureDao getNomenclatureDao() {
		return (NomenclatureDao) getDefaultDao();
	}

	@Override
	public NomenclatureTO convertNomenclatureDTOToNomenclatureTO(NomenclatureDTO nomenclatureDTO) throws InstantiationException, IllegalAccessException {
		NomenclatureTO nomenclatureTO = CarServiceUtils.copyBeanProperties(nomenclatureDTO, NomenclatureTO.class);

		return nomenclatureTO;
	}

	
}
