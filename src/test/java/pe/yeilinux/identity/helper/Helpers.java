package pe.yeilinux.identity.helper;

import org.json.JSONException;
import org.json.JSONObject;
import pe.yeilinux.identity.factory.FactoriesTest;
import org.apache.commons.codec.binary.Base64;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Helpers {
    public double getRandomIndexArray(){
        return Math.random()* FactoriesTest.get_clients_application_array().size();
    }

    public List<String> decodeJWT(String jwtToken){
        String[] split_string = jwtToken.split("\\.");
        String base64EncodedHeader = split_string[0];
        String base64EncodedBody = split_string[1];
        String base64EncodedSignature = split_string[2];

        Base64 base64Url = new Base64(true);
        String header = new String(base64Url.decode(base64EncodedHeader));
        String body = new String(base64Url.decode(base64EncodedBody));
        String signature = new String(base64Url.decode(base64EncodedSignature));

        return Arrays.asList(header,body,signature);
    }

    public List<String> getKeysOfObject(String objectString) throws JSONException {
        List<String> keys = new ArrayList<>();
        Iterator iteratorOfKeys = new JSONObject(objectString).keys();
        while (iteratorOfKeys.hasNext()){
            keys.add((String) iteratorOfKeys.next());
        }
        return keys;
    }
}
