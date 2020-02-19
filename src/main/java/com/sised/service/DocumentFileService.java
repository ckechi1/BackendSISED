package com.sised.service;

import com.sised.model.DocumentFile;
import com.sised.repository.DocumentFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentFileService {

    @Autowired
    private DocumentFileRepository documentFileRepository;

    public List<DocumentFile> getAlldocumentfile(){
        return documentFileRepository.findAll();
    }

    public DocumentFile saveDocumentFile(DocumentFile documentfile){
        return documentFileRepository.save(documentfile);
    }

    public Optional<DocumentFile> getDocumentfilebyId(Long id ){
        return documentFileRepository.findById(id);
    }

    public Boolean deleteDocumentIfExists(Long id){
        return documentFileRepository.existsById(id);
    }
}
