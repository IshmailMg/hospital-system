package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Invoice;
import za.ac.cput.factory.InvoiceFactory;
import za.ac.cput.service.InvoiceService;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InvoiceControllerTest {

    private String baseUrl;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private InvoiceController controller;

    @Autowired
    private InvoiceService invoiceService;

    private Invoice invoice;

    @BeforeEach
    void setUp() {
        assertNotNull(controller);
        this.invoice = InvoiceFactory.createInvoice("10112212", 233.2,"10/12/2012","Document");
        this.invoiceService.save(invoice);
        this.baseUrl = "http://localhost:" + this.port + "/hospital-system/invoice/";
    }

    @Test
    void a_save() {
        String url = baseUrl + "save";
        System.out.println(url);
        ResponseEntity<Invoice> response = this.restTemplate
                .withBasicAuth("admin-user", "65ff7492d30")
                .postForEntity(url, this.invoice, Invoice.class);
        System.out.println(response);
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertNotNull(response.getBody()));
    }

    @Test
    public void b_read() {
        String url = baseUrl + "read/" + this.invoice.getInvoiceNum();
        System.out.println(url);
        ResponseEntity<Invoice> response = this.restTemplate
                .withBasicAuth("admin-user", "65ff7492d30")
                .getForEntity(url, Invoice.class);
        System.out.println(response);
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertNotNull(response.getBody()));
    }

    @Test
    public void c_delete() {
        String url = baseUrl + "delete/" + this.invoice.getInvoiceNum();
        System.out.println(url);
        this.restTemplate.delete(url);
    }

    @Test
    public void d_findAll() {
        String url = baseUrl + "find-all";
        System.out.println(url);
        ResponseEntity<Invoice[]> response = this.restTemplate
                .withBasicAuth("client-user", "1253208465b")
                .getForEntity(url, Invoice[].class);
        System.out.println("Show All:");
        System.out.println(Arrays.asList(Objects.requireNonNull(response.getBody())));
        assertAll(() -> assertEquals(HttpStatus.OK, response.getStatusCode()), () -> assertEquals(9, response.getBody().length));
    }

}