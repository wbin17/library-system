package library.management;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
public class TransactionConfig implements TransactionManagementConfigurer {

    private final DataSource dataSource;

    @Bean
    public TransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Override
    public TransactionManager annotationDrivenTransactionManager() {
        return null;
    }
}
