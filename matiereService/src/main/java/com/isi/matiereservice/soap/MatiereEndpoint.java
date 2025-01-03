package com.isi.matiereservice.soap;

import com.isi.matiereservice.entities.MatiereEntity;
import com.isi.matiereservice.repository.MatiereRepository;
import com.isi.matiereservice.wsdl.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class MatiereEndpoint {

    private static final String NAMESPACE_URI = "http://ws.groupeisi.com";


    private MatiereRepository matiereRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateMatiereRequest")
    public @ResponsePayload AddMatiereResponse addMatiere(@RequestPayload AddMatiereRequest request) {
        MatiereEntity matiere = new MatiereEntity();
        matiere.setName(request.getName());
        matiere.setDescription(request.getDescription());
        matiereRepository.save(matiere);

        AddMatiereResponse response = new AddMatiereResponse();
        response.setStatus("Matière crée avec succès");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetMatiereRequest")
    public @ResponsePayload GetMatiereResponse getMatiere(@RequestPayload GetMatiereRequest request) {
        MatiereEntity matiere = matiereRepository.findById(request.getId()).orElse(null);

        GetMatiereResponse response = new GetMatiereResponse();
        if (matiere != null) {
            response.setName(matiere.getName());
            response.setDescription(matiere.getDescription());
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdateMatiereRequest")

    public @ResponsePayload UpdateMatiereResponse updateMatiere(@RequestPayload UpdateMatiereRequest request) {
        MatiereEntity matiere = matiereRepository.findById(request.getId()).orElse(null);
        UpdateMatiereResponse response = new UpdateMatiereResponse();

        if (matiere != null) {
            matiere.setName(request.getName());
            matiere.setDescription(request.getDescription());
            matiereRepository.save(matiere);
            response.setStatus("Matière mise à jour avec succès");
        } else {
            response.setStatus("Matière non trouvée");
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteMatiereRequest")
    @ResponsePayload
    public DeleteMatiereResponse deleteMatiere(@RequestPayload DeleteMatiereRequest request) {
        DeleteMatiereResponse response = new DeleteMatiereResponse();
        if (matiereRepository.existsById(request.getId())) {
            matiereRepository.deleteById(request.getId());
            response.setStatus("Matière supprimée avec succès");
        } else {
            response.setStatus("Matière non trouvée");
        }
        return response;
    }
}

