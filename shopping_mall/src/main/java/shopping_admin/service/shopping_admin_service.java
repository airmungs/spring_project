package shopping_admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping_admin.dao.shopping_admin_dao;
import shopping_admin.model.shopping_admin_dto;

@Service
public class shopping_admin_service {
	 @Autowired
	    private shopping_admin_dao sad;
	 
	 	public boolean registerAdmin(shopping_admin_dto adminDTO) {
	        int result = sad.insert_admin(adminDTO);
	        return result > 0;
	    }

	    public boolean isIdAvailable(String adid) {
	        shopping_admin_dto admin = sad.selectAdminById(adid);
	        return admin == null;
	    }
}
