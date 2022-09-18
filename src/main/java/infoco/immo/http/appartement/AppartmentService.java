package infoco.immo.http.appartement;

import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.configuration.DatabaseConfiguration;
import infoco.immo.core.Apartment;
import infoco.immo.database.SQL.appartment.ApartmentRepository;
import infoco.immo.database.SQL.tenant.TenantRepository;
import infoco.immo.usecase.appartment.ApartmentPaymentData;
import infoco.immo.usecase.appartment.ApartmentUseCase;
import infoco.immo.usecase.appartment.ApartmentUseCaseI;
import infoco.immo.usecase.tenant.TenantUseCase;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppartmentService implements ApartmentUseCaseI {

    @Autowired
    BeanConfiguration beanConfiguration;

    @Override
    public void create(Apartment apartment) {
        apartment.setId(UUID.randomUUID());
        beanConfiguration.apartmentUseCase().create(apartment);
    }

    @Override
    public Apartment get(Apartment apartment) {
        return beanConfiguration.apartmentUseCase().get(apartment);
    }

    @Override
    public void update(Apartment apartment) {
        beanConfiguration.apartmentUseCase().update(apartment);
    }

    @Override
    public void delete(UUID apartmentId) {
        beanConfiguration.apartmentUseCase().delete(apartmentId);
    }

    public List<Apartment> get(){
       return  beanConfiguration.apartmentUseCase().get();
    }

    @Override
    public ApartmentPaymentData getPaymentByApartment(UUID apartment) {
        return null;
    }
}
