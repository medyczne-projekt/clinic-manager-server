package pl.umcs.clinicmanager.receipt;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.umcs.clinicmanager.receipt.domain.Receipt;
import pl.umcs.clinicmanager.receipt.dto.ReceiptDTO;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ReceiptController {

    private final ReceiptService receiptService;

    @PostMapping("/receipt/add")
    ResponseEntity<Receipt> addReceipt(@RequestBody ReceiptDTO newReceipt) {
        final Receipt addedReceipt = receiptService.addReceipt(newReceipt);
        return new ResponseEntity<>(addedReceipt, HttpStatus.CREATED);
    }

    @GetMapping("/receipts/doctor/{doctorID}")
    ResponseEntity<List<ReceiptDTO>> getAllReceiptsByDoctorID(@PathVariable Long doctorID) {
        final List<ReceiptDTO> receipts = receiptService.getAllReceiptsByDoctorID(doctorID);
        return new ResponseEntity<>(receipts, HttpStatus.FOUND);
    }

    @GetMapping("/receipts/patient/{patientID}")
    ResponseEntity<List<ReceiptDTO>> getAllReceiptsByPatientID(@PathVariable Long patientID) {
        final List<ReceiptDTO> receipts = receiptService.getAllReceiptsByPatientID(patientID);
        return new ResponseEntity<>(receipts, HttpStatus.FOUND);
    }

    @GetMapping("/receipt/{id}")
    ResponseEntity<ReceiptDTO> getReceiptByID(@PathVariable Long id) {
        final ReceiptDTO receipt = receiptService.getReceipt(id);
        return new ResponseEntity<>(receipt, HttpStatus.FOUND);
    }

    @DeleteMapping("/receipt/delete/{id}")
    ResponseEntity<Void> deleteReceipt(@PathVariable Long id) {
        receiptService.deleteReceipt(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
