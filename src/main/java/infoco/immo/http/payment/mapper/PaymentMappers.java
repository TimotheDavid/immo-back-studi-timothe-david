package infoco.immo.http.payment.mapper;

import infoco.immo.core.Payment;
import infoco.immo.database.SQL.payment.PaymentData;
import infoco.immo.http.payment.dto.CreateDTOPayment;
import infoco.immo.http.payment.dto.UpdateDTOPayment;
import infoco.immo.http.payment.response.PaymentDataResponse;
import infoco.immo.http.payment.response.PaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract  class   PaymentMappers {

    public static final PaymentMappers INSTANCE = Mappers.getMapper(PaymentMappers.class);

    public abstract Payment createDTOToDomain(CreateDTOPayment paymentdto);

    public abstract Payment updatePaymentDTOToDomain(UpdateDTOPayment paymentDTO);

    public abstract PaymentResponse domainToResponse(Payment payment);

     public static CreateDTOPayment domaineToCreateDTO(Payment payment){

        if(payment == null){
            return null;
        }

        CreateDTOPayment createDTOPayment = new CreateDTOPayment();

        createDTOPayment.setDatePayment(payment.getDatePayment());
        createDTOPayment.setPaymentRentId(payment.getPaymentRentId());
        createDTOPayment.setTypePayment(payment.getTypePayment());
        createDTOPayment.setAmountPayment(payment.getAmount());
        createDTOPayment.setSens(payment.getSens());
        createDTOPayment.setAgencyPart(payment.getAgencyPart());
        createDTOPayment.setLandlorPart(payment.getLandlorPart());
        createDTOPayment.setRentId(payment.getRentId().toString());
        createDTOPayment.setOrigin(payment.getOrigin());
        createDTOPayment.setSens(payment.getSens());
        createDTOPayment.setPaymentRentId(payment.getPaymentRentId());

        return createDTOPayment;

    };

    public abstract UpdateDTOPayment domaineToUpdateDTO(Payment payment);

    public  abstract PaymentDataResponse domaineToPaymentDataResponse(PaymentData paymentData);
}
