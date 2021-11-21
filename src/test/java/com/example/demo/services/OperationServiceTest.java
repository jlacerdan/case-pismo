package com.example.demo.services;

import com.example.demo.exceptions.OperationNotFoundException;
import com.example.demo.models.Operation;
import com.example.demo.repositories.OperationRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.example.demo.mass.DataMass.getOperationWithTypeCredit;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OperationServiceTest {

    @InjectMocks
    private OperationService operationService;

    @Mock
    private OperationRepository operationRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void getOperationByIdSuccessfullyTest() {
        when(operationRepository.findById(anyString())).thenReturn(Optional.of(getOperationWithTypeCredit()));
        Operation operation = operationService.getOperationById("1");

        Assert.assertNotNull(operation);
    }

    @Test
    public void getAccountByIdNotFoundTest() {
        when(operationRepository.findById(anyString())).thenReturn(Optional.empty());

        exception.expect(OperationNotFoundException.class);
        exception.expectMessage("Operation Type with id 1 not found.");

        operationService.getOperationById("1");
    }
}
