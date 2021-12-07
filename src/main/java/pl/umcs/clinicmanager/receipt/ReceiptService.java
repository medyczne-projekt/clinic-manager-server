package pl.umcs.clinicmanager.receipt;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import pl.umcs.clinicmanager.receipt.domain.Receipt;
import pl.umcs.clinicmanager.receipt.dto.ReceiptDTO;
import pl.umcs.clinicmanager.receipt.mapper.ReceiptMapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ReceiptMapper mapper;

    public Receipt addReceipt(ReceiptDTO newReceipt) {
        final Receipt mapped = mapper.dtoToEntity(newReceipt);
        return receiptRepository.save(mapped);
    }


    public List<ReceiptDTO> getAllReceiptsByDoctorID(Long doctorID) {
        final List<Receipt> foundReceipts = receiptRepository.findAllByDoctorId(doctorID);
        if (foundReceipts.isEmpty()) throw new NoSuchElementException();

        return mapper.entityListToDtoList(foundReceipts);
    }

    public List<ReceiptDTO> getAllReceiptsByPatientID(Long patientID) {
        final List<Receipt> foundReceipts = receiptRepository.findAllByPatientId(patientID);
        if (foundReceipts.isEmpty()) throw new NoSuchElementException();

        return mapper.entityListToDtoList(foundReceipts);
    }

    public ReceiptDTO getReceipt(Long id) {
        final Receipt foundReceipt = receiptRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return mapper.entityToDto(foundReceipt);
    }

    @Transactional
    @Modifying
    public void deleteReceipt(Long id) {
        final Receipt receipt = receiptRepository.findById(id).orElseThrow(NoSuchElementException::new);
        receiptRepository.delete(receipt);
    }
}
