package org.alfresco.ml.model;

import org.alfresco.service.namespace.QName;

public interface MLSAContentModel {
	
	static final String MLSA_MODEL = "http://www.alfresco.com/model/machine/learning/sentiment/analysis/1.0";
	static final String MLSA_PREFIX = "mlsa";

	static final QName ASPECT_SENTIMENT_ANALYZABLE = QName.createQName(MLSA_MODEL, "sentimentAnalyzable");
	static final QName PROP_POSITIVE_COUNT = QName.createQName(MLSA_MODEL, "positiveCount");
	static final QName PROP_NEGATIVE_COUNT = QName.createQName(MLSA_MODEL, "negativeCount");
	static final QName PROP_NEUTRAL_COUNT = QName.createQName(MLSA_MODEL, "neutralCount");

}
