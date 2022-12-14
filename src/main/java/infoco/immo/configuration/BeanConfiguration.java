package infoco.immo.configuration;

import infoco.immo.database.SQL.appartment.ApartmentRepository;
import infoco.immo.database.SQL.payment.PaymentRepository;
import infoco.immo.database.SQL.rent.RentRepository;
import infoco.immo.database.SQL.tenant.TenantRepository;
import infoco.immo.database.SQL.user.UserRepository;
import infoco.immo.files.FilesGenerator;
import infoco.immo.usecase.appartment.ApartmentUseCase;
import infoco.immo.usecase.files.FilesUseCase;
import infoco.immo.usecase.payment.PaymentUseCase;
import infoco.immo.usecase.rent.RentUseCase;
import infoco.immo.usecase.tenant.TenantUseCase;
import infoco.immo.usecase.user.UserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableAutoConfiguration
public class BeanConfiguration implements EnvironmentAware {

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    RentRepository rentRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    FilesGenerator filesGenerator;

    Environment environment;
    @Bean
    public String getSecret(){
        return environment.getProperty("secret.key");
    }

    @Bean(name = "ApartmentBean")
    public ApartmentUseCase apartmentUseCase(){
        return new ApartmentUseCase(apartmentRepository);
    }

    @Bean
    public TenantUseCase tenantUseCase(){
        return new TenantUseCase(tenantRepository);
    }

    @Bean
    public RentUseCase rentUseCase(){
        return new RentUseCase(rentRepository, apartmentRepository,tenantRepository, paymentRepository);
    }

    @Bean
    public PaymentUseCase paymentUseCase(){
        return new PaymentUseCase(paymentRepository);
    }

    @Bean
    public UserUseCase userUseCase(){
        return
                new UserUseCase(userRepository,getSecret(), bCryptPasswordEncoder);
    }



    public FilesUseCase  filesUseCase(){
        return new FilesUseCase(paymentRepository,filesGenerator, apartmentRepository, rentRepository, tenantRepository);
    }


}
