package com.pawelpluta.day014;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PaycheckServiceTest {


    @Mock
    private TaxOffice taxOffice;
    @Mock
    private PayoutOffice payoutOffice;
    @Mock
    private PayslipCalculator payslipCalculator;

    @InjectMocks
    private PaycheckService paycheckService;
    private static final Money EXAMPLE_TAXES = new Money(new BigDecimal("5500"), Currency.getInstance(Locale.UK));
    private static final Money EXAMPLE_NET = new Money(new BigDecimal("20000"), Currency.getInstance(Locale.UK));
    private static final Payslip EXAMPLE_PAYSLIP = new Payslip(EXAMPLE_TAXES, EXAMPLE_NET);


    @BeforeEach
    void setUp() {
        Mockito.when(payslipCalculator.calculateFor(any(EmployeeId.class)))
                .thenReturn(EXAMPLE_PAYSLIP);
    }

    private static final EmployeeId EMPLOYEE_ID = new EmployeeId("emp1");

    @Test
    void employeeTaxesShouldBeSendToTheTaxOffice() {
        // when
        paycheckService.pay(EMPLOYEE_ID);

        // then
        verify(taxOffice, times(1))
                .pay(EMPLOYEE_ID, EXAMPLE_PAYSLIP.taxes());

    }

    @Test
    void employeeShouldReceivePayment() {
        // when
        paycheckService.pay(EMPLOYEE_ID);

        // then
        verify(payoutOffice, times(1))
                .pay(EMPLOYEE_ID, EXAMPLE_PAYSLIP.net());
    }

}