package org.mfusco.sitesummarizer;

import java.util.function.Supplier;

import org.slf4j.Logger;

import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
class SiteSummarizer {

	private static final Logger LOGGER = org.slf4j.LoggerFactory
			.getLogger(SiteSummarizer.class);

	@Inject
	private SummarizerAiService summarizerAiService;

	public Multi<String> summarize(String topic) {
		String content = "asdf";
		return summarizeContent(content);
	}

	private Multi<String> summarizeContent(String content) {
		while (true) {
			try {
				LOGGER.info("Summarizing content " + content.length()
						+ " characters long");
				return summarizerAiService.summarize(content);
			} catch (Exception e) {
				if (content.length() > 1_000) {
					content = content.substring(0, content.length() - 1_000);
				} else {
					// just keep it as it s too short to summarize
					return Multi.createFrom().item(content);

				}
			}
		}
	}

}
