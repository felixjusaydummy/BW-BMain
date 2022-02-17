package com.ladykoala.controller;

import com.ladykoala.dao.UserDao;
import com.ladykoala.model.DtoAccount;
import com.ladykoala.model.DtoAccountVerifiedID;
import com.ladykoala.model.RequestDigitalAsset;
import com.ladykoala.service.DigitalAssetService;
import com.ladykoala.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin()
public class DigitAssetsController {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private DigitalAssetService digitalAssetsService;

    @RequestMapping(value = "/digitalassets/addasset", method = RequestMethod.POST)
//    public ResponseEntity<?> addAssets(@RequestBody Map<String, Object> assets, HttpServletResponse response) throws Exception {
    public ResponseEntity<?> addAssets(@RequestBody Map<String, List<RequestDigitalAsset>> assets, HttpServletResponse response) throws Exception {

        List<RequestDigitalAsset> list = (List<RequestDigitalAsset>) assets.get("assets");
        if(list.isEmpty()){
            return ResponseEntity.ok("Asset list is empty ");
        }
        String username = list.get(0).getUsername();
        UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
        if(userDetails != null){
            UserDao userDao = this.jwtUserDetailsService.findByUsername(username);
            digitalAssetsService.addAssets(list, userDao.getId());
            return ResponseEntity.ok(new String("Assets were Successfully Added"));
        }else{
            return ResponseEntity.ok("Username not found: "+ username);
        }
    }

    @RequestMapping(value = "/digitalassets", method = RequestMethod.GET)
    public ResponseEntity<?> testing(HttpServletResponse response) {
        return ResponseEntity.ok("testing...");
    }
}
