package io.erocurement.b2b.auth;


import io.erocurement.b2b.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig {

    @Value("${public.key.path}")
    private String publicKeyPath;

    @Value("${private.key.path}")
    private String privateKeyPath;





    public String getRsaPrivate()
    {

        return FileUtils.getResourceFileAsString(this.privateKeyPath);
    }

    public String getRsaPublic()
    {
        return FileUtils.getResourceFileAsString(this.publicKeyPath);
    }



}
