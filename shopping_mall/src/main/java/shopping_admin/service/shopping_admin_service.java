package shopping_admin.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping_admin.dao.shopping_admin_dao;
import shopping_admin.model.shopping_admin_dto;

@Service
public class shopping_admin_service {
	 @Autowired
	    private shopping_admin_dao adminDAO;
	 
	 public boolean registerAdmin(shopping_admin_dto adminDTO) {
	        try {
	            // 패스워드 해싱
	            String hashedPassword = hashPassword(adminDTO.getAdpass());
	            adminDTO.setAdpass(hashedPassword);
	            int result = adminDAO.insert_admin(adminDTO);
	            return result > 0;
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public boolean isIdAvailable(String adid) {
	        shopping_admin_dto admin = adminDAO.selectAdminById(adid);
	        return admin == null;
	    }
	    private String hashPassword(String password) throws NoSuchAlgorithmException {
	        MessageDigest digest = MessageDigest.getInstance("SHA3-256"); // SHA3-256을 사용합니다.
	        byte[] hashedBytes = digest.digest(password.getBytes());
	        StringBuilder sb = new StringBuilder();
	        for (byte b : hashedBytes) {
	            sb.append(String.format("%02x", b));
	        }
	        return sb.toString();
	    }
}
