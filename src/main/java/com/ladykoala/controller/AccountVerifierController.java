package com.ladykoala.controller;
import com.ladykoala.model.DtoAccountVerifiedID;
import com.ladykoala.service.AccountVerifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@CrossOrigin()
public class AccountVerifierController {

    @Autowired
    AccountVerifierService verifiedIDService;

    @RequestMapping(value = "/account/verifiedIds", method = RequestMethod.GET)
    public List<DtoAccountVerifiedID> getValidatedIds(HttpServletResponse response) {
        String username = response.getHeader("username");
        long userid = Long.parseLong(response.getHeader("userid"));
        return verifiedIDService.findByUserId(userid);
    }

    @RequestMapping(value = "/account/verifiedIds", method = RequestMethod.POST)
    public ResponseEntity<?> addValidatedIds(@RequestBody Map<String, List<DtoAccountVerifiedID>> validatedIds, HttpServletResponse response) throws Exception {
        long userid = Long.parseLong(response.getHeader("userid"));
        List<DtoAccountVerifiedID> list = validatedIds.get("validatedIds");
        verifiedIDService.addAccountVerifiedIDs(list, userid);
        return ResponseEntity.ok(new String("Ids Successfully Added"));
    }



}