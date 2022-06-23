package com.ladykoala.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ladykoala.model.RequestApplyLoan;
import com.ladykoala.repository.AccountRepository;
import com.ladykoala.repository.BankAccountRepository;
import com.ladykoala.repository.BankRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoanService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankRepository bankRepository;


    public String applyLoan(RequestApplyLoan loan){

        System.out.println("Apply loan: " + loan.toString());

        JSONObject dto = new JSONObject();
        try {

            dto.put("walletAccountId" , loan.getWalletAccountId());
            dto.put("purpose" , loan.getPurpose());
            dto.put("amount", loan.getAmount());
            dto.put("paymentTerms", loan.getPaymentTerms());
            dto.put("occupation", loan.getOccupattion());
            dto.put("grossIncome", loan.getGrossIncome());

            JSONObject dto_kyc = new JSONObject();
            dto_kyc.put("id", "c587d713-6552-407a-a4d4-9954a28d39d0");
            dto.put("kycId", dto_kyc);

            JSONObject dto_options = new JSONObject();
            dto_options.put("trackProgress", true);
            dto.put("options", dto_options);

            JSONObject dto_party = new JSONObject();
            dto_party.put("name", "O=BPI,L=Paris,C=FR");
            dto.put("otherParty", dto_party);

            String cordaServer = "localhost:8500";
//            String url = cordaServer + "/node/Cordapp-example Flows/LoanRequestInitiator";

            String url = "http://localhost:8500/node/Cordapp-example Flows/LoanRequestInitiator";

            System.out.println(dto.toString());

            HttpEntity<?> requestHttp = new HttpEntity(dto.toString());
            System.out.println(requestHttp.toString());
            ResponseEntity<String> linkResult = (new RestTemplate()).exchange(url,  HttpMethod.POST, requestHttp, String.class);
            System.out.println(linkResult.toString());

            String cordaRes = linkResult.getBody();
            if (linkResult.getStatusCode() == HttpStatus.ACCEPTED ||
                    linkResult.getStatusCode() == HttpStatus.OK ){

                System.out.println("corda res: "+ cordaRes);
                String location = linkResult.getHeaders().get("Location").get(0);
                System.out.println("corda loc: "+ location);

                ObjectMapper mapper = new ObjectMapper();

                JsonNode jsonNode = mapper.readTree(cordaRes);
                String flowId = jsonNode.get("flowRunId").asText();
                System.out.println("corda flowid: "+ flowId);

                String url2 = cordaServer + "/node/Cordapp-example Flows/LoanRequestInitiator/snapshot/"+ flowId;
                System.out.println("url2: "+url2);

                RestTemplate restTemplate = new RestTemplate();
                String result = restTemplate.getForObject(url2, String.class);
                System.out.println("corda2: "+ result);

                RestTemplate restTemplate2 = new RestTemplate();

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> request = new HttpEntity<String>(headers);

                ResponseEntity<String> linkResult3 = restTemplate2.exchange(url2,  HttpMethod.GET, request, String.class);
                String cordaRes3 = linkResult3.getBody();
                System.out.println("corda3: "+ cordaRes3);

                return flowId;
            }else{
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
