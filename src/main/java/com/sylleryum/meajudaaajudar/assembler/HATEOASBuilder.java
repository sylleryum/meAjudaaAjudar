package com.sylleryum.meajudaaajudar.assembler;

import com.sylleryum.meajudaaajudar.exception.ResourceNotFoundException;
import org.springframework.hateoas.Link;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Needs to be extended by entities which will receive the HATEOAS, also contain static methods to add the same
 */
public class HATEOASBuilder{

    /**
     * here is where the first level links (HATEOAS) are stored, @Transient is used so the field is not persisted
     */
    @Transient
    private List<Link> links;

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    /**
     * Jackson will use this field to serialize/build the JSON
     * @return what will be serialized/shown as Json in the class that implements this
     */
    public List<Link> get_Links(){
        return this.links;
    }

    /**
     * this is needed to implement below methods generically
     * @return
     */
    public Long getId(){
        return null;
    }

    /**
     * first main method to insert the HATEOAS links
     * @param entity that extends {@link HATEOASBuilder}
     * @param ControllerClass given controller that will be used to build the links, to be passed to here as Classname.class
     * @param rel the name of the rel (findAll) to be inserted in the findAll method
     */
    public static void linksBuilder (HATEOASBuilder entity, Class<? extends HATEOASController> ControllerClass, String rel) throws ResourceNotFoundException {

        List<Link> linksList = new ArrayList<>();
        linksList.add(linkTo(methodOn(ControllerClass).findById(entity.getId(), null)).withSelfRel());
        linksList.add(linkTo(methodOn(ControllerClass).findAll(null, null)).withRel(rel));
        entity.setLinks(linksList);
    }

    /**
     * to be invoked for the list inside the main entity, E.g. Country entity invokes linksBuilder inside a forEach,
     * inside the same, this method is invoked passing the nested entity state
     * @param entity that extends {@link HATEOASBuilder}
     * @param ControllerClass given controller that will be used to build the links, to be passed to here as Classname.class
     * @param rel the name of the rel (findAll) to be inserted in the findAll method
     */
    public static void linksBuilderNestedEntity(List<? extends HATEOASBuilder> entity, Class<? extends HATEOASController> ControllerClass, String rel){

        entity.forEach(i-> {
            List<Link> linksList = new ArrayList<>();

            try {
                linksList.add(linkTo(methodOn(ControllerClass).findById(i.getId(), null)).withSelfRel());
            } catch (ResourceNotFoundException e) {

            }
            try {
                linksList.add(linkTo(methodOn(ControllerClass).findAll(null, null)).withRel(rel));
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
            i.setLinks(linksList);
        });
    }

        //*****************************************************************************************************************
        //this works perfectly, however, the problem is on how to dinamically
        // pass the nested class (E.g pass estado to this method, then send cidade to receive hateoas)
        //*****************************************************************************************************************
//    public static void linksBuilderWithMap (Page<? extends HATEOASBuilder> estado){
//
//        estado.forEach(i-> {
//            List<Link> linksList = new ArrayList<>();
//
//            linksList.add(linkTo(methodOn(HATEOASController.class).findById(i.getId())).withSelfRel());
//            linksList.add(linkTo(methodOn(HATEOASController.class).findAll(null)).withRel("estados"));
//            i.setLinks(linksList);
//        });
////
////        List<Link> linksList = new ArrayList<>();
////        linksList.add(linkTo(methodOn(EstadoController.class).getEstado(estado.getId())).withSelfRel());
////        linksList.add(linkTo(methodOn(EstadoController.class).findAll(null)).withRel("estados"));
////        estado.setLinks(linksList);
////        estado.forEach(i->{
////            List<Link> linksList = new ArrayList<>();
////            linksList.add(linkTo(methodOn(EstadoController.class).getEstado(i.getId())).withSelfRel());
////            linksList.add(linkTo(methodOn(EstadoController.class).findAll(null)).withRel("estados"));
////            i.setLinks(linksList);
////        });
//
//        //return null;
//    }



}
