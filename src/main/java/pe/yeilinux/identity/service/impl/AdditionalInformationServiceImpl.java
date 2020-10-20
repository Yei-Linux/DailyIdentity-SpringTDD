package pe.yeilinux.identity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.yeilinux.identity.domain.AdditionalInformation;
import pe.yeilinux.identity.repository.AdditionalInformationRepository;
import pe.yeilinux.identity.service.AdditionalInformationService;

import java.io.IOException;
import java.util.List;

@Service
public class AdditionalInformationServiceImpl implements AdditionalInformationService {
    @Autowired
    AdditionalInformationRepository additionalInformationRepository;
    @Override
    public List<AdditionalInformation> getAdditionalInformationByUser(String username) {
        return this.additionalInformationRepository.getAdditionalInformationByUser(username);
    }

    @Override
    public List<AdditionalInformation> getAdditionalInformationFields() {
        return this.additionalInformationRepository.getAdditionalInformationFields();
    }

    @Override
    public List<AdditionalInformation> getAdditionalInformation(int tokenId) throws IOException {
        return this.additionalInformationRepository.getAdditionalInformation(tokenId);
    }

    @Override
    public void createAdditionalInformation(String additionalInformation,int tokenId) {
        this.additionalInformationRepository.createAdditionalInformation(additionalInformation,tokenId);
    }

    @Override
    public String getValueWithDynamicalField(String field, String username) {
        return this.additionalInformationRepository.getValueWithDynamicalField(field, username);
    }
}
