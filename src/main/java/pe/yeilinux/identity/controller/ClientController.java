package pe.yeilinux.identity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.yeilinux.identity.controller.response.GeneralResponse;
import pe.yeilinux.identity.domain.Client;
import pe.yeilinux.identity.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping
    public ResponseEntity<?> getClients(@RequestParam int tokenId){
        return new ResponseEntity<>(this.clientService.getClients(tokenId), HttpStatus.OK);
    }

    @GetMapping("/{client_id}")
    public ResponseEntity<?> getClient(@PathVariable("client_id") String clientId){
        return new ResponseEntity<>(this.clientService.getClientById(clientId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody Client client){
        this.clientService.postClient(client);
        return new ResponseEntity<>(new GeneralResponse("Correctly created"),HttpStatus.CREATED);
    }

    @PutMapping("/{client_id}")
    public ResponseEntity<?> updateClient(@RequestBody Client client,@PathVariable("client_id") String clientId){
        this.clientService.udpateClient(client,clientId);
        return new ResponseEntity<>(new GeneralResponse("Correctly udpated"),HttpStatus.OK);
    }

    @PutMapping("/client-secret/{client_id}")
    public ResponseEntity<?> updateClientSecret(@RequestBody Client client,@PathVariable("client_id") String clientId){
        this.clientService.updateClientSecret(client,clientId);
        return new ResponseEntity<>(new GeneralResponse("Correctly udpated client secret"),HttpStatus.OK);
    }

    @DeleteMapping("/{client_id}")
    public ResponseEntity<?> deleteClient(@PathVariable("client_id") String clientIid){
        this.clientService.deleteClient(clientIid);
        return new ResponseEntity<>(new GeneralResponse("Correctly deleted"), HttpStatus.OK);
    }
}
