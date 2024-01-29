package com.first.redBank.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.first.redBank.DTO.TransferDTO;
import com.first.redBank.DTO.TransferResponseDTO;
import com.first.redBank.service.TransferService;

public class TransferControllerTest {

    private TransferService transferService;
    private TransferController transferController;

    @BeforeEach
    public void setUp() {
        transferService = mock(TransferService.class);
        transferController = new TransferController();
        transferController.setTransferService(transferService);
    }

    @Test
    public void testHandleTransferSuccess() {
        TransferDTO transferDTO = createTransferDTO(1L, 2L, 500.0);

        when(transferService.handleTransfer(1L, 2L, 500.0)).thenReturn(true);
        when(transferService.isAccountActive(1L)).thenReturn(true);
        when(transferService.hasSufficientBalance(1L, 500.0)).thenReturn(true);

        ResponseEntity<TransferResponseDTO> responseEntity = transferController.handleTransfer(transferDTO);

        assertSuccessResponse(responseEntity.getBody(), HttpStatus.OK);
    }

    @Test
    public void testHandleTransferFailure() {
        TransferDTO transferDTO = createTransferDTO(1L, 2L, 500.0);

        when(transferService.handleTransfer(1L, 2L, 500.0)).thenReturn(false);
        when(transferService.isAccountActive(1L)).thenReturn(false);

        ResponseEntity<TransferResponseDTO> responseEntity = transferController.handleTransfer(transferDTO);

        assertFailureResponse(responseEntity.getBody(), HttpStatus.BAD_REQUEST);
    }

    private TransferDTO createTransferDTO(Long idOriginAccount, Long idDestinyAccount, double value) {
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setIdOriginAccount(idOriginAccount);
        transferDTO.setIdDestinyAccount(idDestinyAccount);
        transferDTO.setValue(value);
        return transferDTO;
    }

    private void assertSuccessResponse(TransferResponseDTO response, HttpStatus expectedStatus) {
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo("Transferência bem-sucedida");
        assertThat(response.getErrorMessage()).isNull();
    }

    private void assertFailureResponse(TransferResponseDTO response, HttpStatus expectedStatus) {
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo("Transferência falhou");
        assertThat(response.getErrorMessage()).isNotNull();
    }
}
