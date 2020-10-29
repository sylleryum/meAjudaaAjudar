package com.sylleryum.meajudaaajudar.controller;

import com.sylleryum.meajudaaajudar.commons.EntityToHATEOAS;
import com.sylleryum.meajudaaajudar.commons.TraceIdGenerator;
import com.sylleryum.meajudaaajudar.entity.Cidade;
import com.sylleryum.meajudaaajudar.assembler.CidadeModelAssembler;
import com.sylleryum.meajudaaajudar.assembler.CidadeRepresentationModelAssembler;
import com.sylleryum.meajudaaajudar.assembler.HATEOASController;
import com.sylleryum.meajudaaajudar.exception.ResourceNotFoundException;
import com.sylleryum.meajudaaajudar.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/cidades")
public class CidadeController implements HATEOASController {

    private final CidadeService cidadeService;
    private final CidadeRepresentationModelAssembler cidadeRepresentationModelAssembler;
    private final PagedResourcesAssembler<Cidade> cidadePagedResourcesAssembler;
    private final CidadeModelAssembler cidadeModelAssembler;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public CidadeController(CidadeService cidadeService, CidadeRepresentationModelAssembler cidadeRepresentationModelAssembler, PagedResourcesAssembler<Cidade> cidadePagedResourcesAssembler, CidadeModelAssembler cidadeModelAssembler) {
        this.cidadeService = cidadeService;
        this.cidadeRepresentationModelAssembler = cidadeRepresentationModelAssembler;
        this.cidadePagedResourcesAssembler = cidadePagedResourcesAssembler;
        this.cidadeModelAssembler = cidadeModelAssembler;
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<?> findAll(Pageable pageable,
                                     @RequestHeader(value = "trace-id") Optional<String> traceIdHeader) {

        String traceId = TraceIdGenerator.generateTraceId(traceIdHeader);
        //public Page<Cidade> findAll(Pageable pageable){

        //return ResponseEntity.ok(this.assembler.toCollectionModel(this.cidadeService.findAll(pageable)));
        //return cidadeService.findAll(pageable);

//        Page<Cidade> cidades = cidadeService.findAll(pageable);
//        PagedModel<EntityModel<Cidade>> cidadePagedModel = cidadePagedResourcesAssembler
//                .toModel(cidades, cidadeRepresentationModelAssembler);
//        return new ResponseEntity(cidadePagedModel, HttpStatus.OK);
//        Page<Cidade> inventories = cidadeService.findAll(pageable);
//
//        return ResponseEntity
//                .ok()
//                .contentType(MediaTypes.HAL_JSON)
//                .body(cidadePagedResourcesAssembler.toModel(inventories, cidadeModelAssembler));
        Page<Cidade> results = cidadeService.findAll(pageable);
        results.forEach(i -> {
            try {
                EntityToHATEOAS.cidadeToHATEOAS(i);
            } catch (ResourceNotFoundException e) {
            }
        });

        return new ResponseEntity(results, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id,
                                      @RequestHeader(value = "trace-id") Optional<String> traceIdHeader) throws ResourceNotFoundException {

        String traceId = TraceIdGenerator.generateTraceId(traceIdHeader);
//        return cidadeService.findById(id).map(cidadeRepresentationModelAssembler::toModel)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound()
//                        .build());
        Optional<Cidade> results = cidadeService.findById(id, traceId);
        if (results.isPresent()) {
            EntityToHATEOAS.cidadeToHATEOAS(results.get());
            return new ResponseEntity(results.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/ativas")
    public ResponseEntity<?> findByAtivas(Pageable pageable,
                                          @RequestHeader(value = "trace-id") Optional<String> traceIdHeader) {
        String traceId = TraceIdGenerator.generateTraceId(traceIdHeader);

        Page<Cidade> results = cidadeService.findAllActiveCidades(pageable, traceId);

        results.forEach(i -> {
            try {
                EntityToHATEOAS.cidadeToHATEOAS(i);
            } catch (ResourceNotFoundException e) {
            }
        });
        return new ResponseEntity(results, HttpStatus.OK);

    }

    @GetMapping("/search")
    public ResponseEntity<?> findByNome(Pageable pageable,
                                        @RequestParam Optional<String> nome,
                                        @RequestHeader(value = "trace-id") Optional<String> traceIdHeader) {
        String traceId = TraceIdGenerator.generateTraceId(traceIdHeader);
//        System.out.println();
//        Page<Cidade> cidadesSearch = null;
//        if (nome.isPresent()) {
//            cidadesSearch = cidadeService.findByNome(nome.get(), pageable);
//            PagedModel<EntityModel<Cidade>> cidadePagedModel = cidadePagedResourcesAssembler.toModel(cidadesSearch, cidadeRepresentationModelAssembler);
//            System.out.println();
//            return new ResponseEntity(cidadePagedModel, HttpStatus.OK);
//
//        }
//        System.out.println();
//        return null;
        System.out.println();
        Page<Cidade> results;
        if (nome.isPresent()) {
            results = cidadeService.findByNome(nome.get(), pageable);
            results.forEach(i -> {
                try {
                    EntityToHATEOAS.cidadeToHATEOAS(i);
                } catch (ResourceNotFoundException e) {
                }
            });
            return new ResponseEntity(results, HttpStatus.OK);

        }
        System.out.println();
        return null;
    }

//    @PostMapping(value = {"/", ""})
//    public ResponseEntity<?> saveCidade(@RequestBody Cidade cidade) throws URISyntaxException {
//        System.out.println();
//        Estado estado = cidadeService.findById(2l).get().getEstado();
//        cidade.setEstado(estado);
//        Cidade cidadeToSave = cidadeService.save(cidade);
//        EntityModel<Cidade> employeeResource = new EntityModel<>(cidadeToSave, //
//                linkTo(methodOn(CidadeController.class).findById(cidadeToSave.getId())).withSelfRel());
//
//        return ResponseEntity //
//                .created(new URI(employeeResource.getRequiredLink(IanaLinkRelations.SELF).getHref())) //
//                .body(employeeResource);
//    }


}
