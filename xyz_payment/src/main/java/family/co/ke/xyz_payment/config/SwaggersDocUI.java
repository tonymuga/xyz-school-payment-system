package family.co.ke.xyz_payment.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(contact = @Contact(name = "XYZ ", email = "xyz@email.com", url = "https//xyz.com"), description = "API for XYZ platform", title = "XYZ API", version = "1.0.0", license = @License(name = "license name", url = "https://someurl.com")), servers = {

       @Server(description = "Local ENV", url = "http://localhost:${server.port}")

})
public class SwaggersDocUI {
    public SwaggersDocUI() {
    }
}
