package pl.umcs.clinicmanager.receipt.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.umcs.clinicmanager.receipt.domain.Receipt;
import pl.umcs.clinicmanager.receipt.dto.ReceiptDTO;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ReceiptMapper {

    @Mapping(source = "receiptDescription", target = "description")
    @Mapping(source = "receiptPrescriptionDate", target = "prescriptionDate")
    @Mapping(source = "receiptValidTo", target = "validTo")
    @Mapping(source = "receiptPatientId", target = "patientId")
    @Mapping(source = "receiptDoctorId", target = "doctorId")
    Receipt dtoToEntity(ReceiptDTO receipt);


    @Mapping(source = "description", target = "receiptDescription")
    @Mapping(source = "prescriptionDate", target = "receiptPrescriptionDate")
    @Mapping(source = "validTo", target = "receiptValidTo")
    @Mapping(source = "patientId", target = "receiptPatientId")
    @Mapping(source = "doctorId", target = "receiptDoctorId")
    ReceiptDTO entityToDto(Receipt receipt);

    default List<ReceiptDTO> entityListToDtoList(List<Receipt> receiptList) {
        if (receiptList == null) {
            return null;
        }
        List<ReceiptDTO> mappedList = new ArrayList<>(receiptList.size());
        for (Receipt receipt : receiptList) {
            mappedList.add(entityToDto(receipt));
        }
        return mappedList;
    }
}