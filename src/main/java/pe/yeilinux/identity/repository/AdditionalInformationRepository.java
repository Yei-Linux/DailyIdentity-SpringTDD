package pe.yeilinux.identity.repository;

import pe.yeilinux.identity.domain.AdditionalInformation;

import java.io.IOException;
import java.util.List;

public interface AdditionalInformationRepository {
    public List<AdditionalInformation> getAdditionalInformationByUser(String username);
    public List<AdditionalInformation> getAdditionalInformationFields();
    public List<AdditionalInformation> getAdditionalInformation(int tokenId) throws IOException;
    public void createAdditionalInformation(String additionalInformation,int tokenId);
    public String getValueWithDynamicalField(String field,String username);
}
