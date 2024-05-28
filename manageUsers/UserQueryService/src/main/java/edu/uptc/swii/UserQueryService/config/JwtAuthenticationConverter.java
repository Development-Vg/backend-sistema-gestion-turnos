package edu.uptc.swii.UserQueryService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


//Convierte los el nombre de los roles ya que por defecto spring security les agraga ROL al final, de esta manera se convieten en permisos dentro de nuestra aplicacion
@Component
public class JwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken>{
    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Value("${jwt.auth.converter.principaleAttribute}")
    private String principaleAttribute;

    @Value("${jwt.auth.converter.resouce-id}")
    private String resourceId;

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream
                .concat(jwtGrantedAuthoritiesConverter.convert(jwt).stream(), extractResourcesRoles(jwt).stream())
                .toList();
        return new JwtAuthenticationToken(jwt, authorities, getPricipalName(jwt));
    }

    private  Collection <? extends GrantedAuthority> extractResourcesRoles(Jwt jwt){
        Map<String, Object> resourceAccess;
        Map<String, Object> resource;
        Collection<String> resourceRoles;

        if (jwt.getClaim("resource_access") == null){
            return List.of();
        }
        resourceAccess = jwt.getClaim("resource_access");
        if(resourceAccess.get(resourceId) == null){
            return List.of();
        }
        resource = (Map<String, Object>) resourceAccess.get(resourceId);
        if(resource.get("roles") == null){
            return List.of();
        }
        resourceRoles = (Collection<String>) resource.get("roles");
        return resourceRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role)))
                .toList(); // ROLE_usser
    }

    private String getPricipalName(Jwt jwt){
        String claimName = JwtClaimNames.SUB;
        if (principaleAttribute != null){
            claimName = principaleAttribute;
        }
        return jwt.getClaim(claimName);
    }
}
