package org.alfresco.ml.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.ml.action.bean.SentimentAnalysisResponseBean;
import org.alfresco.ml.model.MLSAContentModel;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SendToSentimentAnalysisServiceActionExecuter extends ActionExecuterAbstractBase {
	
	private ServiceRegistry serviceRegistry;
	private String sentimentAnalysisAPIUrl;

	public void setSentimentAnalysisAPIUrl(String sentimentAnalysisAPIUrl) {
		this.sentimentAnalysisAPIUrl = sentimentAnalysisAPIUrl;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	@Override
	protected void executeImpl(Action action, NodeRef nodeRef) {
		
		ContentService contentService = serviceRegistry.getContentService();
		ContentReader reader = contentService.getReader(nodeRef, ContentModel.PROP_CONTENT);
		
		String content = reader.getContentString();
		
        try {   
        	
			HttpClient httpClient = new HttpClient();
	        PostMethod sentimentAnalyzeMethod = new PostMethod(sentimentAnalysisAPIUrl + "/text");
	        sentimentAnalyzeMethod.setRequestEntity(new StringRequestEntity(content, MimetypeMap.MIMETYPE_TEXT_PLAIN, "UTF-8"));
	        int status = httpClient.executeMethod(sentimentAnalyzeMethod);
	        
	        if (status == HttpStatus.SC_OK) {
	        	
	        	String response = sentimentAnalyzeMethod.getResponseBodyAsString();
	        	SentimentAnalysisResponseBean sarb = new ObjectMapper().readValue(response, SentimentAnalysisResponseBean.class);
	        	
	        	NodeService nodeService = serviceRegistry.getNodeService();
	        	Map<QName, Serializable> properties = new HashMap<QName, Serializable>();
	        	properties.put(MLSAContentModel.PROP_POSITIVE_COUNT, sarb.getRanking().getPositive());
	        	properties.put(MLSAContentModel.PROP_NEGATIVE_COUNT, sarb.getRanking().getNegative());
	        	properties.put(MLSAContentModel.PROP_NEUTRAL_COUNT, sarb.getRanking().getNeutral());
				nodeService.addAspect(nodeRef, MLSAContentModel.ASPECT_SENTIMENT_ANALYZABLE, properties );
	        	
	        	
	        } else {
	        	throw new RuntimeException("Status " + status + ", " + sentimentAnalyzeMethod.getResponseBodyAsString());
	        }
	        
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }

		
	}

	@Override
	protected void addParameterDefinitions(List<ParameterDefinition> paremeters) {
	}
	
}
