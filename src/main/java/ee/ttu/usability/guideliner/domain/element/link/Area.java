package ee.ttu.usability.guideliner.domain.element.link;

import ee.ttu.usability.guideliner.domain.attribute.AlternativeText;
import ee.ttu.usability.guideliner.domain.element.UsabilityGuideline;
import lombok.Data;

@Data
public class Area extends UsabilityGuideline {

	private AlternativeText alternativeText;

}
