package pe.yeilinux.identity.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.yeilinux.identity.domain.AdditionalInformation;
import pe.yeilinux.identity.service.AdditionalInformationService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class ObjectUtil {

    @Autowired
    AdditionalInformationService additionalInformationService;

    public Map<String,Object> parseStringToMap (String objectString) throws JSONException {
        Map<String,Object> additionalInformation = new HashMap<>();

        JSONObject jsonObject  = new JSONObject(objectString);
        Iterator iterator = jsonObject.keys();
        while(iterator.hasNext()){
            String key = (String)iterator.next();
            Object value = jsonObject.getString(key);
            additionalInformation.put(key,value);
        }
        return additionalInformation;
    }

    public Map<String,Object> parseObjectToMap(List<AdditionalInformation> additionalInformationList){
        Map<String,Object> additionalInformationMap = new HashMap<>();
        for (AdditionalInformation additionalInformation:additionalInformationList) {
            additionalInformationMap.put(additionalInformation.getField(),additionalInformation.getValue());
        }
        return additionalInformationMap;
    }

    public Map<String,Object> setAdditionalInformationUser(List<AdditionalInformation> additionalInformationList,String username){
        Map<String,Object> additionalInformationMap = new HashMap<>();
        for (AdditionalInformation additionalInformation: additionalInformationList) {
            String value = this.additionalInformationService.getValueWithDynamicalField(additionalInformation.getField(),username);
            additionalInformationMap.put(additionalInformation.getField(),value);
        }
        return additionalInformationMap;
    }
}
