package com.sised.controller;
import com.sised.model.DocumentFile;
import com.sised.repository.DocumentFileRepository;
import com.sised.service.DemandeEquiService;
import com.sised.service.DocumentFileService;
import com.sised.service.FileStorageService;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/SISED/demandeur/{demandeurId}/DemandeEquivalence/{demandeEquiId}")
public class DocumentFileController {

    private static final Logger logger = LoggerFactory.getLogger(DocumentFileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private DocumentFileRepository documentFileRepository;

    @Autowired
    private DemandeEquiService demandeEquiService;

    @Autowired
    DocumentFileService docfileservice;

    @GetMapping("/documentfile")
    public List<DocumentFile> getAllfiles(){
        return docfileservice.getAlldocumentfile();
    }

    @PostMapping("/uploadfile")
    public ResponseEntity<DocumentFile> uploadFile(@RequestParam("file") MultipartFile file ,
                                   @Valid @ModelAttribute DocumentFile docfileRequest ,
                                   @PathVariable (value = "demandeEquiId") Long id) {
 // we use ModelAttribute instead of requestBody to be able to insert data as key value with file
        demandeEquiService.getDemandeEquivalence(id).map(demandeEquivalence -> {
           docfileRequest.setDemandeEquivalence(demandeEquivalence);

            String nom = fileStorageService.storeFile(file);
            String cheminFichier = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadfile/")
                    .path(nom)
                    .toUriString();

            docfileRequest.setNomFichier(nom);
            docfileRequest.setCheminFichier(cheminFichier);
            docfileRequest.setTypeFichier(file.getContentType());
            docfileRequest.setTailleFichier(file.getSize());

            return documentFileRepository.save(docfileRequest);
        });

        return ResponseEntity.ok().body(docfileRequest);
    }

    @PostMapping("/uploadMultipleFiles")
    public List<ResponseEntity<DocumentFile>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files ,
                                                                  @Valid @ModelAttribute DocumentFile docfileRequest,
                                                                  @PathVariable (value = "id") Long id) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file,docfileRequest,id))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadfile/{nomFichier:.+}")
    //@RequestMapping(value = "/downloadfile/{nomFichier:.+}" , method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile(@PathVariable String nomFichier, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(nomFichier);
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }
        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
