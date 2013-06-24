package org.switchyard.quickstarts.publish.as.webservice.inonly;

import java.util.Map;

public interface FaultService {
    
    String DETAIL_CODE_CONTENT = "org.switchyard.quickstarts.message.fault.detail.code";
    String DETAIL_DESCRIPTION_CONTENT = "org.switchyard.quickstarts.message.fault.detail.description";
    String DETAIL_DETAIL_CONTENT = "org.switchyard.quickstarts.message.fault.detail.detail";
    
    String process(Map<String, String> message);
    
}
