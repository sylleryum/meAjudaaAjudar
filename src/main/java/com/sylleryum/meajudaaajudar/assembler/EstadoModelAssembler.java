package com.sylleryum.meajudaaajudar.assembler;

import com.sylleryum.meajudaaajudar.entity.Estado;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

//@Component
public class EstadoModelAssembler implements RepresentationModelAssembler<Estado, EntityModel<Estado>> {
    @Override
    public EntityModel<Estado> toModel(Estado entity) {
        return null;
    }

//    @Override
//    public EntityModel<Estado> toModel(Estado entity) {
//        entity.setCidades(toEstadoModel(entity.getCidades()));
//        EntityModel<Estado> entityModel = null;
//        //entity.setCidades(toEstadoModel(entity.getCidades()))
//        entityModel = EntityModel.of(entity,
//                linkTo(methodOn(EstadoController.class).getEstado(entity.getId()))
//                        .withSelfRel())
//                .add(linkTo(methodOn(CidadeController.class).findAll(null))
//                        .withSelfRel())
//                .add();
//
//        return entityModel;
//    }

//    private List<Cidade> toEstadoModel(List<Cidade> cidades) {
//        if (cidades.isEmpty())
//            return Collections.emptyList();
//
//        return cidades.stream()
//                .map(c -> {
//                    Cidade iC = new Cidade();
//                    iC.setId(c.getId());
//                    iC.setContato(c.getContato());
//                    iC.setNome(c.getNome());
//                    iC.add(linkTo(
//                            methodOn(CidadeController.class)
//                            .findById(c.getId()))
//                            .withSelfRel());
//                    return iC;
//                }).collect(Collectors.toList());
//    }
}
