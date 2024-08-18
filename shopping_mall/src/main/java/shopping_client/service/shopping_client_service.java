package shopping_client.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping_client.dao.shopping_client_dao;
import shopping_client.dto.shopping_clients_dto;

@Service
public class shopping_client_service {
	
	@Autowired
	private shopping_client_dao clientDao;
	
 	//client 등록신청
	public boolean registerClient(shopping_clients_dto clientsDTO) throws Exception {
	        String hashedPassword = hashPassword(clientsDTO.getPassword());
	        clientsDTO.setPassword(hashedPassword);
	        int result = clientDao.insertClient(clientsDTO);
	        return result > 0;
	}
	
    //password 암호화
    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA3-256"); // SHA3-256을 사용합니다.
        byte[] hashedBytes = digest.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
	//admin 등록 신청 id중복체크
    public boolean isIdAvailable(String userId) {
    	shopping_clients_dto clientsDTO = clientDao.selectClientById(userId);
        return clientsDTO == null;
    }
	
}
