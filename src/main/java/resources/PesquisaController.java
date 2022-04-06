package resources;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import model.Cad;


import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static io.quarkus.hibernate.orm.panache.PanacheEntityBase.count;
import static io.quarkus.hibernate.orm.panache.PanacheEntityBase.find;


@Path("/pesquisa")
public class PesquisaController {
    //Lista de estados e cidades

    @GET
    @Produces(MediaType.APPLICATION_JSON)
        public List<Cad> pesquisa() {

            return Cad.listAll();

        }
    //paginacao
    @Transactional
    @QueryParam("/{page}/{size}")

    public List<Cad> listCadByPage(int page, int size){
       PanacheQuery<Cad> listCad = find("pagina",true);
        return  listCad.page(Page.of(page, size)).list();
    }

//contagem de registros
@Transactional
    @GET
   public long countCad(){
       return Cad.count();
    }

    //Inserindo Estado e cidade
    @POST
    @Transactional
    public Cad create(Cad Cadastro) {

        Cadastro.persist();

        return Cadastro;
    }
    //Atualizando Estado e cidade
    @PUT
    @Transactional
    public Cad update(Cad cad) {
        Cad cadastroAtt = Cad.findById(cad.id);
        cadastroAtt.estado = cad.estado;
        cadastroAtt.cidade = cad.cidade;

        return cadastroAtt;
    }

    //Deletando Estado e cidade
    @DELETE
    @Transactional
    @Path("/{id}")
    public void delete(@PathParam("id") Long id){

        Cad.deleteById(id);
    }

   @Transactional
    @GET
    @Path("/filter")
   //pesquisando por estado
   public List<Cad> listEstado(String estado) {

       return find("estado(estado) = :estado", Parameters.with("estado", estado)).list();
   }
    //pesquisando por cidade
    @Transactional
    @GET
    @QueryParam("/filter")
    public List<Cad> listCidade(String cidade) {
        return find("cidade(cidade) = :cidade", Parameters.with("estado", cidade)).list();
    }
}