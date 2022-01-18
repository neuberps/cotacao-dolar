package com.neuber.souza.resource;

import com.neuber.souza.entity.Dolar;
import org.eclipse.microprofile.opentracing.Traced;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.Collection;

@Traced
@Path("/dolar")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DolarResource {

    @GET
    public Collection<Dolar> list() {
        return Dolar.listAll();
    }

    @GET
    @Path("/{id}")
    public Dolar get(@PathParam Integer id) {
        Dolar dolar = Dolar.findById(id);
        if (dolar != null){
            return new Dolar();
        } else {
            throw new NotFoundException("Dolar id não encontrado: " + id);
        }
    }

    @POST
    @Transactional
    public Collection<Dolar> add(Dolar dolar) {
        dolar.id = null;
        Dolar.persist(dolar);
        return Dolar.listAll();
    }

    @PUT
    @Transactional
    public Dolar update(Dolar dolar) {
        Dolar dolarBD = Dolar.findById(dolar.id);
        if(dolarBD != null){
            dolarBD.dataCotacao = dolar.dataCotacao;
            dolarBD.valorCompra = dolar.valorCompra;
            dolarBD.valorVenda = dolar.valorVenda;
            dolarBD.dataAtualizacao = LocalDateTime.now();
            Dolar.persist(dolarBD);
            return dolarBD;
        }else{
            throw new NotFoundException("Dolar id não encontrado: " + dolar.id);
        }

    }

    @DELETE
    @Transactional
    public void delete(Dolar dolar) {
        Dolar dolarBD  = Dolar.findById(dolar.id);
        if(dolarBD != null){
            Dolar.deleteById(dolar.id);
        }else{
            throw new NotFoundException("Dolar id não encontrado: " + dolar.id);
        }
    }
}