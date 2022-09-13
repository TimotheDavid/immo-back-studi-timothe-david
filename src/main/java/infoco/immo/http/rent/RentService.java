package infoco.immo.http.rent;

import infoco.immo.configuration.DatabaseConfiguration;
import infoco.immo.core.Rent;
import infoco.immo.database.SQL.rent.RentRepository;
import infoco.immo.usecase.rent.RentUseCase;
import infoco.immo.usecase.rent.RentUseCaseI;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RentService implements RentUseCaseI {

    private RentUseCase _rentUseCase(){
        RentRepository rentRepository = new RentRepository();
        rentRepository.setDataSource(new DatabaseConfiguration().dataSource());
        return new RentUseCase(rentRepository);
    }

    @Override
    public void create(Rent rent) {
        rent.setId(UUID.randomUUID());
        _rentUseCase().create(rent);
    }

    @Override
    public Rent get(Rent rent) {
        return _rentUseCase().get(rent);
    }

    @Override
    public List<Rent> get() {
        return _rentUseCase().get();
    }

    @Override
    public void update(Rent rent) {
        _rentUseCase().update(rent);

    }

    @Override
    public void delete(UUID rentId) {
        _rentUseCase().delete(rentId);
    }
}
