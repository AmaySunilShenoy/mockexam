package org.epita;

import jakarta.persistence.EntityManagerFactory;
import org.epita.datamodels.Patient;
import org.epita.services.InsuranceDAO;
import org.epita.services.MedicationDAO;
import org.epita.services.PatientDAO;
import org.epita.services.PrescriptionDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Configuration
public class ApplicationConfiguration {


    @Bean
    public DataSource getDataSource() {
        return new DriverManagerDataSource(
                "jdbc:h2:mem:test_mem;DB_CLOSE_DELAY=-1",
                "test",
                "test"
        );
    }

    @Bean
    public InsuranceDAO getInsuranceDAO(){
        return new InsuranceDAO();
    }

    @Bean
    public PatientDAO getPatientDAO(){
        return new PatientDAO();
    }

    @Bean
    public MedicationDAO getMedicationDAO(){
        return new MedicationDAO();
    }

    @Bean
    public PrescriptionDAO getPrescriptionDAO(){
        return new PrescriptionDAO();
    }

}
