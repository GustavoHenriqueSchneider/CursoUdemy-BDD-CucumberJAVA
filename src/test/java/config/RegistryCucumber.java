package config;

import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.cucumberexpressions.ParameterType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistryCucumber implements TypeRegistryConfigurer {
    @Override
    public void configureTypeRegistry(TypeRegistry registry) {
        registry.defineParameterType(
                new ParameterType<>("data","\\d{2}/\\d{2}/\\d{4}",Date.class,(String s)->{
                    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    Date retorno = format.parse(s);
                    return retorno;
                })
        );
    }
}
