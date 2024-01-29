package com.first.redBank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.first.redBank.DTO.TransferDTO;
import com.first.redBank.DTO.TransferResponseDTO;
import com.first.redBank.service.TransferService;


@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping("/handle")
    public ResponseEntity<TransferResponseDTO> handleTransfer(@RequestBody TransferDTO transferDTO) {
        TransferResponseDTO response = new TransferResponseDTO();
        boolean transferSuccess = transferService.handleTransfer(
                transferDTO.getIdOriginAccount(),
                transferDTO.getIdDestinyAccount(),
                transferDTO.getValue());

        response.setStatus(transferSuccess ? "Transferência bem-sucedida" : "Transferência falhou");
        response.setIdOriginAccount(transferDTO.getIdOriginAccount());
        response.setIdDestinyAccount(transferDTO.getIdDestinyAccount());

        if (!transferSuccess) {
            String failureMessage = getFailureMessage(transferDTO);
            response.setErrorMessage(failureMessage);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }

    private String getFailureMessage(TransferDTO transferDTO) {
        if (!transferService.isAccountActive(transferDTO.getIdOriginAccount())) {
            return "Transferências de contas inativas não podem ser realizadas";
        }

        if (!transferService.hasSufficientBalance(transferDTO.getIdOriginAccount(), transferDTO.getValue())) {
            return "Conta de origem não possui saldo suficiente para a transferência";
        }

        return "Falha desconhecida na transferência";
    }
    
    public void setTransferService(TransferService transferService) {
        this.transferService = transferService;
    }
}
