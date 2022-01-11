package pl.umcs.clinicmanager.receipt;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umcs.clinicmanager.receipt.domain.Receipt;
import pl.umcs.clinicmanager.receipt.dto.ReceiptDTO;
import pl.umcs.clinicmanager.receipt.dto.ReceiptResponseDTO;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ReceiptController {

    private final ReceiptService receiptService;

    @PostMapping("/receipt/add")
    ResponseEntity<Receipt> addReceipt(@RequestBody ReceiptDTO newReceipt) {
        final Receipt addedReceipt;
        try {
            addedReceipt = receiptService.addReceipt(newReceipt);
        }
        catch (NoSuchElementException e) {
            //TODO : make better log
            System.out.println("addReceipt : BAD ARGUMENT PROVIDED");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(addedReceipt, HttpStatus.CREATED);
    }

    @GetMapping("/receipts/doctor/{doctorID}")
    ResponseEntity<List<ReceiptResponseDTO>> getAllReceiptsByDoctorID(@PathVariable Long doctorID) {
        final List<ReceiptResponseDTO> receipts;
        try {
            receipts = receiptService.getAllReceiptsByDoctorID(doctorID);
        } catch (NoSuchElementException e) {
            System.out.println("getAllReceiptsByDoctorID : NOTHING FOUND");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(receipts, HttpStatus.FOUND);
    }

    @GetMapping("/receipts/patient/{patientID}")
    ResponseEntity<List<ReceiptResponseDTO>> getAllReceiptsByPatientID(@PathVariable Long patientID) {
        final List<ReceiptResponseDTO> receipts;
        try {
            receipts = receiptService.getAllReceiptsByPatientID(patientID);
        } catch (NoSuchElementException e) {
            System.out.println("getAllReceiptsByPatientID : NOTHING FOUND");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(receipts, HttpStatus.FOUND);
    }

    @GetMapping("/receipt/{id}")
    ResponseEntity<ReceiptResponseDTO> getReceiptByID(@PathVariable Long id) {
        final ReceiptResponseDTO receipt;
        try {
            receipt = receiptService.getReceipt(id);
        } catch (NoSuchElementException e) {
            System.out.println("getReceiptByID : NOTHING FOUND");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(receipt, HttpStatus.FOUND);
    }

    @DeleteMapping("/receipt/delete/{id}")
    ResponseEntity<Void> deleteReceipt(@PathVariable Long id) {
        try {
            receiptService.deleteReceipt(id);
        } catch (NoSuchElementException e) {
            System.out.println("deleteReceipt : BAD ARGUMENT PROVIDED");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
