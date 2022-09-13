package infoco.immo.http.payment.mapper;

import infoco.immo.core.Payment;
import infoco.immo.http.payment.dto.CreateDTOPayment;
import infoco.immo.http.payment.dto.UpdateDTOPayment;
import infoco.immo.http.payment.response.PaymentResponse;
import infoco.immo.http.rent.mapper.RentMappers;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {

    public static final PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    Payment createDTOToDomain(CreateDTOPayment paymentdto);

    Payment updatePaymentDTOToDomain(UpdateDTOPayment paymentDTO);

    PaymentResponse domainToResponse(Payment payment);
}
