package infoco.immo.http.rent;

import infoco.immo.configuration.BeanConfiguration;
import infoco.immo.core.Rent;
import infoco.immo.usecase.rent.RentUseCaseI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RentService implements RentUseCaseI {


    @Autowired
    BeanConfiguration beanConfiguration;
    @Override
    public void create(Rent rent) {
        beanConfiguration.rentUseCase().create(rent);
    }

    @Override
    public Rent get(Rent rent) {
        return beanConfiguration.rentUseCase().get(rent);
    }

    @Override
    public List<Rent> get() {
        return beanConfiguration.rentUseCase().get();
    }

    @Override
    public void update(Rent rent) {
        beanConfiguration.rentUseCase().update(rent);

    }

    @Override
    public void delete(UUID rentId) {
        beanConfiguration.rentUseCase().delete(rentId);
    }
}
